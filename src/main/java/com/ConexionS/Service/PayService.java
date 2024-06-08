package com.ConexionS.Service;

import com.ConexionS.Entities.Pay;
import com.ConexionS.Repository.PayRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PayService {

    @Autowired
    private PayRepository payRepository;

    public Pay createPay(Pay pay) {
        return payRepository.save(pay);
    }

    public List<Pay> getAllPays() {
        return payRepository.findAll();
    }

    public Optional<Pay> getPayById(Integer id) {
        return payRepository.findById(id);
    }

    public Pay updatePay(Pay pay) {
        return payRepository.save(pay);
    }

    public void deletePayById(Integer id) {
        payRepository.deleteById(id);
    }
}
