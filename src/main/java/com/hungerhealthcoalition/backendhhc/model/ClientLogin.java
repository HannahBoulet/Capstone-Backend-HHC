package com.hungerhealthcoalition.backendhhc.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Login")
public class ClientLogin {

    @Id
    @Column(name = "id")
    private String id;


    @Column(name = "userName")
    private String userName;

    @Column(name = "password")
    private String password;


    public String getid() {
        return id;
    }

    public void setid(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
