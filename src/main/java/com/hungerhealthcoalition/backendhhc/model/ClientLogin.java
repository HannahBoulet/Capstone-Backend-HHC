package com.hungerhealthcoalition.backendhhc.model;

import jakarta.persistence.*;

@Entity
@Table (name = "Login")
public class ClientLogin {

    @Id
    @Column(name = "ClientID")
    private String ClientID;


    @Column(name = "userName")
    private String userName;

    @Column(name = "password")
    private String password;



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


    public String getPassword() { return password; }
    public void setPassword(String password){
        this.password= password;
    }
}
