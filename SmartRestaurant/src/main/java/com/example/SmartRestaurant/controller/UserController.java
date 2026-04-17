package com.example.SmartRestaurant.controller;

import com.example.SmartRestaurant.common.Const;
import com.example.SmartRestaurant.dto.request.UserRequest;
import com.example.SmartRestaurant.dto.response.ApiResponse;
import com.example.SmartRestaurant.dto.response.UserResponse;
import com.example.SmartRestaurant.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping(Const.PREFIX_VERSION + "/auth")
public class UserController {
    @Autowired
    UserService userService;

    @PostMapping("/register")
    ResponseEntity<ApiResponse<UserResponse>> register(@RequestBody UserRequest userRequest) throws Exception {
        try {
            return ResponseEntity.ok(
                    new ApiResponse<>(200
                            , "Success"
                            , userService.create(userRequest)
                            , LocalDateTime.now()
                    )
            );
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest()
                    .body(new ApiResponse<>(400
                                    , e.getMessage()
                                    , null
                                    , LocalDateTime.now()
                            )
                    );
        }
    }

}
