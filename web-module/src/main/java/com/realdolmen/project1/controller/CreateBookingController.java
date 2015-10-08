package com.realdolmen.project1.controller;

import com.realdolmen.project1.domain.Location;
import com.realdolmen.project1.domain.Trip;
import com.realdolmen.project1.persistence.TripEJB;

import javax.ejb.EJB;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by SVCAX33 on 7/10/2015.
 */

@Named
@ConversationScoped
public class CreateBookingController implements Serializable {

    @EJB
    private TripEJB tripEJB;

    @Inject
    private LoginController loginController;


    private String selectedDestinationName;
    private List<Location> allDestinations = new ArrayList<>();
    private Date arrivalDate;
    private Date departureDate;
    private int numberOfPersons;
    private List<Trip> possibleTrips;
    private Trip selectedTrip;

    public String startCreationTrip(){
        allDestinations = tripEJB.getAllDestinations();
        return "/searchTrip.xhtml";
    }

    public String proceedToNumberOfPersons(){
        return "/enterNumberOfPersons.xhtml";
    }

    public String proceedToTrips(){

        Location destination = tripEJB.getDestinationForName(selectedDestinationName);
        possibleTrips = tripEJB.getPossibleTrips(destination, departureDate, arrivalDate, numberOfPersons);
        System.out.println(possibleTrips);

        return "/bookingTripList.xhtml";
    }

    public String chooseTrip(int id){
        selectedTrip = tripEJB.getTripForID(id);
        if(loginController.getLoggedIn()){
            return "/secured/proceedBooking.xhtml";
        }
        else{
            return "/login.xhtml";
        }

    }

    public String proceedToOverview(){
        return "/secured/bookingOverview.xhtml";
    }


    public List<Location> getAllDestinations() {
        return allDestinations;
    }

    public void setAllDestinations(List<Location> allDestinations) {
        this.allDestinations = allDestinations;
    }

    public String getSelectedDestinationName() {
        return selectedDestinationName;
    }

    public void setSelectedDestinationName(String selectedDestinationName) {
        this.selectedDestinationName = selectedDestinationName;
    }

    public Date getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(Date arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public Date getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(Date departureDate) {
        this.departureDate = departureDate;
    }

    public int getNumberOfPersons() {
        return numberOfPersons;
    }

    public void setNumberOfPersons(int numberOfPersons) {
        this.numberOfPersons = numberOfPersons;
    }

    public List<Trip> getPossibleTrips() {
        return possibleTrips;
    }

    public void setPossibleTrips(List<Trip> possibleTrips) {
        this.possibleTrips = possibleTrips;
    }

    public Trip getSelectedTrip() {
        return selectedTrip;
    }
}
