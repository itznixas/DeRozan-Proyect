package com.ConexionS.Controller;

import com.ConexionS.Entities.Pay;
import com.ConexionS.Service.PayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/pay")
public class PayController {

    @Autowired
    private PayService payService;

    @PostMapping("/add-pay")
    public ResponseEntity<Pay> createPay(@RequestBody Pay pay) {
        Pay savePay = payService.createPay(pay);
        return new ResponseEntity<>(savePay, HttpStatus.CREATED);
    }

    @GetMapping("/get-all-pays")
    public ResponseEntity<List<Pay>> getAllPay() {
        List<Pay> pays = payService.getAllPays();
        return new ResponseEntity<>(pays, HttpStatus.OK);
    }

    @GetMapping("/get-pay/{id}")
    public ResponseEntity<Pay> getPayById(@PathVariable int id) {
        Optional<Pay> pay = payService.getPayById(id);

        return pay.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/update-pay/{id}")
    public ResponseEntity<Pay> updatePay(@PathVariable int id, @RequestBody(required = false) Pay payDetails) {
        Optional<Pay> payOptional = payService.getPayById(id);

        if(payOptional.isPresent()) {
            Pay pay = payOptional.get();

            if (payDetails != null && payDetails.getTotal() != null && payDetails.getOrder() != null && payDetails.getPayDay() != null && payDetails.getPaymentMethod() != null) {
                pay.setTotal(payDetails.getTotal());
                pay.setOrder(payDetails.getOrder());
                pay.setPayDay(payDetails.getPayDay());
                pay.setPaymentMethod(payDetails.getPaymentMethod());

                Pay updatePay = payService.updatePay(pay);
                return new ResponseEntity<>(updatePay, HttpStatus.OK);
            } else {
                // Handle case where brandDetails or brandDetails.getName() is null
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete-pay/{id}")
    public ResponseEntity<Void> deletePay(@PathVariable int id) {
        if(payService.getPayById(id).isPresent()) {
            payService.deletePayById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}