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

    @Column(name = "time", unique = true, nullable = false)
    private Date time;

    @Column(name = "descriptionMovement", nullable = false)
    private String descriptionMovement;

    @Column(name = "movement", nullable = false)
    private String movement;

    @Column(name = "type", nullable = false)
    private String type;

    @Column(name = "registration", nullable = false)
    private Date registration;

    @ManyToOne
    @JoinColumn(name = "id_user", nullable = false)
    private  Users users;

    @ManyToOne
    @JoinColumn(name = "id_sneakers", nullable = false)
    private  Sneakers sneakers;

    @OneToMany(mappedBy = "id_movement")
    private Set<InventoryMovement> inventoryMovement;

}
