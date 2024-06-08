package com.ConexionS.Controller;

import com.ConexionS.Entities.ShoppingBag;
import com.ConexionS.Entities.ShoppingBagItem;
import com.ConexionS.Service.ShoppingBagService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@RestController
@RequestMapping("/api/shopping-bag")
public class ShoppingBagController {

    @Autowired
    private ShoppingBagService shoppingBagService;

    @PostMapping("/add-shopping-bag")
    public ResponseEntity<ShoppingBag> addProductToShoppingBag(@PathVariable Integer userId,@RequestParam Integer productId) {
        ShoppingBag saveShoppingBag = shoppingBagService.addProduct(userId, productId);
        return new ResponseEntity<>(saveShoppingBag, HttpStatus.CREATED);
    }

    @GetMapping("/get-shopping-bag/{userId}")
    public ResponseEntity<List<ShoppingBagItem>> getShoppingBagById(@PathVariable Integer userId) {
        List<ShoppingBagItem> shoppingBagItem = shoppingBagService.inShoppingBag(userId);
        return  new ResponseEntity<>(shoppingBagItem, HttpStatus.OK);
    }

    @DeleteMapping("/delete-shopping-bag/{itemid}")
    public ResponseEntity<Void>deleteProductInShoppingBag(@PathVariable Integer userId, @RequestParam Long itemid){
        shoppingBagService.deleteToShoppingBag(userId, itemid);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}