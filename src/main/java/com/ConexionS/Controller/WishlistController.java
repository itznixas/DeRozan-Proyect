package com.ConexionS.Controller;

import com.ConexionS.Entities.Wishlist;
import com.ConexionS.Entities.WishlistItem;
import com.ConexionS.Service.WishlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/wishlist")
public class WishlistController {

    @Autowired
    private WishlistService wishlistService;

    @PostMapping("/add-wishlist")
    public ResponseEntity<Wishlist> addProductToShoppingBag(@PathVariable Integer userId, @PathVariable Integer productId) {
        Wishlist saveWishlist = wishlistService.addProduct(userId, productId);
        return new ResponseEntity<>(saveWishlist, HttpStatus.CREATED);
    }

    @GetMapping("/get-wishlist/{userId}")
    public ResponseEntity<List<WishlistItem>> getWishlistById(@PathVariable Integer userId) {
        List<WishlistItem> wishlistItem = wishlistService.inWishlist(userId);
        return  new ResponseEntity<>(wishlistItem, HttpStatus.OK);
    }

    @DeleteMapping("/delete-wishlist/{itemid}")
    public ResponseEntity<Void>deleteProductInWishlist(@PathVariable Integer userId, @PathVariable Long itemid){
        wishlistService.deleteToWishlist(userId, itemid);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}