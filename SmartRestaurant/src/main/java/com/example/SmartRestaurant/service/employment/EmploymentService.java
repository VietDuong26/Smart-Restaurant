package com.example.SmartRestaurant.service.employment;

import com.example.SmartRestaurant.dto.request.EmploymentRehireRequest;
import com.example.SmartRestaurant.dto.request.EmploymentRequest;
import com.example.SmartRestaurant.dto.request.EmploymentUpdateRequest;
import com.example.SmartRestaurant.dto.response.EmploymentResponse;

public interface EmploymentService {


    EmploymentResponse create(EmploymentRequest employmentRequest, Long parentId);

    EmploymentResponse update(Long id, EmploymentUpdateRequest employmentRequest);

    void delete(Long id);

    EmploymentResponse getById(Long id);

    EmploymentResponse createFromExistingUser(Long shopId, Long userId, EmploymentRehireRequest request);

}
