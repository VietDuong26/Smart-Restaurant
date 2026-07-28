package com.example.SmartRestaurant.service.base;

public interface ShopResourceBaseService<Request, Response, Key> {
    Response create(Request request, Long shopId);

    Response update(Key id, Request request);

    void delete(Key id);

    Response getById(Key id);
}
