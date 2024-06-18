package com.ConexionS.Controller;

import com.ConexionS.Entities.PaymentMethod;
import com.ConexionS.Service.PaymentMethodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/payment-method")
public class PaymentMethodController {

    @Autowired
    private PaymentMethodService paymentMethodService;

    @PostMapping("/add-payment-method")
    public ResponseEntity<PaymentMethod> createPaymentMethod(@RequestBody PaymentMethod paymentMethod) {
        PaymentMethod savePaymentMethod = paymentMethodService.createPaymentMethod(paymentMethod);
        return new ResponseEntity<>(savePaymentMethod, HttpStatus.CREATED);
    }

    @GetMapping("/get-all-payment-method")
    public ResponseEntity<List<PaymentMethod>> getAllPaymentMethods() {
        List<PaymentMethod> paymentMethods = paymentMethodService.getAllPaymentMethods();
        return new ResponseEntity<>(paymentMethods, HttpStatus.OK);
    }

    @GetMapping("/get-payment-method/{id}")
    public ResponseEntity<PaymentMethod> getPaymentMethod(@PathVariable Integer id) {
        Optional<PaymentMethod> paymentMethod = paymentMethodService.getPaymentMethodById(id);

        return paymentMethod.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/update-payment-method/{id}")
    public ResponseEntity<PaymentMethod> updatePaymentMethod(@PathVariable Integer id, @RequestBody(required = false) PaymentMethod paymentMethodDetails) {
        Optional<PaymentMethod> paymentMethodOptional = paymentMethodService.getPaymentMethodById(id);

        if(paymentMethodOptional.isPresent()) {
            PaymentMethod paymentMethod = paymentMethodOptional.get();

            if (paymentMethodDetails != null && paymentMethodDetails.getName() != null && paymentMethodDetails.getStatus() != null && paymentMethodDetails.getModificationDate() != null) {
                paymentMethod.setName(paymentMethodDetails.getName());
                paymentMethod.setStatus(paymentMethodDetails.getStatus());
                paymentMethod.setModificationDate(paymentMethodDetails.getModificationDate());

                PaymentMethod updatePaymentMethod = paymentMethodService.updatePaymentMethod(paymentMethod);
                return new ResponseEntity<>(updatePaymentMethod, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete-payment-method/{id}")
    public ResponseEntity<Void> deletePaymentMethod(@PathVariable int id) {
        if(paymentMethodService.getPaymentMethodById(id).isPresent()) {
            paymentMethodService.deletePaymentMethodById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}