package com.woolf.project.product.services;

import com.woolf.project.product.dtos.StoreProductDTO;
import com.woolf.project.product.models.Product;

import java.util.List;

public interface ProductService {

    Product getSingleProduct(Long id);

    StoreProductDTO addNewProduct(StoreProductDTO product);

    List<Product> getAllProducts();
}
