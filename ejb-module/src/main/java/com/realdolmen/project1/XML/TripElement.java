package com.realdolmen.project1.XML;

import com.realdolmen.project1.domain.Location;

import javax.persistence.ManyToOne;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by JVDAX31 on 8/10/2015.
 */
public class TripElement implements Serializable {

    private String from;
    private String to;
    private String picturename;
    private String description;
    private int freeplaces;
    private Date departureDate;
    private Date returnDate;
    private Double pricePerDay;
    private int totalPlaces;
    private int availablePlaces;



    List<FlightElement> flight;

    public TripElement(String from, String to, String picturename, String description, int freeplaces, Date departureDate, Date returnDate, Double pricePerDay, int totalPlaces, int availablePlaces, List<FlightElement> flight) {
        this.from = from;
        this.to = to;
        this.picturename = picturename;
        this.description = description;
        this.freeplaces = freeplaces;
        this.departureDate = departureDate;
        this.returnDate = returnDate;
        this.pricePerDay = pricePerDay;
        this.totalPlaces = totalPlaces;
        this.availablePlaces = availablePlaces;
        this.flight = flight;
    }

    public TripElement() {
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getPicturename() {
        return picturename;
    }

    public void setPicturename(String picturename) {
        this.picturename = picturename;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getFreeplaces() {
        return freeplaces;
    }

    public void setFreeplaces(int freeplaces) {
        this.freeplaces = freeplaces;
    }


    @XmlElement(name = "departureDate", required = true)
    @XmlJavaTypeAdapter(DateAdapter.class)
    public Date getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(Date departureDate) {
        this.departureDate = departureDate;
    }


    @XmlElement(name = "returnDate", required = true)
    @XmlJavaTypeAdapter(DateAdapter.class)
    public Date getReturnDate() {
        return returnDate;
    }


    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public Double getPricePerDay() {
        return pricePerDay;
    }

    public void setPricePerDay(Double pricePerDay) {
        this.pricePerDay = pricePerDay;
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

    public List<FlightElement> getFlight() {
        return flight;
    }

    public void setFlight(List<FlightElement> flight) {
        this.flight = flight;
    }
}
