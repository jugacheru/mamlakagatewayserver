
package com.mamlaka.paymentgatewayserver.rest.model;

import com.mamlaka.paymentgatewayserver.database.model.Status;

/**
 *
 * @author Julius
 */
public class MAMClient {
    private int clientID;
    private String names;
    private String phone;
    private String nationalID;
    private String createdAt;
    private Status clientStatus;

    public void setClientID(int clientID){
        this.clientID = clientID;
    }
    
    public int getClientID(){
        return clientID;
    }
    
    public String getNames() {
        return names;
    }

    public void setNames(String names) {
        this.names = names;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getNationalID() {
        return nationalID;
    }

    public void setNationalID(String nationalID) {
        this.nationalID = nationalID;
    }
    
    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }
    
    public Status getClientStatus() {
        return clientStatus;
    }

    public void setClientStatus(Status clientStatus) {
        this.clientStatus = clientStatus;
    }
}
