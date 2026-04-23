package com.example.SmartRestaurant.service.role;

import com.example.SmartRestaurant.dto.response.RoleResponse;
import com.example.SmartRestaurant.entity.RoleEntity;
import com.example.SmartRestaurant.entity.UserEntity;
import com.example.SmartRestaurant.exception.UserNotFoundException;
import com.example.SmartRestaurant.mapper.RoleMapper;
import com.example.SmartRestaurant.repository.RoleRepository;
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
public class RoleServiceImpl implements RoleService {
    RoleRepository repository;
    RoleMapper mapper;
    UserRepository userRepository;

    @Override
    public RoleResponse create(RoleEntity roleEntity) {
        return null;
    }

    @Override
    public RoleResponse update(Long id, RoleEntity roleEntity) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public RoleResponse getById(Long id) {
        return null;
    }

    @Override
    public List<RoleResponse> getAll() {
        return null;
    }

    @Override
    public List<RoleResponse> getAllByUserId(Long userId) {
        UserEntity user = userRepository.getById(userId);
        if (user == null) {
            throw new UserNotFoundException();
        }
        return repository.findByUsersContains(user).stream().map(x -> mapper.toResponse(x)).collect(Collectors.toList());
    }
}
