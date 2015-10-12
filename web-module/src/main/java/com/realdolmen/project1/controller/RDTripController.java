package com.realdolmen.project1.controller;

import com.realdolmen.project1.domain.Location;
import com.realdolmen.project1.domain.Trip;
import com.realdolmen.project1.persistence.FlightEJB;
import com.realdolmen.project1.persistence.TripEJB;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedProperty;
import javax.faces.event.ActionEvent;
import javax.faces.event.AjaxBehaviorEvent;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by JVDAX31 on 7/10/2015.
 */

@Named
@SessionScoped
public class RDTripController implements Serializable {

    @EJB
    private TripEJB tripEJB;

    @EJB
    private FlightEJB flightEJB;

    private List<Trip> allTrips;
    private Trip detailedTrip;
    private double newPrice;



    List<Location> locationListFrom;
    List<Location> locationListDestination;

    List<String> continentFrom;
    List<String> continentDestination;

    private int filterDestinationID;
    private int filterFromID;

    private String filterContintentDestinationID = "%%";
    private String filterContinentFromID = "%%";


    @ManagedProperty(value="#{param.flightID}")
    private int flightID;

    private int flightToChangeID;

    public String goToTripOverview(){
        allTrips = tripEJB.getAllTrips();
        continentDestination = tripEJB.getAllContintentDestinationsOfTrips();
        continentFrom = tripEJB.getAllContintentFromOfTrips();
        locationListDestination = tripEJB.getAllDestinationsOfTrips();
        locationListFrom = tripEJB.getAllFromOfTrips();


        return "tripOverview";
    }


    public String showDetailedTrip(int id){
        detailedTrip = tripEJB.getTripForID(id);
        System.out.println(detailedTrip.getDestination().getCity());
        return "detailedTripOverview";
    }


    public void updPrice(ActionEvent actionEvent){
        System.out.println("in updPrce");
        System.out.println(flightID);
        System.out.println(newPrice);
        if(flightToChangeID != 0){
            flightEJB.updateEmployeePriceOfFlight(flightToChangeID,newPrice);
            detailedTrip = tripEJB.getTripForID(detailedTrip.getId());
            flightID = 0;
            newPrice = 0;

        }
    }


    public void updPriceWithParam(int id){
        System.out.println("in update price with param");
        System.out.println(id);
        System.out.println(newPrice);
    }

    public void doFiltering(){
        String filterDestID = "%%";
        String filterFrID = "%%";

        if(filterDestinationID != 0){
            filterDestID = Integer.toString(filterDestinationID);
        }
        if(filterFromID != 0){
            filterFrID = Integer.toString(filterFromID);
        }

        allTrips = tripEJB.getAllTripsFiltered(filterDestID, filterFrID, filterContintentDestinationID, filterContinentFromID);
       // bookings = reportEJB.getAllBookingsFiltered(fromDate, endDate, filterDestID, filterFrID, filterContintentDestinationID, filterContinentFromID, filterTravelAgency);
       // reportInfo = reportEJB.getAllBookingsInfoFiltered(fromDate, endDate, filterDestID, filterFrID, filterContintentDestinationID, filterContinentFromID, filterTravelAgency);
    }


    public void filterOnLocation(AjaxBehaviorEvent event){


        doFiltering();

    }

    public TripEJB getTripEJB() {
        return tripEJB;
    }

    public void setTripEJB(TripEJB tripEJB) {
        this.tripEJB = tripEJB;
    }

    public List<Trip> getAllTrips() {
        return allTrips;
    }

    public void setAllTrips(List<Trip> allTrips) {
        this.allTrips = allTrips;
    }

    public Trip getDetailedTrip() {
        return detailedTrip;
    }

    public void setDetailedTrip(Trip detailedTrip) {
        this.detailedTrip = detailedTrip;
    }

    public double getNewPrice() {
        return newPrice;
    }

    public void setNewPrice(double newPrice) {
        this.newPrice = newPrice;
    }

    public int getFlightID() {
        return flightID;
    }

    public void setFlightID(int flightID) {
        this.flightID = flightID;
    }

    public FlightEJB getFlightEJB() {
        return flightEJB;
    }

    public void setFlightEJB(FlightEJB flightEJB) {
        this.flightEJB = flightEJB;
    }

    public int getFlightToChangeID() {
        return flightToChangeID;
    }

    public void setFlightToChangeID(int flightToChangeID) {
        this.flightToChangeID = flightToChangeID;
    }

    public List<Location> getLocationListFrom() {
        return locationListFrom;
    }

    public void setLocationListFrom(List<Location> locationListFrom) {
        this.locationListFrom = locationListFrom;
    }

    public List<Location> getLocationListDestination() {
        return locationListDestination;
    }

    public void setLocationListDestination(List<Location> locationListDestination) {
        this.locationListDestination = locationListDestination;
    }

    public List<String> getContinentFrom() {
        return continentFrom;
    }

    public void setContinentFrom(List<String> continentFrom) {
        this.continentFrom = continentFrom;
    }

    public List<String> getContinentDestination() {
        return continentDestination;
    }

    public void setContinentDestination(List<String> continentDestination) {
        this.continentDestination = continentDestination;
    }

    public int getFilterDestinationID() {
        return filterDestinationID;
    }

    public void setFilterDestinationID(int filterDestinationID) {
        this.filterDestinationID = filterDestinationID;
    }

    public int getFilterFromID() {
        return filterFromID;
    }

    public void setFilterFromID(int filterFromID) {
        this.filterFromID = filterFromID;
    }

    public String getFilterContintentDestinationID() {
        return filterContintentDestinationID;
    }

    public void setFilterContintentDestinationID(String filterContintentDestinationID) {
        this.filterContintentDestinationID = filterContintentDestinationID;
    }

    public String getFilterContinentFromID() {
        return filterContinentFromID;
    }

    public void setFilterContinentFromID(String filterContinentFromID) {
        this.filterContinentFromID = filterContinentFromID;
    }
}
