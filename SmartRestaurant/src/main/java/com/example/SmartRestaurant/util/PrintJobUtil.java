package com.example.SmartRestaurant.util;

import com.example.SmartRestaurant.dto.response.OrderItemResponse;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;


public final class PrintJobUtil {
    public static String generateReceiptContentData(String shopName,
                                                    String phoneNumber,
                                                    String shopAddress,
                                                    Long orderId,
                                                    String tableName,
                                                    String areaName,
                                                    String userName,
                                                    LocalDateTime createdAt,
                                                    BigDecimal subTotal,
                                                    BigDecimal discount,
                                                    BigDecimal totalAmount,
                                                    String paymentMethod,
                                                    BigDecimal change,
                                                    List<OrderItemResponse> items) {
        String data = "{" +
                "\"shopName\":\"" + shopName + "\"," +
                "\"phoneNumber\":\"" + phoneNumber + "\"," +
                "\"shopAddress\":\"" + shopAddress + "\"," +
                "\"orderId\":" + orderId + "," +
                "\"tableName\":\"" + tableName + "\"," +
                "\"areaName\":\"" + areaName + "\"," +
                "\"userName\":\"" + userName + "\"," +
                "\"createdAt\":\"" + createdAt + "\"," +
                "\"subTotal\":" + subTotal + "," +
                "\"discount\":" + discount + "," +
                "\"totalAmount\":" + totalAmount + "," +
                "\"paymentMethod\":\"" + paymentMethod + "\"," +
                "\"change\":" + change + "," +
                "\"items\":[";

        for (int i = 0; i < items.size(); i++) {
            OrderItemResponse item = items.get(i);

            data += "{" +
                    "\"productName\":\"" + item.getProductName() + "\"," +
                    "\"price\":" + item.getPrice() + "," +
                    "\"quantity\":" + item.getQuantity() + "," +
                    "\"totalPrice\":" + item.getTotalPrice() +
                    "}";

            if (i < items.size() - 1) {
                data += ",";
            }
        }

        data += "]}";

        return data;
    }
}
