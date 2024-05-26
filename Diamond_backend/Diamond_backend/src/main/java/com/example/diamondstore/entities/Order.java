package com.example.diamondstore.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;



@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "[Order]")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "orderid")
    private Integer orderId;

    @Column(name = "customerid")
    private  Integer cid;

    @Column(name = "customer_name", nullable = false)
    private String cname;

    @Column(name = "phone_number", nullable = false, unique = true)
    private String phone;

    @Column(name = "address")
    private String address;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "order_date")
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private Date order_date;

    @Column(name = "status")
    private String status;

    @Column(name = "voucherid")
    private Integer voucher;

    @Column(name = "payment_amount")
    private float payment;

    @Column(name = "payment_date")
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private Date payment_date;

    @Column(name = "payment_method")
    private String payment_method;

    @Column(name = "delivery_date")
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private Date delivery;

    @Column(name = "delivery_staffid")
    private Integer deli_staff;





}
