package com.realdolmen.project1.controller;

import com.realdolmen.project1.domain.Flight;
import com.realdolmen.project1.persistence.FlightEJB;
import org.primefaces.context.RequestContext;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by JVDAX31 on 5/10/2015.
 */

@Named
@SessionScoped
public class FlightController implements Serializable {

    @EJB
    private FlightEJB flightEJB;

    private Date departureDate;


    public List<Flight> getAllFlights(){
    return flightEJB.findAllFlights();

    }

    public List<Flight> getAllFlightsForAirline(){
        return flightEJB.findAllFlights();

    }


    public FlightEJB getFlightEJB() {
        return flightEJB;
    }

    public void setFlightEJB(FlightEJB flightEJB) {
        this.flightEJB = flightEJB;
    }

    public Date getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(Date departureDate) {
        this.departureDate = departureDate;
    }


    public String click() {
   //     RequestContext requestContext = RequestContext.getCurrentInstance();

     //   requestContext.update("form:display");
      //  requestContext.execute("PF('dlg').show()");
        System.out.println(departureDate);
          return "allFlights.xhtml";
    }
}
