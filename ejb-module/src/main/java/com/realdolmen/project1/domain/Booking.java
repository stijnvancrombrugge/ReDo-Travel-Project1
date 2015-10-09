package com.realdolmen.project1.domain;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by SVCAX33 on 9/10/2015.
 */

@Entity
public class Booking implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Basic(optional = false)
    private double totalPrice;

    @Basic(optional = false)
    private int nrOfTrips;

    @Basic(optional = false)
    private PaymentType paymentType;

    @ManyToOne
    private Trip trip;

    protected Booking(){}

    public Booking(double totalPrice, int nrOfTrips, PaymentType paymentType, Trip trip){

        this.totalPrice = totalPrice;
        this.nrOfTrips = nrOfTrips;
        this.paymentType = paymentType;
        this.trip = trip;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getNrOfTrips() {
        return nrOfTrips;
    }

    public void setNrOfTrips(int nrOfTrips) {
        this.nrOfTrips = nrOfTrips;
    }

    public PaymentType getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(PaymentType paymentType) {
        this.paymentType = paymentType;
    }

    public Trip getTrip() {
        return trip;
    }

    public void setTrip(Trip trip) {
        this.trip = trip;
    }
}
