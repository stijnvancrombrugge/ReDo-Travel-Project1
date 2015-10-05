package com.realdolmen.project1.domain;

import javax.persistence.*;
import java.util.List;

/**
 * Created by SVCAX33 on 5/10/2015.
 */

@Entity
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Basic(optional = false)
    private List<User> userList;

    @Basic(optional = false)
    private Trip trip;

    public Booking(List<User> user, Trip trip){
        this.userList = user;
        this.trip = trip;
    }

    protected Booking(){}

    public List<User> getUser() {
        return userList;
    }

    public void setUser(List<User> user) {
        this.userList = userList;
    }

    public Trip getTrip() {
        return trip;
    }

    public void setTrip(Trip trip) {
        this.trip = trip;
    }
}
