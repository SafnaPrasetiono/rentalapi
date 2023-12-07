package com.rental.Models.Users;

import java.math.BigInteger;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "users")
public class UsersModels {
    @Id
    @Column(name = "id_user", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private BigInteger id_user;

    @Column(name = "email", nullable = false)
    private String email;
    @Column(name = "username", nullable = false)
    private String username;
    @Column(name = "password", nullable = false)
    private String password;
    @Column(name = "born", nullable = true)
    private String born;
    @Column(name = "address", nullable = true)
    private String address;
    @Column(name = "phone", nullable = true)
    private String phone;
    @Column(name = "avatar", nullable = true)
    private String avatar;
    
}
