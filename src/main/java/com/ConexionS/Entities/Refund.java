package com.ConexionS.Entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "refund")
public class Refund {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Integer id_refund;

    @Column(name = "reason", nullable = false)
    private String reason;

    @Column(name = "dataOrder", nullable = false)
    private Date dataOrder;

    @Column(name = "status", nullable = false)
    private String status;

    @ManyToOne
    @JoinColumn(name = "id_orders", nullable = false)
    private Order order;

    @ManyToOne
    @JoinColumn(name = "id_user", nullable = false)
    private Users users;
}
