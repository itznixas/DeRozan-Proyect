package com.ConexionS.Service;

import com.ConexionS.Entities.ShoppingBag;
import com.ConexionS.Entities.ShoppingBagItem;
import com.ConexionS.Entities.Sneakers;
import com.ConexionS.Entities.Users;
import com.ConexionS.Repository.ShoppingBagItemRepository;
import com.ConexionS.Repository.ShoppingBagRepository;
import com.ConexionS.Repository.SneakerRepository;
import com.ConexionS.Repository.UsersRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Data
@Service
public class ShoppingBagService {

    @Autowired
    private ShoppingBagRepository shoppingBagRepository;
    private ShoppingBagItemRepository shoppingBagItemRepository;
    private UsersRepository usersRepository;
    private SneakerRepository sneakerRepository;

    public ShoppingBag addProduct(Integer userId, Integer productId) {

        Users users = usersRepository.findById(userId).orElseThrow(() -> new RuntimeException("User don't exist."));
        ShoppingBag shoppingBag = shoppingBagRepository.findByUsers_id(userId).orElse(new ShoppingBag());
        shoppingBag.setUsers(users);

        Sneakers sneakers = sneakerRepository.findById(productId).orElseThrow(() ->new RuntimeException("Product don't exist."));
        ShoppingBagItem item = new ShoppingBagItem();
        item.setSneakers(sneakers);

        shoppingBag.getItems().add(item); //
        item.setShoppingBag(shoppingBag);

        shoppingBagRepository.save(shoppingBag);
        shoppingBagItemRepository.save(item);

        return shoppingBag;
    }

    public List<ShoppingBagItem> inShoppingBag(Integer userId){
        ShoppingBag shoppingBag = shoppingBagRepository.findByUsers_id(userId).orElseThrow(() -> new RuntimeException("Shopping Cart don't exist."));
        return shoppingBag.getItems();
    }

    public void deleteToShoppingBag(Integer userId, Long itemId){
        ShoppingBag shoppingBag = shoppingBagRepository.findByUsers_id(userId).orElseThrow(()->new RuntimeException("Shopping Cart don't exist."));
        ShoppingBagItem item = shoppingBagItemRepository.findById(itemId).orElseThrow(()-> new RuntimeException("Item don't exist."));
        shoppingBag.getItems().remove(item);
        shoppingBagItemRepository.delete(item);
        shoppingBagRepository.save( shoppingBag);
    }
}
