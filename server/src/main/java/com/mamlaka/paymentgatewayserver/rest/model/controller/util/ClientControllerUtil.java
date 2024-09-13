
package com.mamlaka.paymentgatewayserver.rest.model.controller.util;

import com.mamlaka.paymentgatewayserver.database.model.Client;
import com.mamlaka.paymentgatewayserver.database.model.ClientStatus;
import com.mamlaka.paymentgatewayserver.database.service.ClientService;
import com.mamlaka.paymentgatewayserver.rest.model.MAMClient;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Julius
 */

public class ClientControllerUtil {
    private static final Logger logger = LoggerFactory.getLogger(ClientControllerUtil.class);

    public MAMClient createClient(Client client, ClientService clientService) {

        MAMClient mAMClient = new MAMClient();

        try {
            client.setStatus(ClientStatus.CLIENT_CREATED);
            clientService.save(client);

            mAMClient.setClientID(client.getId());
            mAMClient.setNames(client.getNames());
            mAMClient.setPhone(client.getPhone());
            mAMClient.setNationalID(client.getNationalID());
            mAMClient.setCreatedAt(client.getCreatedAt());
            mAMClient.setClientStatus(client.getStatus());
        } 
        catch (Exception e) {
            mAMClient.setClientStatus(ClientStatus.ERROR_CREATING_CLIENT);
            
            logger.error(e.getMessage());
        }

        return mAMClient;
    }
    
    public MAMClient getClient(int clientId, ClientService clientService) {
        MAMClient mAMClient = null;
        
        Client client = clientService.get(clientId);
        
        if(client != null){
            mAMClient = new MAMClient();
            mAMClient.setClientID(client.getId());
            mAMClient.setNames(client.getNames());
            mAMClient.setPhone(client.getPhone());
            mAMClient.setNationalID(client.getNationalID());
            mAMClient.setCreatedAt(client.getCreatedAt());
            mAMClient.setClientStatus(client.getStatus());
        }
        
        return mAMClient;
    }
    
    public List<MAMClient> getClients(ClientService clientService) {
        List<MAMClient> aMClients = null;
        
        List<Client> clients = clientService.listAll();
        
        if(clients != null){
            aMClients = new ArrayList<>();
            
            for(Client client: clients){
                MAMClient mAMClient = new MAMClient();
                mAMClient.setClientID(client.getId());
                mAMClient.setNames(client.getNames());
                mAMClient.setPhone(client.getPhone());
                mAMClient.setNationalID(client.getNationalID());
                mAMClient.setCreatedAt(client.getCreatedAt());
                mAMClient.setClientStatus(client.getStatus());
                
                aMClients.add(mAMClient);
            }
        }
        
        return aMClients;
    }
}
