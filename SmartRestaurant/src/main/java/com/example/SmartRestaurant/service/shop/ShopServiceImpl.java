package com.example.SmartRestaurant.service.shop;

import com.example.SmartRestaurant.common.ShopStatus;
import com.example.SmartRestaurant.dto.request.ShopRequest;
import com.example.SmartRestaurant.dto.response.ShopResponse;
import com.example.SmartRestaurant.entity.ShopEntity;
import com.example.SmartRestaurant.entity.UserEntity;
import com.example.SmartRestaurant.exception.*;
import com.example.SmartRestaurant.mapper.ShopMapper;
import com.example.SmartRestaurant.repository.ShopRepository;
import com.example.SmartRestaurant.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
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
        ShopEntity shop = repository.getById(id);
        if (shop == null) {
            throw new ShopNotFoundException();
        }
        switch (shop.getStatus()) {
            case PENDING:
                throw new ShopInactivatedException();
            case BLOCKED:
                throw new ShopBlockedException();
            case DELETED:
                throw new ShopDeletedException();
        }
        UserEntity user = userRepository.getById(shopRequest.getUserId());
        if (user == null) {
            throw new UserNotFoundException();
        }
        ShopEntity newShop = mapper.toEntity(shopRequest);
        newShop.setUpdatedAt(LocalDateTime.now());
        newShop.setUser(user);
        return mapper.toResponse(repository.save(newShop));
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
    public void activateShop(Long id) {
        ShopEntity shop = repository.getById(id);
        if (shop == null) {
            throw new ShopNotFoundException();
        }
        shop.setStatus(ShopStatus.ACTIVE);
        repository.save(shop);
    }
}
