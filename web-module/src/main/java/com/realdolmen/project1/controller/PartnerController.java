package com.realdolmen.project1.controller;

import com.realdolmen.project1.domain.AirlineCompany;
import com.realdolmen.project1.domain.Flight;
import com.realdolmen.project1.persistence.AirlineEJB;
import com.realdolmen.project1.persistence.FlightEJB;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
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

    public String goToFlights(String username){
        airlineCompany = airlineEJB.getAirlineOfPartner(username);
        flights = airlineCompany.getFlights();
        return "allFlights";



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
}
