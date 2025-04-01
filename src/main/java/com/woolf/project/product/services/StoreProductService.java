package com.woolf.project.product.services;

import com.woolf.project.product.dtos.StoreProductDTO;
import com.woolf.project.product.models.Category;
import com.woolf.project.product.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class StoreProductService implements ProductService {

    private RestTemplate restTemplate;

    @Autowired
    public StoreProductService(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    //ToDo : Need to implement all other 7 APIs of fake store    16 oct, 23 oct, 25 oct
    @Override
    public Product getSingleProduct(Long id) {
        StoreProductDTO response = restTemplate.getForObject(
                "https://fakestoreapi.com/products/"+id,
                StoreProductDTO.class);

        return convertFakeStoreToProduct(response);
    }

    @Override
    public StoreProductDTO addNewProduct(StoreProductDTO product) {
        StoreProductDTO response = restTemplate.postForObject("https://fakestoreapi.com/products",product,StoreProductDTO.class);
        return response;
    }

    @Override
    public List<Product> getAllProducts() {
        //Todo Need add Product [].Class instead List.Class
        List<Product> productList = restTemplate.getForObject("https://fakestoreapi.com/products", List.class);
        return productList;
    }

    private Product convertFakeStoreToProduct(StoreProductDTO StoreProductDTO ) {
        Product product = new Product();
        product.setId(StoreProductDTO.getId());
        product.setTitle(StoreProductDTO.getTitle());
        product.setPrice(StoreProductDTO.getPrice());
        product.setDescription(StoreProductDTO.getDescription());
        product.setCategory(new Category());
        product.getCategory().setName(StoreProductDTO.getCategory());
        product.setImageUrl(StoreProductDTO.getImage());
        return product;
    }
}
