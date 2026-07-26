package com.example.SmartRestaurant.service.shop;

import com.example.SmartRestaurant.dto.request.ShopRequest;
import com.example.SmartRestaurant.dto.response.ShopResponse;
import com.example.SmartRestaurant.entity.ShopEntity;
import com.example.SmartRestaurant.entity.UserEntity;
import com.example.SmartRestaurant.mapper.ShopMapper;
import com.example.SmartRestaurant.repository.ShopRepository;
import com.example.SmartRestaurant.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.example.SmartRestaurant.validator.ShopValidator.validateCreate;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@Transactional
public class ShopServiceImplement implements ShopService {
    ShopRepository repository;

    UserRepository userRepository;

    ShopMapper mapper;

    @Override
    public ShopResponse create(ShopRequest request) {
        validateCreate(request);
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        UserEntity user = userRepository.findByEmail(email);
        ShopEntity shop = mapper.toEntity(request);
        shop.setUser(user);
        return mapper.toResponse(repository.save(shop));
    }

    @Override
    public ShopResponse update(Long id, ShopRequest request) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public ShopResponse getById(Long id) {
        return null;
    }
}
