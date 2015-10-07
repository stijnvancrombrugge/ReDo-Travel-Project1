package com.realdolmen.project1.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by JVDAX31 on 5/10/2015.
 */
@Entity
public class Trip implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @ManyToOne
    private Location From;

    @ManyToOne
    private Location Destination;

    private Date departureDate;
    private Date returnDate;
    private String Description;
    private Double pricePerDay;
    private String picturename;
    private int totalPlaces;
    private int availablePlaces;

    @ManyToMany(mappedBy = "trips", cascade = {CascadeType.PERSIST}, fetch = FetchType.EAGER)
    private List<Flight> flights = new ArrayList<>();

    protected Trip(){

    }


    public Trip(Location from, Location destination, Double pricePerDay, Date departureDate, Date returnDate, String description, int totalPlaces, String picturename, int availablePlaces) {
        From = from;
        Destination = destination;
        this.pricePerDay = pricePerDay;
        this.departureDate = departureDate;
        this.returnDate = returnDate;
        Description = description;
        this.totalPlaces = totalPlaces;
        this.picturename = picturename;
        this.availablePlaces = availablePlaces;
    }

    public void addFlight(Flight flight){
        flights.add(flight);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Location getFrom() {
        return From;
    }

    public void setFrom(Location from) {
        From = from;
    }

    public Location getDestination() {
        return Destination;
    }

    public void setDestination(Location destination) {
        Destination = destination;
    }

    public Date getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(Date departureDate) {
        this.departureDate = departureDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public Double getPricePerDay() {
        return pricePerDay;
    }

    public void setPricePerDay(Double pricePerDay) {
        this.pricePerDay = pricePerDay;
    }

    public String getPicturename() {
        return picturename;
    }

    public void setPicturename(String picturename) {
        this.picturename = picturename;
    }

    public int getTotalPlaces() {
        return totalPlaces;
    }

    public void setTotalPlaces(int totalPlaces) {
        this.totalPlaces = totalPlaces;
    }

    public int getAvailablePlaces() {
        return availablePlaces;
    }

    public void setAvailablePlaces(int availablePlaces) {
        this.availablePlaces = availablePlaces;
    }

    public List<Flight> getFlights() {
        return flights;
    }

    public void setFlights(List<Flight> flights) {
        this.flights = flights;
    }


}
