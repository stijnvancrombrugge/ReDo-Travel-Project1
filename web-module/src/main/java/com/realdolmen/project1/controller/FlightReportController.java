package com.realdolmen.project1.controller;

import com.realdolmen.project1.domain.*;
import com.realdolmen.project1.persistence.AirlineEJB;
import com.realdolmen.project1.persistence.BookingEJB;
import com.realdolmen.project1.persistence.FlightReportEJB;
import com.realdolmen.project1.persistence.ReportEJB;
import org.primefaces.event.SelectEvent;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.event.AjaxBehaviorEvent;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by JVDAX31 on 12/10/2015.
 */
@Named
@SessionScoped
public class FlightReportController implements Serializable {


    @EJB
    private FlightReportEJB flightReportEJB;

    @EJB
    private AirlineEJB airlineEJB;

    private AirlineCompany airlineCompany;



    private List<Flight> flights;

    List<Location> locationListFrom;
    List<Location> locationListDestination;

    List<String> continentFrom;
    List<String> continentDestination;

    private int filterDestinationID;
    private int filterFromID;



    private String filterContintentDestinationID = "%%";
    private String filterContinentFromID = "%%";


    private Date departureMin;
    private Date departureMax;

    private ReportInfo reportInfo;




    public String goToReportPage(String username){
        System.out.println("in go to report page");
        airlineCompany = airlineEJB.getAirlineOfPartner(username);
        locationListDestination = flightReportEJB.getAllDestinationsOfFlights(airlineCompany.getName());
        locationListFrom = flightReportEJB.getAllOriginOfFlights(airlineCompany.getName());
        departureMax = flightReportEJB.getMaxDateOfFlights(airlineCompany.getName());
        departureMin =flightReportEJB.getMinDateOfFlights(airlineCompany.getName());
        continentDestination =flightReportEJB.getAllContintentDestinationsOfFlight(airlineCompany.getName());
        continentFrom = flightReportEJB.getAllContintentFromOfFlight(airlineCompany.getName());
        flights = flightReportEJB.getAllFlightsFiltered(departureMin, departureMax, "%%", "%%", filterContintentDestinationID, filterContinentFromID, airlineCompany.getName());
        reportInfo = flightReportEJB.getAllFlightsInfoFiltered(departureMin, departureMax, "%%", "%%", filterContintentDestinationID, filterContinentFromID, airlineCompany.getName());
        return "/secured/flightReport.xhtml";
    }


    public void doFiltering(){
        String filterDestID = "%%";
        String filterFrID = "%%";
        Date fromDate = departureMin;
        Date endDate = departureMax;
        if(filterDestinationID != 0){
            filterDestID = Integer.toString(filterDestinationID);
        }
        if(filterFromID != 0){
            filterFrID = Integer.toString(filterFromID);
        }

        flights = flightReportEJB.getAllFlightsFiltered(departureMin, departureMax, filterDestID, filterFrID, filterContintentDestinationID, filterContinentFromID, airlineCompany.getName());
        reportInfo = flightReportEJB.getAllFlightsInfoFiltered(departureMin, departureMax, filterDestID, filterFrID, filterContintentDestinationID, filterContinentFromID, airlineCompany.getName());
    }



    public void filterOnLocation(AjaxBehaviorEvent event){


        doFiltering();

    }

    public void onDateSelect(SelectEvent event){
        doFiltering();

    }

    public FlightReportEJB getFlightReportEJB() {
        return flightReportEJB;
    }

    public void setFlightReportEJB(FlightReportEJB flightReportEJB) {
        this.flightReportEJB = flightReportEJB;
    }

    public AirlineEJB getAirlineEJB() {
        return airlineEJB;
    }

    public void setAirlineEJB(AirlineEJB airlineEJB) {
        this.airlineEJB = airlineEJB;
    }

    public AirlineCompany getAirlineCompany() {
        return airlineCompany;
    }

    public void setAirlineCompany(AirlineCompany airlineCompany) {
        this.airlineCompany = airlineCompany;
    }

    public List<Flight> getFlights() {
        return flights;
    }

    public void setFlights(List<Flight> flights) {
        this.flights = flights;
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

    public ReportInfo getReportInfo() {
        return reportInfo;
    }

    public void setReportInfo(ReportInfo reportInfo) {
        this.reportInfo = reportInfo;
    }
}
