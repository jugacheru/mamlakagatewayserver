
package com.mamlaka.paymentgatewayserver.database.service;

import com.mamlaka.paymentgatewayserver.database.model.Transaction;
import com.mamlaka.paymentgatewayserver.database.repository.TransactionRepository;
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
public class TransactionService {
    
    @Autowired
    private TransactionRepository repo;
    
    public List<Transaction> listAll() {
        return (List<Transaction>) repo.findAll();
    }
     
    public void save(Transaction transaction) {
        repo.save(transaction);
    }
     
    public Transaction get(Integer id) {
        return repo.findByIdOrError(id);
    }
     
    public void delete(Integer id) {
        repo.deleteById(id);
    }
}