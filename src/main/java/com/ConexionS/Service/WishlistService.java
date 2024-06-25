package com.ConexionS.Service;

import com.ConexionS.Entities.*;
import com.ConexionS.Repository.SneakerRepository;
import com.ConexionS.Repository.UsersRepository;
import com.ConexionS.Repository.WishlistItemRepository;
import com.ConexionS.Repository.WishlistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WishlistService {

    @Autowired
    private WishlistRepository wishlistRepository;

    @Autowired
    private WishlistItemRepository wishlistItemRepository;

    @Autowired
    private SneakerRepository sneakerRepository;

    @Autowired
    private UsersRepository usersRepository;

    public Wishlist addProduct(Integer userId, Integer productId) {

        Users users = usersRepository.findById(userId).orElseThrow(() -> new RuntimeException("User don't exist."));
        Wishlist wishlist = wishlistRepository.findByUsers_id(userId).orElse(new Wishlist());
        wishlist.setUsers(users);

        Sneakers sneakers = sneakerRepository.findById(productId).orElseThrow(() ->new RuntimeException("Product don't exist."));
        WishlistItem item = new WishlistItem();
        item.setSneakers(sneakers);

        wishlist.getItems().add(item); //
        item.setWishlist(wishlist);

        wishlistRepository.save(wishlist);
        wishlistItemRepository.save(item);

        return wishlist;
    }

    public List<WishlistItem> inWishlist(Integer userId){
        Wishlist wishlist = wishlistRepository.findByUsers_id(userId).orElseThrow(() -> new RuntimeException("Wishlist don't exist."));
        return wishlist.getItems();
    }

    public void deleteToWishlist(Integer userId, Long itemId){
        Wishlist wishlist = wishlistRepository.findByUsers_id(userId).orElseThrow(()->new RuntimeException("Wishlist don't exist."));
        WishlistItem item = wishlistItemRepository.findById(itemId).orElseThrow(()-> new RuntimeException("Item don't exist."));
        wishlist.getItems().remove(item);
        wishlistItemRepository.delete(item);
        wishlistRepository.save( wishlist);
    }
}
