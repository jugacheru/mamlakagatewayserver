package com.mamlaka.paymentgatewayserver.database.repository;

import com.mamlaka.paymentgatewayserver.database.model.Client;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Julius
 */

@Repository
public interface ClientRepository extends CrudRepository<Client, Integer> {
    default Client findByIdOrError(Integer id) {
        return findById(id).orElse(null);
    } 
}