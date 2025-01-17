package com.E_Commerce.API.User;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;

import com.E_Commerce.API.Cart.CartModel;
import com.E_Commerce.API.Order.OrderModel;

import jakarta.annotation.Generated;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String firstName;
    private String lastName;
    private String userName;

    private String email;
    private String password;
    private boolean isEmailVerified = false;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    private CartModel cart;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<OrderModel> orders;
}
