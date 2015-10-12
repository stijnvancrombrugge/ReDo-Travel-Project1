package com.realdolmen.project1.controller;

import com.realdolmen.project1.domain.Booking;
import com.realdolmen.project1.domain.Flight;
import com.realdolmen.project1.domain.Location;
import com.realdolmen.project1.domain.ReportInfo;
import com.realdolmen.project1.persistence.BookingEJB;
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
 * Created by JVDAX31 on 9/10/2015.
 */

@Named
@SessionScoped
public class ReportController implements Serializable {

    @EJB
    private BookingEJB bookingEJB;
    @EJB
    private ReportEJB reportEJB;

    private List<Booking> bookings;

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




    public String goToReportPage(){
        bookings = bookingEJB.findAllBookings();
        locationListDestination = reportEJB.getAllDestinationsOfBookings();
        locationListFrom = reportEJB.getAllFromOfBookings();
        departureMax = reportEJB.getMaxDateOfBookings();
        departureMin =reportEJB.getMinDateOfBookings();
        continentDestination =reportEJB.getAllContintentDestinationsOfBookings();
        continentFrom = reportEJB.getAllContintentFromOfBookings();
        reportInfo = reportEJB.getAllBookingsInfoFiltered(departureMin, departureMax, "%%", "%%", filterContintentDestinationID, filterContinentFromID);
        return "/secured/employeeReport.xhtml";
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

        bookings = reportEJB.getAllBookingsFiltered(fromDate, endDate, filterDestID, filterFrID, filterContintentDestinationID, filterContinentFromID);
        reportInfo = reportEJB.getAllBookingsInfoFiltered(fromDate, endDate, filterDestID, filterFrID, filterContintentDestinationID, filterContinentFromID);
    }



    public void filterOnLocation(AjaxBehaviorEvent event){


        doFiltering();

    }

    public void onDateSelect(SelectEvent event){
        doFiltering();

    }

    public BookingEJB getBookingEJB() {
        return bookingEJB;
    }

    public void setBookingEJB(BookingEJB bookingEJB) {
        this.bookingEJB = bookingEJB;
    }

    public List<Booking> getBookings() {
        return bookings;
    }

    public void setBookings(List<Booking> bookings) {
        this.bookings = bookings;
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

    public ReportEJB getReportEJB() {
        return reportEJB;
    }

    public void setReportEJB(ReportEJB reportEJB) {
        this.reportEJB = reportEJB;
    }

    public ReportInfo getReportInfo() {
        return reportInfo;
    }

    public void setReportInfo(ReportInfo reportInfo) {
        this.reportInfo = reportInfo;
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
