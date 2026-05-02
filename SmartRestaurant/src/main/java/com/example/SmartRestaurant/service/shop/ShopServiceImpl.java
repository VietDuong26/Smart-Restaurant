package com.example.SmartRestaurant.service.shop;

import com.example.SmartRestaurant.common.ShopStatus;
import com.example.SmartRestaurant.config.userdetail.CustomUserDetails;
import com.example.SmartRestaurant.dto.request.ShopRequest;
import com.example.SmartRestaurant.dto.response.ShopResponse;
import com.example.SmartRestaurant.entity.ShopEntity;
import com.example.SmartRestaurant.entity.UserEntity;
import com.example.SmartRestaurant.exception.ShopNotFoundException;
import com.example.SmartRestaurant.exception.UserNotFoundException;
import com.example.SmartRestaurant.mapper.ShopMapper;
import com.example.SmartRestaurant.repository.ShopRepository;
import com.example.SmartRestaurant.repository.UserRepository;
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
public class ShopServiceImpl implements ShopService {
    UserRepository userRepository;

    ShopRepository repository;
    ShopMapper mapper;

    OwnerShipValidateService ownerShipValidateService;

    @Override
    public ShopResponse create(ShopRequest shopRequest, CustomUserDetails userDetails) {
        boolean isAdmin = ownerShipValidateService.checkAdmin(userDetails);
        //1.Nếu là admin thì tạo shop cho user trong request
        //2.Nếu không phải admin thì chỉ tạo cho user đang đăng nhập thôi
        UserEntity user = isAdmin
                ? userRepository.findById(shopRequest.getUserId())
                .orElseThrow(UserNotFoundException::new)
                : userRepository.findById(userDetails.getUser().getId())
                .orElseThrow(UserNotFoundException::new);
        ShopEntity shop = mapper.toEntity(shopRequest);
        shop.setUser(user);
        shop.setStatus(ShopStatus.PENDING);
        return mapper.toResponse(repository.save(shop));
    }

    @Override
    public ShopResponse update(Long id, ShopRequest request, CustomUserDetails userDetails) {
        //1.kiểm tra xem manager này có phải chủ shop hay không
        ShopEntity shop = repository.findById(id)
                .orElseThrow(ShopNotFoundException::new);
        ownerShipValidateService.validateShopOwnership(shop, userDetails);
        if (request.getName() != null) {
            shop.setName(request.getName().isEmpty() ? null : request.getName());
        }
        if (request.getAddress() != null) {
            shop.setAddress(request.getAddress().isEmpty() ? null : request.getAddress());
        }
        if (request.getPhoneNumber() != null) {
            shop.setPhoneNumber(request.getPhoneNumber().isEmpty() ? null : request.getPhoneNumber());
        }
        if (request.getOpenTime() != null) {
            shop.setOpenTime(request.getOpenTime());
        }
        if (request.getCloseTime() != null) {
            shop.setCloseTime(request.getCloseTime());
        }
        return mapper.toResponse(repository.save(shop));
    }

    @Override
    public void delete(Long id, CustomUserDetails userDetails) {
        ShopEntity shop = repository.findById(id)
                .orElseThrow(ShopNotFoundException::new);
        boolean isAdmin = ownerShipValidateService.checkAdmin(userDetails);
        if (!isAdmin) {
            ownerShipValidateService.validateShopOwnership(shop, userDetails);
        }
        shop.setStatus(ShopStatus.DELETED);
        shop.setDeletedAt(LocalDateTime.now());
        repository.save(shop);
    }

    @Override
    public ShopResponse getById(Long id, CustomUserDetails userDetails) {
        ShopEntity shop = repository.findById(id)
                .orElseThrow(ShopNotFoundException::new);
        boolean isAdmin = ownerShipValidateService.checkAdmin(userDetails);
        if (!isAdmin) {
            ownerShipValidateService.validateShopOwnership(shop, userDetails);
        }

        return mapper.toResponse(shop);
    }

    @Override
    public List<ShopResponse> getAll(CustomUserDetails userDetails) {
        return null;
    }

    @Override
    public List<ShopResponse> getAllByUserId(Long userId) {
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(UserNotFoundException::new);
        return repository.findByUser(user).stream().map(x -> mapper.toResponse(x)).collect(Collectors.toList());
    }

    @Override
    public ShopResponse activateShop(Long id) {
        ShopEntity shop = repository.findById(id)
                .orElseThrow(ShopNotFoundException::new);
        shop.setStatus(ShopStatus.ACTIVE);
        return mapper.toResponse(repository.save(shop));
    }


}
