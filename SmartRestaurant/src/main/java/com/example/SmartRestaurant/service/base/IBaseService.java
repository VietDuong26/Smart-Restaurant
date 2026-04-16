package com.example.SmartRestaurant.service.base;

import java.util.List;

public interface IBaseService<Request, Response, Key> {
    Response create(Request request);

    Response update(Key id, Request request);

    void delete(Key id);

    Response getById(Key id);

    List<Response> getAll();
}
