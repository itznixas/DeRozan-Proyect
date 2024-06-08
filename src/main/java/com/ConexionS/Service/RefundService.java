package com.ConexionS.Service;

import com.ConexionS.Entities.Refund;
import com.ConexionS.Repository.RefundRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RefundService {

    @Autowired
    private RefundRepository refundRepository;

    public Refund createRefund(Refund refund) {
        return refundRepository.save(refund);
    }

    public List<Refund> getAllRefunds() {
        return refundRepository.findAll();
    }

    public Optional<Refund> getRefundById(int id) {
        return refundRepository.findById(id);
    }

    public Refund updateRefund(Refund refund) {
        return refundRepository.save(refund);
    }

    public void deleteRefund(int id) {
        refundRepository.deleteById(id);
    }
}
