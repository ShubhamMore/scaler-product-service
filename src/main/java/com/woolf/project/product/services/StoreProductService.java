package com.woolf.project.product.services;

import com.woolf.project.product.dtos.StoreProductDTO;
import com.woolf.project.product.exceptions.ProductNotExistException;
import com.woolf.project.product.models.Category;
import com.woolf.project.product.models.Product;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
@Log4j2
public class StoreProductService implements ProductService {

    private RestTemplate restTemplate;

    @Autowired
    public StoreProductService(RestTemplate restTemplate){
        this.restTemplate = restTemplate;
    }

    //ToDo : Need to implement all other 7 APIs of fake store    16 oct, 23 oct, 25 oct
    @Override
    public Product getSingleProduct(Long id) throws ProductNotExistException {
        try {
            StoreProductDTO productDTO = restTemplate.getForObject(
                    "https://fakestoreapi.com/products/" + id,
                    StoreProductDTO.class);

            if (productDTO == null) {
                throw new ProductNotExistException(
                        "Product doesn't exist."
                );
            }
            
            return convertStoreToProduct(productDTO);
        }catch (Exception e){
            log.error("Some Exception occurred",e);
            return null;
        }
    }

    @Override
    public StoreProductDTO addNewProduct(StoreProductDTO product) {
        try {
            StoreProductDTO response = restTemplate.postForObject("https://fakestoreapi.com/products", product, StoreProductDTO.class);
            return response;
        }catch (Exception e){
            log.error("Some Exception occurred",e);
            return null;
        }
    }

    @Override
    public List<Product> getAllProducts() {
        StoreProductDTO[] response = restTemplate.getForObject(
                "https://fakestoreapi.com/products",
                StoreProductDTO[].class
        );

        List<Product> answer = new ArrayList<>();

        for (StoreProductDTO dto: response) {
            answer.add(convertStoreToProduct(dto));
        }

        return answer;
    }

    private Product convertStoreToProduct(StoreProductDTO StoreProductDTO ) {
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
