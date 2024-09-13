
package com.mamlaka.paymentgatewayserver.rest.model.controller.util;

import com.mamlaka.paymentgatewayserver.database.model.Client;
import com.mamlaka.paymentgatewayserver.database.model.ClientStatus;
import com.mamlaka.paymentgatewayserver.database.service.ClientService;
import com.mamlaka.paymentgatewayserver.rest.model.MAMClient;
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
            mAMClient.setCreatedAt(client.getCreatedAt().toString());
            mAMClient.setClientStatus(client.getStatus());
        }
        
        return mAMClient;
    }
}
