package com.uit.customer_service.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "customers")
@Data
public class Customer extends BaseEntity {
    private String username;
    private String email;
    private String password;
    private String address;
    private String phone;
}
