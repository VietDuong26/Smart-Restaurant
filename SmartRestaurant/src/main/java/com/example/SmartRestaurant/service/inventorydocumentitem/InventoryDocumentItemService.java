package com.example.SmartRestaurant.service.inventorydocumentitem;

import com.example.SmartRestaurant.dto.request.InventoryDocumentItemRequest;
import com.example.SmartRestaurant.entity.InventoryDocumentEntity;

import java.util.List;

public interface InventoryDocumentItemService {
    void createAll(InventoryDocumentEntity documentEntity, List<InventoryDocumentItemRequest> itemRequests);
}
