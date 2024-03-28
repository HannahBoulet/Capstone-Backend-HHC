
package com.hungerhealthcoalition.backendhhc.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Registration")
public class Registration {

    @Id
    @GeneratedValue
    @Column(name = "registrationID")
    private int registrationID;


//    @Column(name = "eventID")
//    private int eventID;



//    @Column(name = "id")
//    private int id;
//
    @ManyToOne
    @JoinColumn(name = "id", referencedColumnName = "id")
    private ClientInfo clientInfo;

    @ManyToOne
    @JoinColumn(name = "eventID", referencedColumnName = "eventID")
    private Events events;



    public ClientInfo getClientInfo() {
        return clientInfo;
    }

    public void setClientInfo(ClientInfo clientInfo) {
        this.clientInfo = clientInfo;
    }


//    public void setId(int id) {
//        this.id = id;
//    }

    public Events getEvents() {
        return events;
    }

    public void setEvents(Events events) {
        this.events = events;
    }

    public int getRegistrationID() {
        return registrationID;
    }

    public void setRegistrationID(int registrationID) {
        this.registrationID = registrationID;
    }

}

