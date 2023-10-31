package com.uit.order_service.entity;

import java.util.LinkedHashSet;
import java.util.Set;

import com.uit.order_service.util.OrderStatus;
import jakarta.persistence.*;
import lombok.Data;


@Entity
@Table(name="oders")

@Data
public class Order extends BaseEntity {

    @Column(name="customer_id")
    private  String CustomerId=null;
    private String status=OrderStatus.PENDING.toString();
    private String price;
    @OneToMany(mappedBy = "order")
    @Column(name="setOrderItem")
    private Set<OrderItem> setOrderItem=new LinkedHashSet<>();


}
