
package com.mamlaka.paymentgatewayserver.database.service;

import com.mamlaka.paymentgatewayserver.database.model.Wallet;
import com.mamlaka.paymentgatewayserver.database.repository.WalletRepository;
import jakarta.transaction.Transactional;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Julius
 */

@Service
@Transactional
public class WalletService {
    
    @Autowired
    private WalletRepository repo;
    
    public List<Wallet> listAll() {
        return (List<Wallet>) repo.findAll();
    }
     
    public void save(Wallet wallet) {
        repo.save(wallet);
    }
     
    public Wallet get(Integer id) {
        return repo.findByIdOrError(id);
    }
     
    public void delete(Integer id) {
        repo.deleteById(id);
    }
}