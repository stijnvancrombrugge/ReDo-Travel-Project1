package com.realdolmen.project1.controller;

import com.realdolmen.project1.domain.Flight;
import com.realdolmen.project1.domain.Location;
import com.realdolmen.project1.persistence.FlightEJB;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by JVDAX31 on 7/10/2015.
 */

@Named
@SessionScoped
public class EditFlightController implements Serializable{

    @EJB
    private FlightEJB flightEJB;

    private Flight flight;
    private int from;
    private int to;
    private List<Location> allLocations = new ArrayList<>();


    public String editFlight(int id){
        flight = flightEJB.findFlightById(id);
        allLocations = flightEJB.findAllLocations();
        return "editFlight";
    }

    public String updateFlight(){
        flightEJB.updateFlight(flight, from, to);
        return "allFlights";
    }


    public FlightEJB getFlightEJB() {
        return flightEJB;
    }

    public void setFlightEJB(FlightEJB flightEJB) {
        this.flightEJB = flightEJB;
    }

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
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
}
