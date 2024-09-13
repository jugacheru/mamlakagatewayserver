
package com.mamlaka.paymentgatewayserver.rest.model.controller;

import com.mamlaka.paymentgatewayserver.database.model.ClientStatus;
import com.mamlaka.paymentgatewayserver.database.model.Wallet;
import com.mamlaka.paymentgatewayserver.database.model.WalletStatus;
import com.mamlaka.paymentgatewayserver.database.service.WalletService;
import com.mamlaka.paymentgatewayserver.rest.model.MAMClient;
import com.mamlaka.paymentgatewayserver.rest.model.MAMWallet;
import com.mamlaka.paymentgatewayserver.rest.model.controller.util.ClientControllerUtil;
import com.mamlaka.paymentgatewayserver.rest.model.controller.util.WalletControllerUtil;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
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
public class WalletController {
    
    @Autowired
    WalletService walletService;
    
    private final WalletControllerUtil walletControllerUtil = new WalletControllerUtil();
    
    //@PostMapping(path = "/client", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @GetMapping(path = "/wallet/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<MAMWallet> createWallet(@RequestBody Wallet wallet){
        
        MAMWallet mAMWallet = walletControllerUtil.createWallet(wallet, walletService);
        HttpStatusCode httpStatusCode = mAMWallet.getWalletStatus() == WalletStatus.WALLET_ACTIVATED ? HttpStatus.CREATED : HttpStatus.BAD_REQUEST;
        
        return new ResponseEntity<>(mAMWallet, httpStatusCode);
    }
    
    @GetMapping("/wallets")
    public ResponseEntity<List<MAMWallet>> getWallets(){
        
        List<MAMWallet> mAMWallets = walletControllerUtil.getWallets(walletService);
        HttpStatusCode httpStatusCode = mAMWallets != null ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        
        return new ResponseEntity<>(mAMWallets, httpStatusCode);
    }
    
    @GetMapping("/wallet")
    public ResponseEntity<MAMWallet> getWallet(@RequestParam(value = "walletid") int walletid){
        
        MAMWallet mAMWallet = walletControllerUtil.getWallet(walletid, walletService);
        HttpStatusCode httpStatusCode = mAMWallet != null ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        
        return new ResponseEntity<>(mAMWallet, httpStatusCode);
    }
}
