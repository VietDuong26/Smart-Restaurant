package com.example.SmartRestaurant.service.authorization;

import com.example.SmartRestaurant.entity.ShopEntity;

public interface AuthorizationService {
    void checkOwnerShop(ShopEntity shop);

    void checkPermissionInShop(ShopEntity shop, String permisisonName);
}
