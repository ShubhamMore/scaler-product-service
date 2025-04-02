package com.woolf.project.product.services;

import com.woolf.project.product.dtos.StoreProductDTO;
import com.woolf.project.product.exceptions.ProductNotExistException;
import com.woolf.project.product.models.Product;

import java.util.List;

public interface ProductService {

    Product getSingleProduct(Long id) throws ProductNotExistException;

    StoreProductDTO addNewProduct(StoreProductDTO product);

    List<Product> getAllProducts();
}
