package com.ConexionS.Entities;

import lombok.*;
import jakarta.persistence.*;

import java.util.Date;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "inventoryMovement")
public class InventoryMovement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_movement;

    @ManyToOne
    @JoinColumn(name = "id_user", nullable = false)
    private  Users users;

    @ManyToOne
    @JoinColumn(name = "id_sneakers", nullable = false)
    private  Sneakers sneakers;

    @Column(name = "type", nullable = false)
    private String type;

    @Column(name = "initialAmount", nullable = false)
    private Integer initialAmount;

    @Column(name = "quantityMoved", nullable = false)
    private Integer quantityMoved;

    @Column(name = "finalAmout", nullable = false)
    private Integer finalAmount;

    @Column(name = "registration", nullable = false)
    private Date registration;


}
