package com.ConexionS.Entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "inventoryMovementDetail")
public class InventoryMovementDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id_movementDetail;

    @Column(name = "initialAmount", nullable = false)
    private Integer initialAmount;

    @Column(name = "quantityMoved", nullable = false)
    private Integer quantityMoved;

    @Column(name = "unitCost", nullable = false)
    private Double unitCost;

    @Column(name = "finalAmout", nullable = false)
    private Integer finalAmount;

    @Column(name = "finals", nullable = false)
    private Double finals;

    @Column(name = "dateMovementDetail", nullable = false)
    private Date dateMovementDetail;

    @ManyToOne
    @JoinColumn(name = "id_movement", nullable = false)
    private InventoryMovement inventoryMovement;

}
