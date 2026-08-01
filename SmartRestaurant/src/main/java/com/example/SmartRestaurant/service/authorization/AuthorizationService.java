package com.example.SmartRestaurant.service.authorization;

public interface AuthorizationService {
    void checkOwnerShop(Long shopId);

    void hasPermissionInShop(Long shopId, String permissionName);
}
