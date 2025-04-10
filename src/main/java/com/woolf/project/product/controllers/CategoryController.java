package com.woolf.project.product.controllers;

import com.woolf.project.product.dto.CategoryDTO;
import com.woolf.project.product.enums.Roles;
import com.woolf.project.product.exceptions.ResourceAccessForbiddenException;
import com.woolf.project.product.models.User;
import com.woolf.project.product.repositories.UserRepository;
import com.woolf.project.product.services.CategoryService;
import com.woolf.project.product.utils.UserUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("categories")
public class CategoryController {


    private final CategoryService categoryService;
    private UserRepository userRepository;

    public CategoryController(CategoryService categoryService,UserRepository userRepository) {
        this.categoryService = categoryService;
        this.userRepository = userRepository;
    }


    @PostMapping
    public ResponseEntity<CategoryDTO> createCategory(Authentication authentication,  @RequestBody CategoryDTO categoryDTO) throws ResourceAccessForbiddenException {

        Jwt jwt = ((JwtAuthenticationToken) authentication).getToken();
        User user = UserUtils.createUserIfNotExist(jwt, userRepository);

        if (!(user.getRoles().contains(Roles.ADMIN.name()) || user.getRoles().contains(Roles.SUPER_ADMIN.name()))) {
            throw new ResourceAccessForbiddenException("No Access to create category");
        }

        CategoryDTO createdCategory = categoryService.createCategory(categoryDTO);
        return ResponseEntity.ok(createdCategory);
    }


    @GetMapping
    public ResponseEntity<List<CategoryDTO>> getAllCategories() {
        List<CategoryDTO> categories = categoryService.getAllCategories();
        return ResponseEntity.ok(categories);
    }


    @GetMapping("/{id}")
    public ResponseEntity<CategoryDTO> getCategoryById(@PathVariable Long id) {
        Optional<CategoryDTO> categoryDTO = categoryService.getCategoryById(id);
        return ResponseEntity.ok(categoryDTO.get());
    }


    @PutMapping("/{id}")
    public ResponseEntity<CategoryDTO> updateCategory(Authentication authentication,
                                                      @PathVariable Long id,
                                                      @RequestBody CategoryDTO categoryDTO) throws ResourceAccessForbiddenException {

        Jwt jwt = ((JwtAuthenticationToken) authentication).getToken();
        User user = UserUtils.createUserIfNotExist(jwt, userRepository);

        if (!(user.getRoles().contains(Roles.ADMIN.name()) || user.getRoles().contains(Roles.SUPER_ADMIN.name()))) {
            throw new ResourceAccessForbiddenException("No Access to update category");
        }
        CategoryDTO updatedCategory = categoryService.updateCategory(id, categoryDTO);
        return ResponseEntity.ok(updatedCategory);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
        return ResponseEntity.noContent().build();
    }
}
