package com.woolf.project.product.services;

import com.woolf.project.product.dtos.StoreProductDTO;
import com.woolf.project.product.exceptions.ProductNotExistException;
import com.woolf.project.product.models.Category;
import com.woolf.project.product.models.Product;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpMessageConverterExtractor;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service("fakeStoreProductService")
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

        StoreProductDTO productDto = restTemplate.getForObject(
                "https://fakestoreapi.com/products/" + id,
                StoreProductDTO.class);

        if (productDto == null) {
            throw new ProductNotExistException(
                    "Product with id: " + id + " doesn't exist."
            );
        }

        return convertFakeStoreToProduct(productDto);

    }

    @Override
    public List<Product> getAllProducts() {
        StoreProductDTO[] response = restTemplate.getForObject(
                "https://fakestoreapi.com/products",
                StoreProductDTO[].class
        );


        List<Product> answer = new ArrayList<>();


        for (StoreProductDTO dto: response) {
            answer.add(convertFakeStoreToProduct(dto));
        }

        return answer;
    }

    @Override
    public Product updateProduct(Long id, Product product) {
        return null;
    }

    @Override
    public Product replaceProduct(Long id, Product product) {
        StoreProductDTO storeProductDTO = new StoreProductDTO();
        storeProductDTO.setTitle(product.getTitle());
        storeProductDTO.setPrice(product.getPrice());
        storeProductDTO.setImage(product.getDescription());
        storeProductDTO.setImage(product.getImageUrl());

        RequestCallback requestCallback = restTemplate.httpEntityCallback(storeProductDTO, StoreProductDTO.class);
        HttpMessageConverterExtractor<StoreProductDTO> responseExtractor =
                new HttpMessageConverterExtractor<>(StoreProductDTO.class, restTemplate.getMessageConverters());
        StoreProductDTO response = restTemplate.execute("https://fakestoreapi.com/products/" + id, HttpMethod.PUT, requestCallback, responseExtractor);

        return convertFakeStoreToProduct(response);
    }

    @Override
    public Product addNewProduct(Product product) {
        return null;
    }

    @Override
    public boolean deleteProduct(Long id) {
        return false;
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
