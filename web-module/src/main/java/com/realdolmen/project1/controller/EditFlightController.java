package com.realdolmen.project1.controller;

import com.realdolmen.project1.domain.Discount;
import com.realdolmen.project1.domain.Flight;
import com.realdolmen.project1.domain.Location;
import com.realdolmen.project1.persistence.FlightEJB;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by JVDAX31 on 7/10/2015.
 */

@Named
@SessionScoped
public class EditFlightController implements Serializable{

    @EJB
    private FlightEJB flightEJB;

    private Date departureMax;
    private Date arrivalMin;
    private Flight flight;
    private int from;
    private int to;
    private List<Location> allLocations = new ArrayList<>();
    private List<Discount> discounts = new ArrayList<>();
    private Discount discount;
    private double percentage;
    private int nbrToCell;

    private int flightID;

    public String editFlight(int id){
        flightID = id;
        flight = flightEJB.findFlightById(id);
        allLocations = flightEJB.findAllLocations();
        return "editFlight";
    }

    public String editDiscount(int id){
        discount = flightEJB.findDiscountById(id);
      //  allLocations = flightEJB.findAllLocations();
        return "editDiscounts";
    }

    public String updateFlight(){
        flightEJB.updateFlight(flight, from, to);
        return "editFlight";
    }

    public String updateDiscount(){
        discount.setPercentage(percentage * 0.01);
        flightEJB.updateDiscount(discount);
        flight = flightEJB.findFlightById(flight.getId());
        discounts = flight.getDiscounts();
        discount = null;
        percentage = 0;
        return "editDiscounts";
    }

    public String editDiscounts(){
      discounts = flight.getDiscounts();
      discount = null;

      return "editDiscounts";
    };

    public String addNewDiscount(){
        Discount discount =  new Discount(nbrToCell, (percentage * 0.01));
        nbrToCell = 0;
        percentage = 0;
        flight.addDiscount(discount);
        flightEJB.updateFlight(flight);
        flight = flightEJB.findFlightById(flight.getId());
        discount = null;
        discounts = flight.getDiscounts();
        return "editDiscounts";
    }

    public void onArrDateSelect(){
        departureMax = flight.getArrivalTime();
    }

    public void onDepDateSelect(){
        arrivalMin = flight.getDepartureTime();
    }


    public String endUpdate(){


        return "allFlights";
    }

    public boolean discountToEdit(){
        return discount != null;
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

    public List<Discount> getDiscounts() {
        return discounts;
    }

    public void setDiscounts(List<Discount> discounts) {
        this.discounts = discounts;
    }

    public Discount getDiscount() {
        return discount;
    }

    public void setDiscount(Discount discount) {
        this.discount = discount;
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

    public int getFlightID() {
        return flightID;
    }

    public void setFlightID(int flightID) {
        this.flightID = flightID;
    }

    public Date getDepartureMax() {
        return departureMax;
    }

    public void setDepartureMax(Date departureMax) {
        this.departureMax = departureMax;
    }

    public Date getArrivalMin() {
        return arrivalMin;
    }

    public void setArrivalMin(Date arrivalMin) {
        this.arrivalMin = arrivalMin;
    }
}
