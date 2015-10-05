package com.realdolmen.project1.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by JVDAX31 on 3/10/2015.
 */
@Entity
public class Flight implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    Location From;

    @ManyToOne
    Location To;
    Date departureTime;
    Date arrivalTime;
    int totalPlaces;
    int availablePlaces;
    Double pricePerSeat;

    public Flight(Location from, int availablePlaces, Location to, Date departureTime, Date arrivalTime, int totalPlaces, Double pricePerSeat) {
        From = from;
        this.availablePlaces = availablePlaces;
        To = to;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.totalPlaces = totalPlaces;
        this.pricePerSeat = pricePerSeat;
    }

    public Flight(Location from, int availablePlaces, Location to, Date departureTime, Date arrivalTime, int totalPlaces) {
        From = from;
        this.availablePlaces = availablePlaces;
        To = to;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.totalPlaces = totalPlaces;
    }

    protected Flight() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getAvailablePlaces() {
        return availablePlaces;
    }

    public void setAvailablePlaces(int availablePlaces) {
        this.availablePlaces = availablePlaces;
    }

    public Location getFrom() {
        return From;
    }

    public void setFrom(Location from) {
        From = from;
    }

    public Location getTo() {
        return To;
    }

    public void setTo(Location to) {
        To = to;
    }

    public Date getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(Date departureTime) {
        this.departureTime = departureTime;
    }

    public Date getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(Date arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public int getTotalPlaces() {
        return totalPlaces;
    }

    public void setTotalPlaces(int totalPlaces) {
        this.totalPlaces = totalPlaces;
    }

    public double getPricePerSeat() {
        return pricePerSeat;
    }

    public void setPricePerSeat(double pricePerSeat) {
        this.pricePerSeat = pricePerSeat;
    }
}
