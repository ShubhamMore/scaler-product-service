package com.woolf.project.product.controllers;

import com.woolf.project.product.dto.CreateProductDTO;
import com.woolf.project.product.dto.ProductDTO;
import com.woolf.project.product.exceptions.InvalidDataException;
import com.woolf.project.product.exceptions.ProductNotExistException;
import com.woolf.project.product.models.product.Product;
import com.woolf.project.product.services.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    private ProductService productService;
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/product")
    public ResponseEntity<ProductDTO> crateProduct(@RequestBody CreateProductDTO createProductDTO) throws InvalidDataException {
        Product product = productService.createProduct(createProductDTO);
        return new ResponseEntity<>(ProductDTO.fromProduct(product), HttpStatus.CREATED);
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<ProductDTO> getProduct(@PathVariable long id) throws ProductNotExistException {
        Product product = productService.getProductById(id);
        return new ResponseEntity<>(ProductDTO.fromProduct(product), HttpStatus.OK);
    }

    @GetMapping("/product/all")
    public ResponseEntity<List<ProductDTO>> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        List<ProductDTO> productDtoList = new ArrayList<>();
        for (Product product : products) {
            productDtoList.add(ProductDTO.fromProduct(product));
        }
        return new ResponseEntity<>(productDtoList, HttpStatus.OK);
    }

    @PatchMapping("/product/{id}")
    public ResponseEntity<ProductDTO> updateProduct(@PathVariable long id,
                                                    @RequestBody Map<String, Object> updates) throws ProductNotExistException {
        Product product = productService.updateProduct(id, updates);
        return new ResponseEntity<>(ProductDTO.fromProduct(product), HttpStatus.OK);
    }
}