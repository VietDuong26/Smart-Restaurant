package com.example.SmartRestaurant.util.validateownership;

import com.example.SmartRestaurant.config.userdetail.CustomUserDetails;
import com.example.SmartRestaurant.entity.ShopEntity;
import com.example.SmartRestaurant.exception.ActionDeniedException;
import com.example.SmartRestaurant.exception.ShopNotFoundException;
import com.example.SmartRestaurant.repository.ShopRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class OwnerShipValidateService {
    ShopRepository shopRepository;

    public ShopEntity validateShopOwnership(ShopEntity shop, CustomUserDetails userDetails) {
        if (shop == null) {
            throw new ShopNotFoundException();
        }
        if (!shop.getUser().getId().equals(userDetails.getUser().getId())) {
            throw new ActionDeniedException();
        }
        return shop;
    }

    public boolean checkAdmin(CustomUserDetails userDetails) {
        return userDetails.getAuthorities().stream().anyMatch(x -> x.getAuthority().equals("ROLE_ADMIN"));
    }
}
