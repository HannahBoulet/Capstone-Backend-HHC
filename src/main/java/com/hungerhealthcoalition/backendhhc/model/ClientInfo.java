package com.hungerhealthcoalition.backendhhc.model;


import com.hungerhealthcoalition.backendhhc.util.PasswordHashing;
import jakarta.persistence.*;

@Entity
@Table(name = "ClientInfo")
public class ClientInfo {

    //this will later be a foreign key to connect to the client login id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-increment primary key
    @Column(name = "id")
    private int clientID;

    @Column(name = "userName")
    private String userName;
    @Column(name = "password")
    private String password;

    @Column(name = "clientFirst")
    private String clientFirst;

    @Column(name = "clientLast")
    private String clientLast;

    @Column(name = "foodBox")
    private Boolean foodBox;
    @Column(name = "medication")
    private Boolean medications;

    @Column(name = "clientPicture")
    private String clientPicture;

    public int getClientID() {
        return clientID;
    }

    public void setClientID(int clientID) {
        this.clientID = clientID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getClientFirst() {
        return clientFirst;
    }

    public void setClientFirst(String clientFirst) {
        this.clientFirst = clientFirst;
    }

    public String getClientLast() {
        return clientLast;
    }

    public void setClientLast(String clientLast) {
        this.clientLast = clientLast;
    }

    public Boolean getFoodBox() {
        return foodBox;
    }

    public void setFoodBox(Boolean foodBox) {
        this.foodBox = foodBox;
    }

    public Boolean getMedications() {
        return medications;
    }

    public void setMedications(Boolean medications) {
        this.medications = medications;
    }

    public String getClientPicture() {
        return clientPicture;
    }

    public void setClientPicture(String clientPicture) {
        this.clientPicture = clientPicture;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        PasswordHashing pw = new PasswordHashing(password);
        this.password = pw.getPassword();
    }
}
