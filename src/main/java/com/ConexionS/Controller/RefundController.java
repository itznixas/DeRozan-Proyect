package com.ConexionS.Controller;

import com.ConexionS.Entities.Refund;
import com.ConexionS.Service.RefundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/refunds")
public class RefundController {

    @Autowired
    private RefundService refundService;

    @PostMapping("/add-refund")
    public ResponseEntity<Refund> createRefund(@RequestBody Refund refund) {
        Refund saveRefund = refundService.createRefund(refund);
        return new ResponseEntity<>(saveRefund, HttpStatus.CREATED);
    }

    @GetMapping("get-all-refunds")
    public ResponseEntity<List<Refund>> getAllRefunds() {
        List<Refund> refunds = refundService.getAllRefunds();
        return new ResponseEntity<>(refunds, HttpStatus.OK);
    }

    @GetMapping("/get-refund/{id}")
    public ResponseEntity<Refund> getRefundById(@PathVariable int id) {
        Optional<Refund> refund = refundService.getRefundById(id);

        return refund.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/update-refund/{id}")
    public ResponseEntity<Refund> updateRefund(@PathVariable int id, @RequestBody Refund refundDetails) {
        Optional<Refund> refundOptional = refundService.getRefundById(id);

        if (refundOptional.isPresent()) {
            Refund refund = refundOptional.get();

            if (refundDetails != null && refundDetails.getStatus() != null && refundDetails.getReason() != null) {
                refund.setStatus(refundDetails.getStatus());
                refund.setReason(refundDetails.getReason());

                Refund updateRefund = refundService.updateRefund(refund);
                return new ResponseEntity<>(updateRefund, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
