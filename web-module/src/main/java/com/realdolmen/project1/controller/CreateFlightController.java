package com.realdolmen.project1.controller;

import com.realdolmen.project1.domain.Location;
import com.realdolmen.project1.persistence.FlightEJB;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by JVDAX31 on 5/10/2015.
 */

@Named
@SessionScoped
public class CreateFlightController implements Serializable {

    @EJB
    private FlightEJB flightEJB;

    private Date departureDate;
    private Date arrivalDate;
    private int totalPlaces;
    private int freePlaces;
    private int from;
    private int to;
    private List<Location> allLocations = new ArrayList<>();

    public FlightEJB getFlightEJB() {
        return flightEJB;
    }

    public void setFlightEJB(FlightEJB flightEJB) {
        this.flightEJB = flightEJB;
    }

    public Date getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(Date departureDate) {
        this.departureDate = departureDate;
    }

    public Date getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(Date arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public int getTotalPlaces() {
        return totalPlaces;
    }

    public void setTotalPlaces(int totalPlaces) {
        this.totalPlaces = totalPlaces;
    }

    public int getFreePlaces() {
        return freePlaces;
    }

    public void setFreePlaces(int freePlaces) {
        this.freePlaces = freePlaces;
    }

    public int getFrom() {
        return from;
    }

    public void setFrom(int from) {
        this.from = from;
    }




    public int getTo() {
        return to;
    }

    public void setTo(int to) {
        this.to = to;
    }

    public List<Location> getAllLocations() {
        return allLocations;
    }

    public void setAllLocations(List<Location> allLocations) {
        this.allLocations = allLocations;
    }

    public String startCreation(){
        allLocations = flightEJB.findAllLocations();
        return "createFlight";
    }

    public String createFlight(){
        System.out.println("flight created");
        System.out.println(from);
        System.out.println(to);
        System.out.println(arrivalDate);
        flightEJB.createFlight(departureDate, arrivalDate, totalPlaces, freePlaces, from, to);
        return "index.html";
    }


}
