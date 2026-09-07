package com.example.SmartRestaurant.service.orderitem;

import com.example.SmartRestaurant.common.enums.ProductStatus;
import com.example.SmartRestaurant.dto.request.StaffOrderItemRequest;
import com.example.SmartRestaurant.dto.response.OrderItemResponse;
import com.example.SmartRestaurant.entity.OrderEntity;
import com.example.SmartRestaurant.entity.OrderItemEntity;
import com.example.SmartRestaurant.entity.ProductEntity;
import com.example.SmartRestaurant.exception.NotFoundException;
import com.example.SmartRestaurant.exception.ValidateException;
import com.example.SmartRestaurant.mapper.OrderItemMapper;
import com.example.SmartRestaurant.repository.OrderItemRepository;
import com.example.SmartRestaurant.repository.OrderRepository;
import com.example.SmartRestaurant.repository.ProductRepository;
import com.example.SmartRestaurant.security.CurrentUserProvider;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@Transactional
public class OrderItemServiceImplement implements OrderItemService {
    OrderItemRepository repository;
    ProductRepository productRepository;

    OrderRepository orderRepository;

    OrderItemMapper mapper;
    CurrentUserProvider currentUserProvider;

    @Override
    public List<OrderItemResponse> createAll(Long orderId, List<StaffOrderItemRequest> itemRequests) {
        OrderEntity order = orderRepository.findById(orderId)
                .orElseThrow(() -> new NotFoundException("Đơn hàng"));
        Set<Long> productIds = itemRequests
                .stream()
                .map(x -> x.getProductId())
                .collect(Collectors.toSet());
        if (productIds.size() != itemRequests.size()) {
            throw new ValidateException("Có sản phẩm bị trùng");
        }
        List<ProductEntity> products = productRepository.findAllByIdInAndAndStatus(productIds, ProductStatus.ACTIVE);
        if (products.size() != productIds.size()) {
            throw new NotFoundException("Sản phẩm");
        }
        Map<Long, ProductEntity> productMap = new HashMap<>();
        products.forEach(x -> {
            productMap.put(x.getId(), x);
        });
        List<OrderItemEntity> preSaveOrderItemList = new ArrayList<>();
        itemRequests.forEach(x -> {
            OrderItemEntity orderItem = mapper.toEntity(x);
            orderItem.setOrder(order);
            ProductEntity product = productMap.get(x.getProductId());
            orderItem.setProduct(product);
            orderItem.setProductName(product.getName());
            orderItem.setPrice(product.getPrice());
            orderItem.setTotalPrice(product.getPrice().multiply(new BigDecimal(x.getQuantity())));
            orderItem.setUser(currentUserProvider.getCurrentUser().getUser());
            preSaveOrderItemList.add(orderItem);
        });
        return repository.saveAll(preSaveOrderItemList)
                .stream()
                .map(x -> mapper.toResponse(x))
                .collect(Collectors.toList());
    }
}
