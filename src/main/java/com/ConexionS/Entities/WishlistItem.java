package com.ConexionS.Entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "wishlistItem")
public class WishlistItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_wishlist", nullable = false)
    private Wishlist wishlist;

    @ManyToOne
    @JoinColumn(name = "id_sneakers", nullable = false)
    private Sneakers sneakers;
}
