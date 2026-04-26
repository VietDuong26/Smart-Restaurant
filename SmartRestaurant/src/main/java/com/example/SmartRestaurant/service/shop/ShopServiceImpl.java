package com.example.SmartRestaurant.service.shop;

import com.example.SmartRestaurant.common.ShopStatus;
import com.example.SmartRestaurant.config.userdetail.CustomUserDetails;
import com.example.SmartRestaurant.dto.request.ShopRequest;
import com.example.SmartRestaurant.dto.response.ShopResponse;
import com.example.SmartRestaurant.entity.ShopEntity;
import com.example.SmartRestaurant.entity.UserEntity;
import com.example.SmartRestaurant.exception.ActionDeniedException;
import com.example.SmartRestaurant.exception.ShopNotFoundException;
import com.example.SmartRestaurant.exception.UserNotFoundException;
import com.example.SmartRestaurant.mapper.ShopMapper;
import com.example.SmartRestaurant.repository.ShopRepository;
import com.example.SmartRestaurant.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ShopServiceImpl implements ShopService {
    UserRepository userRepository;

    ShopRepository repository;
    ShopMapper mapper;

    @Override
    public ShopResponse create(ShopRequest shopRequest) {
        UserEntity user = userRepository.getById(shopRequest.getUserId());
        if (user == null) {
            throw new UserNotFoundException();
        }
        ShopEntity shop = mapper.toEntity(shopRequest);
        shop.setUser(user);
        shop.setStatus(ShopStatus.PENDING);
        return mapper.toResponse(repository.save(shop));
    }

    @Override
    public ShopResponse update(Long id, ShopRequest shopRequest) {
        return null;
    }

    @Override
    public void delete(Long id) {
        ShopEntity shop = repository.getById(id);
        if (shop == null) {
            throw new ShopNotFoundException();
        }
    }

    @Override
    public ShopResponse getById(Long id) {
        ShopEntity shop = repository.getById(id);
        if (shop == null) {
            throw new ShopNotFoundException();
        }
        return mapper.toResponse(shop);
    }

    @Override
    public List<ShopResponse> getAll() {
        return null;
    }

    @Override
    public List<ShopResponse> getAllByUserId(Long userId) {
        UserEntity user = userRepository.getById(userId);
        if (user == null) {
            throw new UserNotFoundException();
        }
        return repository.findByUser(user).stream().map(x -> mapper.toResponse(x)).collect(Collectors.toList());
    }

    @Override
    public ShopResponse activateShop(Long id) {
        ShopEntity shop = repository.getById(id);
        if (shop == null) {
            throw new ShopNotFoundException();
        }
        shop.setStatus(ShopStatus.ACTIVE);
        return mapper.toResponse(repository.save(shop));
    }

    @Override
    public ShopResponse updateCustom(Long id, ShopRequest request, CustomUserDetails userDetails) {
        request.setUserId(userDetails.getAuthorities().stream().anyMatch(x -> x.getAuthority().equals("ROLE_ADMIN"))
                ? request.getUserId()
                : userDetails.getUser().getId());
        ShopEntity shop = repository.getById(id);
        UserEntity user = userRepository.getById(request.getUserId());
        if (user == null) {
            throw new UserNotFoundException();
        }
        if (shop == null) {
            throw new ShopNotFoundException();
        }
        if (shop.getUser().getId() != request.getUserId()) {
            throw new ActionDeniedException();
        }

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
        shop.setUser(user);
        return mapper.toResponse(repository.save(shop));
    }
}
