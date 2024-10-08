
package com.mamlaka.paymentgatewayserver.rest.model.controller;

import com.mamlaka.paymentgatewayserver.database.model.Client;
import com.mamlaka.paymentgatewayserver.database.model.ClientStatus;
import com.mamlaka.paymentgatewayserver.database.service.ClientService;
import com.mamlaka.paymentgatewayserver.rest.model.MAMClient;
import com.mamlaka.paymentgatewayserver.rest.model.controller.util.ClientControllerUtil;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Julius
 */

@RestController
@RequestMapping("/api")
public class ClientController {
    
    @Autowired
    ClientService clientService;
    
    private final ClientControllerUtil clientControllerUtil = new ClientControllerUtil();
    
    //@PostMapping(path = "/client", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @GetMapping(path = "/client/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MAMClient> createClient(@RequestBody Client client){
        
        MAMClient mAMClient = clientControllerUtil.createClient(client, clientService);
        HttpStatusCode httpStatusCode = mAMClient.getClientStatus() == ClientStatus.CLIENT_CREATED ? HttpStatus.CREATED : HttpStatus.BAD_REQUEST;
        
        return new ResponseEntity<>(mAMClient, httpStatusCode);
    }
    
    @GetMapping("/clients")
    public ResponseEntity<List<MAMClient>> getClients(){
        
        List<MAMClient> mAMClients = clientControllerUtil.getClients(clientService);
        HttpStatusCode httpStatusCode = mAMClients != null ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        
        return new ResponseEntity<>(mAMClients, httpStatusCode);
    }
    
    @GetMapping("/client")
    public ResponseEntity<MAMClient> getClient(@RequestParam(value = "userid") int userId){
        
        MAMClient mAMClient = clientControllerUtil.getClient(userId, clientService);
        HttpStatusCode httpStatusCode = mAMClient != null ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        
        return new ResponseEntity<>(mAMClient, httpStatusCode);
    }
}
