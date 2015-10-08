package com.realdolmen.project1.integration;

import com.realdolmen.project1.domain.Flight;
import com.realdolmen.project1.persistence.FlightEJBRemote;
import org.junit.Ignore;
import org.junit.Test;

import javax.naming.NamingException;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by JVDAX31 on 5/10/2015.
 */
public class RemoteFlightEJBTest extends RemoteIntegrationTest implements Serializable {



    @Test
    public void findAllRetrievesAllFlightsRemotely() throws NamingException {
        FlightEJBRemote flightEJBRemote = lookup("ear-module-1.1/ejb-module-1.1/FlightEJB!com.realdolmen.project1.persistence.FlightEJBRemote");
        assertEquals(4, flightEJBRemote.findAllFlights().size());
    }


    @Test
    public void findPassengerById() throws NamingException {
        FlightEJBRemote flightEJBRemote = lookup("ear-module-1.1/ejb-module-1.1/FlightEJB!com.realdolmen.project1.persistence.FlightEJBRemote");
        Flight f =  flightEJBRemote.findFlightById(1001);
        assertEquals(20, f.getAvailablePlaces());
        assertEquals("ANT", f.getFrom().getCode());


    }

    @Test
    public void newCreatedFlightIsPersist() throws NamingException {
        FlightEJBRemote flightEJBRemote = lookup("ear-module-1.1/ejb-module-1.1/FlightEJB!com.realdolmen.project1.persistence.FlightEJBRemote");
        Flight f = flightEJBRemote.createFlight(new Date(), new Date(), 23, 29, 4000, 3000, 55.99);
        f = flightEJBRemote.findFlightById(1003);
        assertEquals("ANT", f.getFrom().getCode());
        assertNotNull(f.getId());

    }

    @Test
    public void employeePriceOfFlightIsChanged() throws NamingException {
        FlightEJBRemote flightEJBRemote = lookup("ear-module-1.1/ejb-module-1.1/FlightEJB!com.realdolmen.project1.persistence.FlightEJBRemote");
        flightEJBRemote.updateEmployeePriceOfFlight(1002, 100.05);
        Flight flight = flightEJBRemote.findFlightById(1002);
        assertEquals(Double.valueOf("100.05"), flight.getPricePerSeatByEmployee());
    }


}

