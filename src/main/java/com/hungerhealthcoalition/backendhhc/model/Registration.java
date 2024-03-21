
package com.hungerhealthcoalition.backendhhc.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Registration")
public class Registration {

    @Id
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



    public String getid() {
        return id;
    }

    public void setid(String id) {
        this.id = id;
    }

    public String getEventID() {
        return eventID;
    }

    public void setEventID(String eventID) {
        this.eventID = eventID;
    }



}

