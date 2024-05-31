package com.ConexionS.Entities;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_orders;

    @Column(name = "amount",  nullable = false)
    private Integer amount;

    @Column(name = "unitPrice",  nullable = false)
    private Double unitPrice;

    @Column(name = "total",  nullable = false)
    private Double total;

    @Column(name = "dataOrder",  nullable = false)
    private Integer dataOrder;

    @Column(name = "status", nullable = false)
    private String status;

    @ManyToOne
    @JoinColumn(name = "id_user", nullable = false)
    private  Users users;

    @ManyToOne
    @JoinColumn(name = "id_sneakers", nullable = false)
    private  Sneakers sneakers;

    @ManyToOne
    @JoinColumn(name = "id_shoppingBag", nullable = false, unique = true )
    private  ShoppingBag shoppingBag;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private Set<Pay> payments;
}
