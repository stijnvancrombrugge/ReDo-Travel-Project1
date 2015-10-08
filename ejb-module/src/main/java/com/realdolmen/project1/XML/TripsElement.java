package com.realdolmen.project1.XML;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * Created by JVDAX31 on 8/10/2015.
 */
@XmlRootElement(name = "trips")
public class TripsElement {

    List<TripElement> trip;

    public TripsElement(List<TripElement> trip) {
        this.trip = trip;
    }

    public TripsElement() {
    }

    @XmlElement
    public List<TripElement> getTrip() {
        return trip;
    }

    public void setTrip(List<TripElement> trip) {
        this.trip = trip;
    }
}
