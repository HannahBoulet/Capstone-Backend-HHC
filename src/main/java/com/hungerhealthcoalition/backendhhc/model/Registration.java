
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
    private String eventID;



    @Column(name = "id")
    private String id;

//    @ManyToOne
//    @JoinColumn(name = "id", referencedColumnName = "id")
//    private ClientInfo clientInfo;
//
//    @ManyToOne
//    @JoinColumn(name = "eventID", referencedColumnName = "eventID")
//    private Events events;



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEventID() {
        return eventID;
    }

    public void setEventID(String eventID) {
        this.eventID = eventID;
    }

    public int getRegistrationID() {
        return registrationID;
    }

    public void setRegistrationID(int registrationID) {
        this.registrationID = registrationID;
    }

}

