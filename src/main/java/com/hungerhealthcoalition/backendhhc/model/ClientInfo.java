package com.hungerhealthcoalition.backendhhc.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "ClientInfo")
public class ClientInfo {

    //this will later be a foreign key to connect to the client login id
    @Id
    @Column(name = "id")
    private String clientID;

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
    //questioning these 3 still
    @Column(name = "goalType")
    private String goalType;

    @Column(name = "goalTarget")
    private int goalTarget;

    @Column(name = "currentGoal")
    private int currentGoal;

    public String getClientID() {
        return clientID;
    }

    public void setClientID(String clientID) {
        this.clientID = clientID;
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

    public int getGoalTarget() {
        return goalTarget;
    }

    public void setGoalTarget(int goalTarget) {
        this.goalTarget = goalTarget;
    }

    public String getGoalType() {
        return goalType;
    }

    public void setGoalType(String goalType) {
        this.goalType = goalType;
    }

    public int getCurrentGoal() {
        return currentGoal;
    }

    public void setCurrentGoal(int currentGoal) {
        this.currentGoal = currentGoal;
    }
}
