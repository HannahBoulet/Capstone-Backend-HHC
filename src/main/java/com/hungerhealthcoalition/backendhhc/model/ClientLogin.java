package com.hungerhealthcoalition.backendhhc.model;

import jakarta.persistence.*;

@Entity
@Table (name = "Login")
public class ClientLogin {

    @Id
    @Column(name = "Client")
    private String ClientID;


    @Id
    @Column(name = "userName")
    private String userName;

    @Id
    @Column(name = "password")
    private String clientPassword;


    public String getClientID() {
        return ClientID;
    }

    public void setClientID(String ClientID) {
        this.ClientID = ClientID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }


    public String getClientPassword() { return clientPassword; }
    public void setClientPassword(String clientPassword){
        this.clientPassword= clientPassword;
    }
}
