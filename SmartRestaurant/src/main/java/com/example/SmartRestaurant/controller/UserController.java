package com.example.SmartRestaurant.controller;

import com.example.SmartRestaurant.common.Const;
import com.example.SmartRestaurant.dto.request.UserRequest;
import com.example.SmartRestaurant.dto.response.ApiResponse;
import com.example.SmartRestaurant.dto.response.UserResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(Const.PREFIX_VERSION + "/auth")
public class UserController {
    @PostMapping("/register")
    ResponseEntity<ApiResponse<UserResponse>> register(@RequestBody UserRequest userRequest) {

    }

}
