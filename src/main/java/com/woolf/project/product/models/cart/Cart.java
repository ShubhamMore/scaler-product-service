package com.woolf.project.product.models.cart;

import com.woolf.project.product.models.BaseModel;
import com.woolf.project.product.models.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
public class Cart extends BaseModel {
    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CartItem> products;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    private double totalPrice;
}
