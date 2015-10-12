package com.realdolmen.project1.controller;

import com.realdolmen.project1.domain.AirlineCompany;
import com.realdolmen.project1.domain.Flight;
import com.realdolmen.project1.domain.Location;
import com.realdolmen.project1.persistence.AirlineEJB;
import com.realdolmen.project1.persistence.FlightEJB;
import org.primefaces.event.SelectEvent;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.event.AjaxBehaviorEvent;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by JVDAX31 on 7/10/2015.
 */

@Named
@SessionScoped
public class PartnerController implements Serializable {


    @EJB
    private AirlineEJB airlineEJB;

    List<Flight> flights;
    AirlineCompany airlineCompany;

    List<Location> locationListForPartner;
    private int filterDestinationID;

    List<Location> locationListFromForPartner;
    private int filterFromID;




    private Date departureMin;
    private Date departureMax;

    public String goToFlights(String username){
        System.out.println(username);

        airlineCompany = airlineEJB.getAirlineOfPartner(username);
        flights = airlineCompany.getFlights();
       // locationListForPartner = airlineEJB.getFromLocationForAirline(airlineCompany.getName());
        locationListForPartner = airlineEJB.getAllDestinationsOfAirline(airlineCompany.getName());
        locationListFromForPartner = airlineEJB.getAllFromOfBookingsOfAirline(airlineCompany.getName());
        departureMin = airlineEJB.getMinDateOfFlightsForAirline(airlineCompany.getName());
        departureMax = airlineEJB.getMaxDateOfFlightsForAirline(airlineCompany.getName());
        return "allFlights";

   }

    public void filterWithDestination(AjaxBehaviorEvent event){

        doFiltering();
    }

    public void onFromDateSelect(SelectEvent event){
        System.out.println(departureMin);

    }

    public void doFiltering(){
        String filterDestID = "%%";
        String filterFrID = "%%";
       // Date fromDate = departureMin;
       // Date endDate = departureMax;
        if(filterDestinationID != 0){
            filterDestID = Integer.toString(filterDestinationID);
        }
        if(filterFromID != 0){
            filterFrID = Integer.toString(filterFromID);
        }
        /*
        if(departureMin == null){
            fromDate = new Date(Long.MIN_VALUE);
        }
        if(departureMax == null){
            endDate = new Date(Long.MAX_VALUE);
        }
        */

        flights = airlineEJB.getFlightsOfAirlineBetween(airlineCompany.getName(), departureMin, departureMax, filterDestID, filterFrID);
    }

    public void onToDateSelect(SelectEvent event){
        doFiltering();
    }

    public List<Flight> getFlights() {
        return flights;
    }

    public void setFlights(List<Flight> flights) {
        this.flights = flights;
    }

    public AirlineCompany getAirlineCompany() {
        return airlineCompany;
    }

    public void setAirlineCompany(AirlineCompany airlineCompany) {
        this.airlineCompany = airlineCompany;
    }

    public AirlineEJB getAirlineEJB() {
        return airlineEJB;
    }

    public void setAirlineEJB(AirlineEJB airlineEJB) {
        this.airlineEJB = airlineEJB;
    }

    public List<Location> getLocationListForPartner() {
        return locationListForPartner;
    }

    public void setLocationListForPartner(List<Location> locationListForPartner) {
        this.locationListForPartner = locationListForPartner;
    }

    public int getFilterDestinationID() {
        return filterDestinationID;
    }

    public void setFilterDestinationID(int filterDestinationID) {
        this.filterDestinationID = filterDestinationID;
    }

    public Date getDepartureMin() {
        return departureMin;
    }

    public void setDepartureMin(Date departureMin) {
        this.departureMin = departureMin;
    }

    public Date getDepartureMax() {
        return departureMax;
    }

    public void setDepartureMax(Date departureMax) {
        this.departureMax = departureMax;
    }

    public List<Location> getLocationListFromForPartner() {
        return locationListFromForPartner;
    }

    public void setLocationListFromForPartner(List<Location> locationListFromForPartner) {
        this.locationListFromForPartner = locationListFromForPartner;
    }

    public int getFilterFromID() {
        return filterFromID;
    }

    public void setFilterFromID(int filterFromID) {
        this.filterFromID = filterFromID;
    }
}
