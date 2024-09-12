
package com.mamlaka.paymentgatewayserver.database.service;

import com.mamlaka.paymentgatewayserver.database.model.Client;
import com.mamlaka.paymentgatewayserver.database.repository.ClientRepository;
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
public class ClientService {
    
    @Autowired
    private ClientRepository repo;
    
    public List<Client> listAll() {
        return (List<Client>) repo.findAll();
    }
     
    public void save(Client client) {
        repo.save(client);
    }
     
    public Client get(Integer id) {
        return repo.findByIdOrError(id);
    }
     
    public void delete(Integer id) {
        repo.deleteById(id);
    }
}