package com.woolf.project.product.services;

import com.woolf.project.product.dto.AddCartItemDTO;
import com.woolf.project.product.repositories.CartItemRepository;
import com.woolf.project.product.repositories.CartRepository;
import com.woolf.project.product.repositories.ProductRepository;
import org.springframework.stereotype.Service;

@Service
public class CartService {
    private CartRepository cartRepository;
    private CartItemRepository cartItemRepository;
    private ProductRepository productRepository;

    public CartService(CartRepository cartRepository, CartItemRepository cartItemRepository,
                       ProductRepository productRepository) {
        this.cartRepository = cartRepository;
        this.cartItemRepository = cartItemRepository;
        this.productRepository = productRepository;
    }

    public void addItemsToCart(Long userId, AddCartItemDTO addCartItemDTO) {

    }

    public void removeItemsFromCart(Long userId, AddCartItemDTO addCartItemDTO) {

    }

    public void clearCart(Long userId) {

    }

    public void getCartItems(Long userId) {

    }

}
