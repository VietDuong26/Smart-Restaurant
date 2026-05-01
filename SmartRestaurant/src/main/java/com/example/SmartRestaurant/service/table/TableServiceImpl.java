package com.example.SmartRestaurant.service.table;

import com.example.SmartRestaurant.common.TableStatus;
import com.example.SmartRestaurant.config.userdetail.CustomUserDetails;
import com.example.SmartRestaurant.dto.request.TableRequest;
import com.example.SmartRestaurant.dto.response.TableResponse;
import com.example.SmartRestaurant.entity.ShopEntity;
import com.example.SmartRestaurant.entity.TableEntity;
import com.example.SmartRestaurant.exception.ActionDeniedException;
import com.example.SmartRestaurant.exception.ShopNotFoundException;
import com.example.SmartRestaurant.exception.TableNotFoundException;
import com.example.SmartRestaurant.exception.TableNotFoundInShopException;
import com.example.SmartRestaurant.mapper.TableMapper;
import com.example.SmartRestaurant.repository.ShopRepository;
import com.example.SmartRestaurant.repository.TableRepository;
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

    ShopRepository shopRepository;

    @Override
    public TableResponse create(TableRequest tableRequest) {
        ShopEntity shop = shopRepository.getById(tableRequest.getShopId());
        if (shop == null) {
            throw new ShopNotFoundException();
        }
        TableEntity table = mapper.toEntity(tableRequest);
        table.setCreatedAt(LocalDateTime.now());
        table.setShop(shop);
        return mapper.toResponse(repository.save(table));
    }

    @Override
    public TableResponse update(Long id, TableRequest tableRequest) {
        TableEntity table = repository.getById(id);
        if (table == null) {
            throw new TableNotFoundException();
        }
        ShopEntity shop = shopRepository.getById(tableRequest.getShopId());
        if (shop == null) {
            throw new ShopNotFoundException();
        }
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
    public void delete(Long id) {
        TableEntity table = repository.getById(id);
        if (table == null) {
            throw new TableNotFoundException();
        }
        table.setStatus(TableStatus.OUT_OF_SERVICE);
        repository.save(table);
    }

    @Override
    public TableResponse getById(Long id) {
        TableEntity table = repository.getById(id);
        if (table == null) {
            throw new TableNotFoundException();
        }
        return mapper.toResponse(table);
    }

    @Override
    public List<TableResponse> getAll() {
        return null;
    }

    @Override
    public List<TableResponse> getTablesByShopId(Long shopId, CustomUserDetails userDetails) {
        if (!userDetails.getAuthorities().stream().anyMatch(x -> x.getAuthority().equals("ROLE_ADMIN")
                || x.getAuthority().equals("ROLE_MANAGER"))) {
            throw new ActionDeniedException();
        }
        ShopEntity shop = shopRepository.getById(shopId);
        if (shop == null) {
            throw new ShopNotFoundException();
        }
        if (!userDetails.getAuthorities().stream().anyMatch(x -> x.getAuthority().equals("ROLE_ADMIN"))
                && !shop.getUser().getId().equals(userDetails.getUser().getId())) {
            throw new ActionDeniedException();
        }
        return repository.findByShopId(shopId).stream()
                .map(x -> mapper.toResponse(x)).collect(Collectors.toList());
    }
}
