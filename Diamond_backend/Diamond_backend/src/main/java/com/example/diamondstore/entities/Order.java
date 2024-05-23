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
@Table(name = "Orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "OrderID")
    private Integer orderId;

    @Column(name = "CustomerID")
    private  Integer cid;

    @Column(name = "CustomerName", nullable = false)
    private String cname;

    @Column(name = "PhoneNumber", nullable = false, unique = true)
    private String phone;

    @Column(name = "Address")
    private String address;

    @Column(name = "Email", nullable = false, unique = true)
    private String Email;

    @Column(name = "OrderDate")
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private Date order_date;

    @Column(name = "Status")
    private String status;

    @Column(name = "Voucherid")
    private String voucher;

    @Column(name = "PaymentAmount")
    private float payment;

    @Column(name = "PaymentDate")
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private Date payment_date;

    @Column(name = "PaymentMethod")
    private String payment_method;

    @Column(name = "DeliveryDate")
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private Date delivery;

    @Column(name = "DeliveryStaffID")
    private Integer deli_staff;





}
