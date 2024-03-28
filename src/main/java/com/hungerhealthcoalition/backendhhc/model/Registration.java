
package com.hungerhealthcoalition.backendhhc.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Registration")
public class Registration {

    @Id
    @GeneratedValue
    @Column(name = "registrationID")
    private int registrationID;


    @Column(name = "eventID")
    private int eventID;



    @Column(name = "id")
    private int id;

//    @ManyToOne
//    @JoinColumn(name = "id", referencedColumnName = "id")
//    private ClientInfo clientInfo;
//
//    @ManyToOne
//    @JoinColumn(name = "eventID", referencedColumnName = "eventID")
//    private Events events;



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEventID() {
        return eventID;
    }

    public void setEventID(int eventID) {
        this.eventID = eventID;
    }

    public int getRegistrationID() {
        return registrationID;
    }

    public void setRegistrationID(int registrationID) {
        this.registrationID = registrationID;
    }

}

