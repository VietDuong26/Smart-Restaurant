package com.example.SmartRestaurant.service.shop;

import com.example.SmartRestaurant.dto.request.ShopRequest;
import com.example.SmartRestaurant.dto.response.ShopResponse;
import com.example.SmartRestaurant.entity.ShopEntity;
import com.example.SmartRestaurant.exception.NotFoundException;
import com.example.SmartRestaurant.mapper.ShopMapper;
import com.example.SmartRestaurant.repository.ShopRepository;
import com.example.SmartRestaurant.security.CurrentUserProvider;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static com.example.SmartRestaurant.validator.ShopValidator.validateCreate;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@Transactional
public class ShopServiceImplement implements ShopService {
    ShopRepository repository;

    ShopMapper mapper;
    CurrentUserProvider currentUserProvider;

    @Override
    public ShopResponse create(ShopRequest request) {
        validateCreate(request);
        ShopEntity shop = mapper.toEntity(request);
        shop.setUser(currentUserProvider.getCurrentUser().getUser());
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

    @Override
    public List<ShopResponse> getAllShopOfCurrentUser() {
        return repository.findAllByUser_Id(currentUserProvider.getCurrentUserId())
                .stream()
                .map(x -> mapper.toResponse(x))
                .collect(Collectors.toList());
    }

    @Override
    public ShopResponse getShopByIdOfCurrentUser(Long id) {
        ShopEntity shop = repository.findShopEntityById(id);
        if (shop == null) {
            throw new NotFoundException("Shop");
        }
        if (currentUserProvider.getCurrentUserId() != shop.getUser().getId()) {
            throw new AccessDeniedException("Không có quyền truy cập");
        }
        return mapper.toResponse(shop);
    }

}
