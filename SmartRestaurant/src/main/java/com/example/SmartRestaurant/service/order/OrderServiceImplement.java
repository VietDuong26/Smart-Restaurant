package com.example.SmartRestaurant.service.order;

import com.example.SmartRestaurant.common.enums.AreaStatus;
import com.example.SmartRestaurant.common.enums.OrderStatus;
import com.example.SmartRestaurant.common.enums.PaymentStatus;
import com.example.SmartRestaurant.common.enums.TableStatus;
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

import java.util.List;

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
        List<OrderEntity> orderIsPendingInTableNowList = repository.findAllByTableIdAndOrderStatus(
                request.getTableId(),
                OrderStatus.PENDING);
        if (!orderIsPendingInTableNowList.isEmpty()) {
            throw new InvalidStatusException("bàn");
        }
        OrderEntity order = new OrderEntity();
        order.setTable(table);
        order.setOrderStatus(OrderStatus.PENDING);
        order.setPaymentStatus(PaymentStatus.PENDING);
        OrderEntity savedOrder = repository.save(order);
        //set orderid cho các item và lưu các item
        List<OrderItemResponse> itemResponses = orderItemService.createAll(savedOrder.getId(), request.getItemRequests());
        OrderResponse orderResponse = mapper.toResponse(savedOrder);
        orderResponse.setItemResponses(itemResponses);
        return orderResponse;
    }
}
