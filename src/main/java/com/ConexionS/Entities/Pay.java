package com.ConexionS.Entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "pay")
public class Pay {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_pay;

    @Column(name = "total", nullable = false)
    private Double total;

    @Column(name = "payDay", nullable = false)
    private Date payDay;

    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "id_orders", nullable = false)
    private Order order;

    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "id_paymentMethod", nullable = false)
    private PaymentMethod paymentMethod;
}
