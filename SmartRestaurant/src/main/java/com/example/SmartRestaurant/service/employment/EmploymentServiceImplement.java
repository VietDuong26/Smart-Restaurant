package com.example.SmartRestaurant.service.employment;

import com.example.SmartRestaurant.common.enums.EmploymentStatus;
import com.example.SmartRestaurant.dto.request.EmploymentRehireRequest;
import com.example.SmartRestaurant.dto.request.EmploymentRequest;
import com.example.SmartRestaurant.dto.response.EmploymentResponse;
import com.example.SmartRestaurant.entity.EmploymentEntity;
import com.example.SmartRestaurant.entity.RoleEntity;
import com.example.SmartRestaurant.entity.ShopEntity;
import com.example.SmartRestaurant.entity.UserEntity;
import com.example.SmartRestaurant.exception.NotFoundException;
import com.example.SmartRestaurant.exception.ValidateException;
import com.example.SmartRestaurant.mapper.EmploymentMapper;
import com.example.SmartRestaurant.mapper.UserMapper;
import com.example.SmartRestaurant.repository.EmploymentRepository;
import com.example.SmartRestaurant.repository.RoleRepository;
import com.example.SmartRestaurant.repository.ShopRepository;
import com.example.SmartRestaurant.repository.UserRepository;
import com.example.SmartRestaurant.security.CurrentUserProvider;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

import static com.example.SmartRestaurant.validator.EmploymentValidator.*;

@Service
public class EmploymentServiceImplement implements EmploymentService {
    EmploymentRepository repository;
    ShopRepository shopRepository;

    RoleRepository roleRepository;

    UserRepository userRepository;

    PasswordEncoder passwordEncoder;
    EmploymentMapper mapper;

    UserMapper userMapper;

    CurrentUserProvider currentUserProvider;

    @Override
    public EmploymentResponse create(EmploymentRequest employmentRequest, Long parentId) {
        ShopEntity shop = shopRepository.findById(parentId)
                .orElseThrow(() -> new NotFoundException("Shop"));
        //ở trên controller đã có check permission rồi
        //kiểm tra có phải chủ shop hoặc manager của shop không
        if (!shop.getUser().getId().equals(currentUserProvider.getCurrentUserId()) &&
                !repository.existsByUserIdAndShopIdAndStatus(
                        currentUserProvider.getCurrentUserId(),
                        parentId,
                        EmploymentStatus.ACTIVE)) {
            throw new NotFoundException("Quan hệ nhân viên-shop");
        }
        validateRequest(employmentRequest);
        //kiểm tra có roleId nào bị trùng trong request không
        Set<Long> roleIds = new HashSet<>(employmentRequest.getRoleIds());
        if (roleIds.size() != employmentRequest.getRoleIds().size()) {
            throw new ValidateException("Có vai trò bị trùng");
        }
        //kiểm tra có role nào mà không thuộc shop không
        Set<RoleEntity> roles = roleRepository.findAllByIdInAndShopId(roleIds, parentId);
        if (roles.size() != roleIds.size()) {
            throw new NotFoundException("Vai trò");
        }
        //kiểm tra email trùng
        if (userRepository.findByEmail(employmentRequest.getRegisterRequest().getEmail()) != null) {
            throw new ValidateException("Email đã có người sử dụng");
        }
        EmploymentEntity employment = mapper.toEntity(employmentRequest);
        UserEntity user = userMapper.toEntity(employmentRequest.getRegisterRequest());
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(roles);
        UserEntity savedUser = userRepository.save(user);
        employment.setStatus(EmploymentStatus.ACTIVE);
        employment.setShop(shop);
        employment.setUser(savedUser);
        return mapper.toResponse(repository.save(employment));
    }

    @Override
    public EmploymentResponse update(Long id, EmploymentRequest employmentRequest) {
        EmploymentEntity employment = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Quan hệ nhân viên-shop"));
        //kiểm tra có phải nhân viên hoặc chủ shop
        if (!employment.getShop().getUser().getId().equals(currentUserProvider.getCurrentUserId())
                && !repository.existsByUserIdAndShopIdAndStatus(
                currentUserProvider.getCurrentUserId(),
                employment.getShop().getId(),
                EmploymentStatus.ACTIVE)) {
            throw new NotFoundException("Quan hệ nhân viên-shop");
        }
        validateUpdateRequest(employmentRequest);
        employment.setSalary(employmentRequest.getSalary());
        return mapper.toResponse(repository.save(employment));
    }

    @Override
    public void delete(Long id) {
        EmploymentEntity employment = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Quan hệ nhân viên-shop"));
        //kiểm tra có phải nhân viên hoặc chủ shop
        if (!employment.getShop().getUser().getId().equals(currentUserProvider.getCurrentUserId())
                && !repository.existsByUserIdAndShopIdAndStatus(
                currentUserProvider.getCurrentUserId(),
                employment.getShop().getId(),
                EmploymentStatus.ACTIVE)) {
            throw new NotFoundException("Quan hệ nhân viên-shop");
        }
        employment.setStatus(EmploymentStatus.TERMINATED);
        repository.save(employment);
    }

    @Override
    public EmploymentResponse getById(Long id) {
        EmploymentEntity employment = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Quan hệ nhân viên-shop"));
        //kiểm tra có phải nhân viên hoặc chủ shop
        if (!employment.getShop().getUser().getId().equals(currentUserProvider.getCurrentUserId())
                && !repository.existsByUserIdAndShopIdAndStatus(
                currentUserProvider.getCurrentUserId(),
                employment.getShop().getId(),
                EmploymentStatus.ACTIVE)) {
            throw new NotFoundException("Quan hệ nhân viên-shop");
        }
        return mapper.toResponse(employment);
    }

    @Override
    public EmploymentResponse rehire(Long shopId, Long userId, EmploymentRehireRequest request) {
        ShopEntity shop = shopRepository.findById(shopId)
                .orElseThrow(() -> new NotFoundException("Shop"));
        //ở trên controller đã có check permission rồi
        //kiểm tra có phải chủ shop hoặc manager của shop không
        if (!shop.getUser().getId().equals(currentUserProvider.getCurrentUserId()) &&
                !repository.existsByUserIdAndShopIdAndStatus(
                        currentUserProvider.getCurrentUserId(),
                        shopId,
                        EmploymentStatus.ACTIVE)) {
            throw new NotFoundException("Quan hệ nhân viên-shop");
        }
        //kiểm tra xem đã có employment nào đang active của userId và shopId không
        if (repository.existsByUserIdAndShopIdAndStatus(userId, shopId, EmploymentStatus.ACTIVE)) {
            throw new ValidateException("Nhân viên này đang làm tại shop");
        }
        validateRehireRequest(request);
        //kiểm tra có roleId nào bị trùng trong request không
        Set<Long> roleIds = new HashSet<>(request.getRoleIds());
        if (roleIds.size() != request.getRoleIds().size()) {
            throw new ValidateException("Có vai trò bị trùng");
        }
        //kiểm tra có role nào mà không thuộc shop không
        Set<RoleEntity> roles = roleRepository.findAllByIdInAndShopId(roleIds, shopId);
        if (roles.size() != roleIds.size()) {
            throw new NotFoundException("Vai trò");
        }
        UserEntity user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("Nhân viên"));
        user.setRoles(roles);
        userRepository.save(user);
        EmploymentEntity employment = mapper.toRehireEntity(request);
        employment.setShop(shop);
        employment.setUser(user);
        return mapper.toResponse(repository.save(employment));
    }
}
