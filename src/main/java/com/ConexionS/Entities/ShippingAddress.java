package com.ConexionS.Entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "ShippingAddress")
public class ShippingAddress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id_sending;

    @Column(name = "country", nullable = false)
    private String country;

    @Column(name = "department", nullable = false)
    private String department;

    @Column(name = "municipality", nullable = false)
    private String municipality;

    @Column(name = "postalCode", nullable = false)
    private String postalCode;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "backupAddress", nullable = false)
    private String backupAddress;

    @ManyToOne
    @JoinColumn(name = "id_user", nullable = false)
    private Users users;

    @ManyToOne
    @JoinColumn(name = "id_orders", nullable = false)
    private Order order;
}
