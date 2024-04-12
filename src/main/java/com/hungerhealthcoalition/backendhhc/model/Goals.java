package com.hungerhealthcoalition.backendhhc.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Goals")
public class Goals {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-increment primary key
    @Column(name = "goalId")
    private int goalId;

    @ManyToOne
    @JoinColumn(name = "id", referencedColumnName = "id")
    private ClientInfo clientID;

    @Column(name = "goalName")
    private String goalName;


    @Column(name = "startValue")
    private int startValue;

    @Column(name = "currentValue")
    private int currentValue;

    @Column(name = "goalValue")
    private int goalValue;

    @Column(name = "goalDesc")
    private String goalDesc;


    public int getGoalId() {
        return goalId;
    }

    public void setGoalId(int goalId) {
        this.goalId = goalId;
    }

    public void setClientID(ClientInfo clientID) {
        this.clientID = clientID;
    }

    public ClientInfo getClientID() {
        return clientID;
    }

    public String getGoalName() {
        return goalName;
    }

    public void setGoalName(String goalName) {
        this.goalName = goalName;
    }

    public int getStartValue() {
        return startValue;
    }

    public void setStartValue(int startValue) {
        this.startValue = startValue;
    }

    public int getCurrentValue() {
        return currentValue;
    }

    public void setCurrentValue(int currentValue) {
        this.currentValue = currentValue;
    }

    public int getGoalValue() {
        return goalValue;
    }

    public void setGoalValue(int goalValue) {
        this.goalValue = goalValue;
    }

    public void setGoalDesc(String goalDesc) {
        this.goalDesc = goalDesc;
    }

    public String getGoalDesc() {
        return goalDesc;
    }
}

