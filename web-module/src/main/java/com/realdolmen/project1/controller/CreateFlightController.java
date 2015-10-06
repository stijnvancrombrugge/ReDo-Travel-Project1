package com.realdolmen.project1.controller;

import com.realdolmen.project1.domain.Discount;
import com.realdolmen.project1.domain.Flight;
import com.realdolmen.project1.domain.Location;
import com.realdolmen.project1.persistence.FlightEJB;

import javax.ejb.EJB;
import javax.enterprise.context.ConversationScoped;
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
@ConversationScoped
public class CreateFlightController implements Serializable {

    @EJB
    private FlightEJB flightEJB;

    private Date departureDate;
    private Date arrivalDate;
    private int totalPlaces;
    private int freePlaces;
    private int from;
    private int to;
    private Double price;
    private List<Location> allLocations = new ArrayList<>();

    private List<Discount> discounts = new ArrayList<>();

    private int nbrToCell;
    private double percentage;

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



    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public double getPercentage() {
        return percentage;
    }

    public void setPercentage(double percentage) {
        this.percentage = percentage;
    }

    public int getNbrToCell() {
        return nbrToCell;
    }

    public void setNbrToCell(int nbrToCell) {
        this.nbrToCell = nbrToCell;
    }

    public List<Discount> getDiscounts() {
        return discounts;
    }

    public void setDiscounts(List<Discount> discounts) {
        this.discounts = discounts;
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
       // Flight f =  flightEJB.createFlight(departureDate, arrivalDate, totalPlaces, freePlaces, from, to, price);
        return "createFlightDiscount";
    }

    public String addDiscount(){
        Discount discount =  new Discount(nbrToCell,percentage);
        nbrToCell = 0;
        percentage = 0;
        discounts.add(discount);
        return "createFlightDiscount";


    }

    public String saveFlight(){
        Flight f =  flightEJB.createFlight(departureDate, arrivalDate, totalPlaces, freePlaces, from, to, price, discounts);
        return "allFlights";

    }


}
