package com.example.SmartRestaurant.service.hostdevice;

import com.example.SmartRestaurant.config.jwt.JwtService;
import com.example.SmartRestaurant.dto.request.HostDeviceRequest;
import com.example.SmartRestaurant.dto.response.HostDeviceResponse;
import com.example.SmartRestaurant.entity.HostDeviceEntity;
import com.example.SmartRestaurant.entity.ShopEntity;
import com.example.SmartRestaurant.exception.NotFoundException;
import com.example.SmartRestaurant.mapper.HostDeviceMapper;
import com.example.SmartRestaurant.repository.HostDeviceRepository;
import com.example.SmartRestaurant.repository.ShopRepository;
import com.example.SmartRestaurant.service.authorization.AuthorizationService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@Transactional
public class HostDeviceServiceImplement implements HostDeviceService {
    HostDeviceRepository repository;
    AuthorizationService authorizationService;
    ShopRepository shopRepository;

    HostDeviceMapper mapper;

    JwtService jwtService;

    @Override
    public HostDeviceResponse create(Long shopId, HostDeviceRequest request) {
        ShopEntity shop = shopRepository.findById(shopId)
                .orElseThrow(() -> new NotFoundException("Cửa hàng"));
        authorizationService.checkOwnerOrPermissionInShop(shop, "PERM_HOST_DEVICE_REGISTER");
        HostDeviceEntity hostDevice = repository.findByShop_Id(shopId);
        if (hostDevice != null) {
            repository.delete(hostDevice);
            repository.flush();
        }
        HostDeviceEntity newHostDevice = mapper.toEntity(request);
        newHostDevice.setShop(shop);
        HostDeviceEntity savedHostDevice = repository.save(newHostDevice);
        String token = jwtService.generateHostDeviceRefreshToken(savedHostDevice.getId(),
                savedHostDevice.getName(),
                shopId);
        savedHostDevice.setToken(token);
        return mapper.toResponse(repository.save(savedHostDevice));
    }
}
