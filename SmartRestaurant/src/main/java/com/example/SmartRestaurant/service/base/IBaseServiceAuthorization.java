package com.example.SmartRestaurant.service.base;

import com.example.SmartRestaurant.config.userdetail.CustomUserDetails;

import java.util.List;

public interface IBaseServiceAuthorization<Request, Response, Key, UserDetail> {
    Response create(Request request, CustomUserDetails userDetails);

    Response update(Key id, Request request, CustomUserDetails userDetails);

    void delete(Key id, CustomUserDetails userDetails);

    Response getById(Key id, CustomUserDetails userDetails);

    List<Response> getAll(CustomUserDetails userDetails);
}
