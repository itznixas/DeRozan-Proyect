package com.ConexionS.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "shoppingBagItem")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShoppingBagItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_shoppingBag", nullable = false)
    private ShoppingBag shoppingBag;

    @ManyToOne
    @JoinColumn(name = "id_sneakers", nullable = false)
    private Sneakers sneakers;

}
