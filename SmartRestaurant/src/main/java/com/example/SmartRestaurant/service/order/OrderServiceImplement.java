package com.example.SmartRestaurant.service.order;

import com.example.SmartRestaurant.common.enums.*;
import com.example.SmartRestaurant.dto.request.OrderItemDeleteRequest;
import com.example.SmartRestaurant.dto.request.StaffOrderRequest;
import com.example.SmartRestaurant.dto.response.OrderItemResponse;
import com.example.SmartRestaurant.dto.response.OrderResponse;
import com.example.SmartRestaurant.entity.OrderEntity;
import com.example.SmartRestaurant.entity.ShopEntity;
import com.example.SmartRestaurant.entity.TableEntity;
import com.example.SmartRestaurant.exception.InvalidStatusException;
import com.example.SmartRestaurant.exception.NotFoundException;
import com.example.SmartRestaurant.exception.ValidateException;
import com.example.SmartRestaurant.mapper.OrderMapper;
import com.example.SmartRestaurant.repository.OrderRepository;
import com.example.SmartRestaurant.repository.ShopRepository;
import com.example.SmartRestaurant.repository.TableRepository;
import com.example.SmartRestaurant.service.authorization.AuthorizationService;
import com.example.SmartRestaurant.service.orderitem.OrderItemService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@Transactional
public class OrderServiceImplement implements OrderService {
    OrderRepository repository;

    AuthorizationService authorizationService;

    ShopRepository shopRepository;

    TableRepository tableRepository;

    OrderItemService orderItemService;

    OrderMapper mapper;

    @Override
    public OrderResponse create(StaffOrderRequest request, Long shopId) {
        ShopEntity shop = shopRepository.findById(shopId)
                .orElseThrow(() -> new NotFoundException("Cửa hàng"));
        authorizationService.checkOwnerOrPermissionInShop(shop, "PERM_ORDER_CREATE");
        TableEntity table = tableRepository.findById(request.getTableId())
                .orElseThrow(() -> new NotFoundException("Bàn"));
        if (!table.getArea().getShop().getId().equals(shopId)) {
            throw new ValidateException("Bàn không thuộc cửa hàng");
        }
        if (table.getArea().getStatus().equals(AreaStatus.INACTIVE)) {
            throw new InvalidStatusException("khu vực");
        }
        if (table.getStatus().equals(TableStatus.INACTIVE)) {
            throw new InvalidStatusException("bàn");
        }
        //kiểm tra xem có order nào thuộc bàn này tại điểm tạo không
        List<OrderEntity> orderIsPendingInTable = repository.findAllByTableIdAndOrderStatus(
                request.getTableId(),
                OrderStatus.PENDING);
        OrderEntity order;
        if (orderIsPendingInTable.isEmpty()) {
            order = new OrderEntity();
            order.setTable(table);
            order.setOrderStatus(OrderStatus.PENDING);
            order.setPaymentStatus(PaymentStatus.PENDING);
            order = repository.save(order);

        } else if (orderIsPendingInTable.size() == 1) {
            order = orderIsPendingInTable.get(0);
        } else {
            throw new ValidateException("Bàn có nhiều đơn hàng cùng lúc");
        }
        //set orderId cho các item và lưu các item
        List<OrderItemResponse> itemResponses = orderItemService.createAll(order, request.getItemRequests());
        OrderResponse orderResponse = mapper.toResponse(order);
        orderResponse.setItemResponses(itemResponses);
        table.setOrderStatus(TableOrderStatus.OCCUPIED);
        tableRepository.save(table);
        return orderResponse;
    }

    @Override
    public void deleteAll(Long shopId, OrderItemDeleteRequest request) {
        ShopEntity shop = shopRepository.findById(shopId)
                .orElseThrow(() -> new NotFoundException("Cửa hàng"));
        authorizationService.checkOwnerOrPermissionInShop(shop, "PERM_ORDER_CREATE");
        TableEntity table = tableRepository.findById(request.getTableId())
                .orElseThrow(() -> new NotFoundException("Bàn"));
        if (!table.getArea().getShop().getId().equals(shopId)) {
            throw new ValidateException("Bàn không thuộc cửa hàng");
        }
        if (table.getArea().getStatus().equals(AreaStatus.INACTIVE)) {
            throw new InvalidStatusException("khu vực");
        }
        if (table.getStatus().equals(TableStatus.INACTIVE)) {
            throw new InvalidStatusException("bàn");
        }
        //kiểm tra xem có order nào thuộc bàn này tại điểm tạo không
        List<OrderEntity> orderIsPendingInTable = repository.findAllByTableIdAndOrderStatus(
                request.getTableId(),
                OrderStatus.PENDING);
        OrderEntity order;
        if (orderIsPendingInTable.isEmpty()) {
            throw new ValidateException("Hiện tại không có đơn hàng nào trong bàn");
        }
        if (orderIsPendingInTable.size() > 1) {
            throw new ValidateException("Bàn có nhiều đơn hàng cùng lúc");
        }
        order = orderIsPendingInTable.get(0);
        orderItemService.deleteAll(order, request.getOrderItemIds());
    }

    @Override
    public OrderResponse createByQR(StaffOrderRequest request) {
        TableEntity table = tableRepository.findById(request.getTableId())
                .orElseThrow(() -> new NotFoundException("Bàn"));
        if (table.getArea().getStatus().equals(AreaStatus.INACTIVE)) {
            throw new InvalidStatusException("khu vực");
        }
        if (table.getStatus().equals(TableStatus.INACTIVE)) {
            throw new InvalidStatusException("bàn");
        }
        //kiểm tra xem có order nào thuộc bàn này tại điểm tạo không
        List<OrderEntity> orderIsPendingInTable = repository.findAllByTableIdAndOrderStatus(
                request.getTableId(),
                OrderStatus.PENDING);
        OrderEntity order;
        if (orderIsPendingInTable.isEmpty()) {
            order = new OrderEntity();
            order.setTable(table);
            order.setOrderStatus(OrderStatus.PENDING);
            order.setPaymentStatus(PaymentStatus.PENDING);
            order = repository.save(order);

        } else if (orderIsPendingInTable.size() == 1) {
            order = orderIsPendingInTable.get(0);
        } else {
            throw new ValidateException("Bàn có nhiều đơn hàng cùng lúc");
        }
        //set orderId cho các item và lưu các item
        List<OrderItemResponse> itemResponses = orderItemService.createAll(order, request.getItemRequests());
        OrderResponse orderResponse = mapper.toResponse(order);
        orderResponse.setItemResponses(itemResponses);
        table.setOrderStatus(TableOrderStatus.OCCUPIED);
        tableRepository.save(table);
        return orderResponse;
    }

    @Override
    public List<OrderResponse> getAllByShopIdAndDate(Long shopId, LocalDate date) {
        ShopEntity shop = shopRepository.findById(shopId)
                .orElseThrow(() -> new NotFoundException("Cửa hàng"));
        authorizationService.checkOwnerOrPermissionInShop(shop, "PERM_ORDER_VIEW");
        LocalDateTime start = date.atStartOfDay();
        LocalDateTime end = date.plusDays(1).atStartOfDay();
        List<OrderEntity> orders = repository.findAllByTable_Area_Shop_IdAndCreatedAtGreaterThanEqualAndCreatedAtBefore(
                shopId, start, end);
        return orders.stream().map(x -> mapper.toResponse(x)).collect(Collectors.toList());
    }

}
