package com.mamlaka.paymentgatewayserver.database.repository;

import com.mamlaka.paymentgatewayserver.database.model.Wallet;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Julius
 */

@Repository
public interface WalletRepository extends CrudRepository<Wallet, Integer> {
    default Wallet findByIdOrError(Integer id) {
        return findById(id).orElse(null);
    } 
}