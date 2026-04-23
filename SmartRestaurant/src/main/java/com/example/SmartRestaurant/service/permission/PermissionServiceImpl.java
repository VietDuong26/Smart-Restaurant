package com.example.SmartRestaurant.service.permission;

import com.example.SmartRestaurant.dto.response.PermissionResponse;
import com.example.SmartRestaurant.entity.PermissionEntity;
import com.example.SmartRestaurant.entity.UserEntity;
import com.example.SmartRestaurant.exception.UserNotFoundException;
import com.example.SmartRestaurant.mapper.PermissionMapper;
import com.example.SmartRestaurant.repository.PermissionRepository;
import com.example.SmartRestaurant.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class PermissionServiceImpl implements PermissionService {
    PermissionRepository repository;
    UserRepository userRepository;
    PermissionMapper mapper;


    @Override
    public PermissionResponse create(PermissionEntity permission) {
        return null;
    }

    @Override
    public PermissionResponse update(Long id, PermissionEntity permission) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public PermissionResponse getById(Long id) {
        return null;
    }

    @Override
    public List<PermissionResponse> getAll() {
        return repository.findAll().stream().map(x -> mapper.toResponse(x)).collect(Collectors.toList());
    }

    @Override
    public List<PermissionResponse> getAllByUserId(Long userId) {
        UserEntity user = userRepository.getById(userId);
        if (user == null) {
            throw new UserNotFoundException();
        }
        return repository.findByUsersContains(user).stream().map(x -> mapper.toResponse(x)).collect(Collectors.toList());
    }
}
