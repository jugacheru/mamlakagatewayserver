
package com.mamlaka.paymentgatewayserver.rest.model.controller.util;

import com.mamlaka.paymentgatewayserver.database.model.Transaction;
import com.mamlaka.paymentgatewayserver.database.model.TransactionType;
import com.mamlaka.paymentgatewayserver.database.model.Wallet;
import com.mamlaka.paymentgatewayserver.database.model.WalletStatus;
import com.mamlaka.paymentgatewayserver.database.service.TransactionService;
import com.mamlaka.paymentgatewayserver.database.service.WalletService;
import com.mamlaka.paymentgatewayserver.rest.model.MAMWallet;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Julius
 */

public class WalletControllerUtil {
    private static final Logger logger = LoggerFactory.getLogger(WalletControllerUtil.class);

    public MAMWallet createWallet(Wallet wallet, WalletService walletService) {

        MAMWallet mAMWallet = new MAMWallet();

        try {
            wallet.setStatus(WalletStatus.WALLET_CREATED);
            walletService.save(wallet);

            mAMWallet.setWalletID(wallet.getId());
            mAMWallet.setClientName(wallet.getClient().getNames());
            mAMWallet.setPhone(wallet.getPhone());
            mAMWallet.setNationalID(wallet.getClient().getNationalID());
            mAMWallet.setCreatedAt(wallet.getCreatedAt());
            mAMWallet.setWalletStatus(wallet.getStatus());
        } 
        catch (Exception e) {
            mAMWallet.setWalletStatus(WalletStatus.ERROR_CREATING_WALLET);
            
            logger.error(e.getMessage());
        }

        return mAMWallet;
    }
    
    public MAMWallet getWallet(int walletId, WalletService walletService) {
        MAMWallet mAMWallet = null;
        
        Wallet wallet = walletService.get(walletId);
        
        if(wallet != null){
            mAMWallet = new MAMWallet();
            
            mAMWallet.setWalletID(wallet.getId());
            mAMWallet.setClientName(wallet.getClient().getNames());
            mAMWallet.setPhone(wallet.getPhone());
            mAMWallet.setBalance(wallet.getBalance());
            mAMWallet.setNationalID(wallet.getClient().getNationalID());
            mAMWallet.setCreatedAt(wallet.getCreatedAt());
            mAMWallet.setWalletStatus(wallet.getStatus());
        }
        
        return mAMWallet;
    }
    
    public List<MAMWallet> getWallets(WalletService walletService) {
        List<MAMWallet> mAMWallets = null;
        
        List<Wallet> wallets = walletService.listAll();
        
        if(wallets != null){
            mAMWallets = new ArrayList<>();
            
            for(Wallet wallet: wallets){
                MAMWallet mAMWallet = new MAMWallet();
                mAMWallet.setWalletID(wallet.getId());
                mAMWallet.setClientName(wallet.getClient().getNames());
                mAMWallet.setPhone(wallet.getPhone());
                mAMWallet.setBalance(wallet.getBalance());
                mAMWallet.setNationalID(wallet.getClient().getNationalID());
                mAMWallet.setCreatedAt(wallet.getCreatedAt());
                mAMWallet.setWalletStatus(wallet.getStatus());
                
                mAMWallets.add(mAMWallet);
            }
        }
        
        return mAMWallets;
    }
    
    public MAMWallet fundWallet(Transaction transaction, WalletService walletService, TransactionService transactionService) {
        MAMWallet mAMWallet = null;
        
        Wallet wallet = walletService.get(transaction.getWallet().getId());
        
        if(wallet != null){
            int newBalance = wallet.getBalance() + transaction.getAmount();
            
            wallet.setBalance(newBalance);
            walletService.save(wallet);
            
            transaction.setTransactionType(TransactionType.DEPOSIT);
            transactionService.save(transaction);
            
            mAMWallet = new MAMWallet();            
            mAMWallet.setWalletID(wallet.getId());
            mAMWallet.setClientName(wallet.getClient().getNames());
            mAMWallet.setPhone(wallet.getPhone());
            mAMWallet.setBalance(wallet.getBalance());
            mAMWallet.setNationalID(wallet.getClient().getNationalID());
            mAMWallet.setCreatedAt(wallet.getCreatedAt());
            mAMWallet.setWalletStatus(wallet.getStatus());
        }
        
        return mAMWallet;
    }
}
