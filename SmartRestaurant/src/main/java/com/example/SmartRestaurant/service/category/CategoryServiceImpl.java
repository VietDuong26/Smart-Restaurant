package com.example.SmartRestaurant.service.category;

import com.example.SmartRestaurant.config.userdetail.CustomUserDetails;
import com.example.SmartRestaurant.dto.request.CategoryRequest;
import com.example.SmartRestaurant.dto.response.CategoryResponse;
import com.example.SmartRestaurant.entity.CategoryEntity;
import com.example.SmartRestaurant.entity.ShopEntity;
import com.example.SmartRestaurant.exception.CategoryNotFoundException;
import com.example.SmartRestaurant.exception.CategoryNotFoundInShopException;
import com.example.SmartRestaurant.exception.DuplicateDataException;
import com.example.SmartRestaurant.exception.ShopNotFoundException;
import com.example.SmartRestaurant.mapper.CategoryMapper;
import com.example.SmartRestaurant.repository.CategoryRepository;
import com.example.SmartRestaurant.repository.ShopRepository;
import com.example.SmartRestaurant.util.validateownership.OwnerShipValidateService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CategoryServiceImpl implements CategoryService {
    OwnerShipValidateService ownerShipValidateService;

    CategoryRepository repository;
    CategoryMapper mapper;

    ShopRepository shopRepository;

    @Override
    public CategoryResponse create(CategoryRequest categoryRequest, CustomUserDetails userDetails) {
        ShopEntity shop = shopRepository.findById(categoryRequest.getShopId())
                .orElseThrow(ShopNotFoundException::new);
        //1 nếu là admin thì tạo category theo shop trong request
        //2 nếu không thì sẽ tạo theo shop thuộc sở hữu của manager đang đăng nhập
        boolean isAdmin = ownerShipValidateService.checkAdmin(userDetails);
        if (repository.existsByShopIdAndName(shop.getId(), categoryRequest.getName().toLowerCase())) {
            throw new DuplicateDataException("Danh mục");
        }
        CategoryEntity category = mapper.toEntity(categoryRequest);
        category.setCreatedAt(LocalDateTime.now());
        category.setShop(isAdmin
                ? shop
                : ownerShipValidateService.validateShopOwnership(shop, userDetails));
        return mapper.toResponse(repository.save(category));
    }

    @Override
    public CategoryResponse update(Long id, CategoryRequest categoryRequest, CustomUserDetails userDetails) {
        ShopEntity shop = shopRepository.findById(categoryRequest.getShopId())
                .orElseThrow(ShopNotFoundException::new);
        boolean isAdmin = ownerShipValidateService.checkAdmin(userDetails);
        if (!isAdmin) {
            ownerShipValidateService.validateShopOwnership(shop, userDetails);
        }
        CategoryEntity category = repository.findById(id)
                .orElseThrow(CategoryNotFoundException::new);
        if (!category.getShop().getId().equals(categoryRequest.getShopId())) {
            throw new CategoryNotFoundInShopException();
        }
        if (categoryRequest.getName() != null) {
            category.setName(categoryRequest.getName().isEmpty() ? null : categoryRequest.getName());
        }
        if (categoryRequest.getDescription() != null) {
            category.setDescription(categoryRequest.getDescription().isEmpty() ? null : categoryRequest.getDescription());
        }
        category.setUpdatedAt(LocalDateTime.now());
        return mapper.toResponse(repository.save(category));
    }

    @Override
    public void delete(Long id, CustomUserDetails userDetails) {

    }

    @Override
    public CategoryResponse getById(Long id, CustomUserDetails userDetails) {
        CategoryEntity category = repository.findById(id)
                .orElseThrow(CategoryNotFoundException::new);
        boolean isAdmin = ownerShipValidateService.checkAdmin(userDetails);
        if (!isAdmin) {
            ownerShipValidateService.validateShopOwnership(category.getShop(), userDetails);
        }
        return mapper.toResponse(category);
    }

    @Override
    public List<CategoryResponse> getAll(CustomUserDetails userDetails) {
        return null;
    }

    @Override
    public List<CategoryResponse> getAllByShopId(Long shopId, CustomUserDetails userDetails) {
        ShopEntity shop = shopRepository.findById(shopId)
                .orElseThrow(ShopNotFoundException::new);
        //1 nếu là admin thì có thể lấy theo shopId đó
        //2 nếu không phải admin, nếu đúng là chủ sở hữu thì sẽ trả về shop đó luôn, không phải thì bắn ra exception luôn
        boolean isAdmin = ownerShipValidateService.checkAdmin(userDetails);
        if (!isAdmin) {
            ownerShipValidateService.validateShopOwnership(shop, userDetails);
        }
        return repository.findAllByShopId(shopId).stream()
                .map(x -> mapper.toResponse(x)).collect(Collectors.toList());
    }
}
