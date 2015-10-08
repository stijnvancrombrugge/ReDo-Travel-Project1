package com.realdolmen.project1.XML;

import java.io.Serializable;

/**
 * Created by JVDAX31 on 8/10/2015.
 */
public class FlightElement implements Serializable{
    int flightID;

    public FlightElement(int flightID) {
        this.flightID = flightID;
    }

    public FlightElement() {
    }

    public int getFlightID() {
        return flightID;
    }

    public void setFlightID(int flightID) {
        this.flightID = flightID;
    }
}
