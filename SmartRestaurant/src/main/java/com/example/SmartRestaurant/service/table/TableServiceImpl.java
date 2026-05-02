package com.example.SmartRestaurant.service.table;

import com.example.SmartRestaurant.common.TableStatus;
import com.example.SmartRestaurant.config.userdetail.CustomUserDetails;
import com.example.SmartRestaurant.dto.request.TableRequest;
import com.example.SmartRestaurant.dto.response.TableResponse;
import com.example.SmartRestaurant.entity.ShopEntity;
import com.example.SmartRestaurant.entity.TableEntity;
import com.example.SmartRestaurant.exception.ShopNotFoundException;
import com.example.SmartRestaurant.exception.TableNotFoundException;
import com.example.SmartRestaurant.exception.TableNotFoundInShopException;
import com.example.SmartRestaurant.mapper.TableMapper;
import com.example.SmartRestaurant.repository.ShopRepository;
import com.example.SmartRestaurant.repository.TableRepository;
import com.example.SmartRestaurant.util.validateownership.OwnerShipValidateService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class TableServiceImpl implements TableService {
    TableRepository repository;
    TableMapper mapper;
    OwnerShipValidateService ownerShipValidateService;

    ShopRepository shopRepository;

    @Override
    public TableResponse create(TableRequest tableRequest, CustomUserDetails userDetails) {
        ShopEntity shop = shopRepository.findById(tableRequest.getShopId())
                .orElseThrow(ShopNotFoundException::new);//đây là shop trong request, chỉ admin được set thôi
        boolean isAdmin = ownerShipValidateService.checkAdmin(userDetails);
        //1.nếu là admin thì tạo bàn cho shop trong request
        //2. nếu không phải admin thì chỉ tạo trong shop mà thuộc quyền sở hữu của bản thân thôi
        TableEntity table = mapper.toEntity(tableRequest);
        table.setCreatedAt(LocalDateTime.now());
        table.setShop(isAdmin
                ? shop
                : ownerShipValidateService.validateShopOwnership(shop, userDetails));
        return mapper.toResponse(repository.save(table));
    }

    @Override
    public TableResponse update(Long id, TableRequest tableRequest, CustomUserDetails userDetails) {
        ShopEntity shop = shopRepository.findById(tableRequest.getShopId())
                .orElseThrow(ShopNotFoundException::new);
        ownerShipValidateService.validateShopOwnership(shop, userDetails);
        TableEntity table = repository.findById(id)
                .orElseThrow(TableNotFoundException::new);
        if (!table.getShop().getId().equals(shop.getId())) {
            throw new TableNotFoundInShopException(id, shop.getId());
        }
        if (tableRequest.getName() != null) {
            table.setName(tableRequest.getName());
        }
        if (tableRequest.getQrCode() != null) {
            table.setQrCode(tableRequest.getQrCode());
        }
        table.setUpdatedAt(LocalDateTime.now());
        return mapper.toResponse(repository.save(table));
    }

    @Override
    public void delete(Long id, CustomUserDetails userDetails) {
        TableEntity table = repository.findById(id)
                .orElseThrow(TableNotFoundException::new);

        boolean isAdmin = ownerShipValidateService.checkAdmin(userDetails);
        if (!isAdmin) {
            ownerShipValidateService.validateShopOwnership(table.getShop(), userDetails);
        }
        table.setStatus(TableStatus.OUT_OF_SERVICE);
        table.setUpdatedAt(LocalDateTime.now());
        repository.save(table);
    }

    @Override
    public TableResponse getById(Long id, CustomUserDetails userDetails) {
        return null;
    }

    @Override
    public List<TableResponse> getAll(CustomUserDetails userDetails) {
        return null;
    }


    @Override
    public List<TableResponse> getTablesByShopId(Long shopId, CustomUserDetails userDetails) {
        ShopEntity shop = shopRepository.findById(shopId)
                .orElseThrow(ShopNotFoundException::new);
        boolean isAdmin = ownerShipValidateService.checkAdmin(userDetails);
        if (!isAdmin) {
            ownerShipValidateService.validateShopOwnership(shop, userDetails);
        }
        return repository.findByShopId(shopId).stream()
                .map(x -> mapper.toResponse(x)).collect(Collectors.toList());

    }

    @Override
    public void updateStatus(Long tableId, TableStatus status, CustomUserDetails userDetails) {
        TableEntity table = repository.findById(tableId)
                .orElseThrow(TableNotFoundException::new);
        boolean isAdmin = ownerShipValidateService.checkAdmin(userDetails);
        if (!isAdmin) {
            //kiểm tra bàn của shop này có thuộc sở hữu của manager đang đăng nhập không
            ownerShipValidateService.validateShopOwnership(table.getShop(), userDetails);
        }
        table.setStatus(status);
        table.setUpdatedAt(LocalDateTime.now());
        repository.save(table);
    }
}
