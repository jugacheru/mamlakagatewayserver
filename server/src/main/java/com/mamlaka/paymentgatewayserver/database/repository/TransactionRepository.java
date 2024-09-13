package com.mamlaka.paymentgatewayserver.database.repository;

import com.mamlaka.paymentgatewayserver.database.model.Transaction;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Julius
 */

@Repository
public interface TransactionRepository extends CrudRepository<Transaction, Integer> {
    default Transaction findByIdOrError(Integer id) {
        return findById(id).orElse(null);
    } 
}