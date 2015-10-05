package com.realdolmen.project1.integration;

import com.realdolmen.project1.domain.Flight;
import com.realdolmen.project1.persistence.FlightEJBRemote;
import org.junit.Ignore;
import org.junit.Test;

import javax.naming.NamingException;

/**
 * Created by JVDAX31 on 5/10/2015.
 */
public class RemoteFlightEJBTest extends RemoteIntegrationTest {


    @Ignore
    @Test
    public void findAllRetrievesAllFlightsRemotely() throws NamingException {
        FlightEJBRemote flightEJBRemote = lookup("ear-module-1.1/ejb-module-1.1/FlightEJB!com.realdolmen.project1.persistence.FlightEJBRemote");
        assertEquals(3, flightEJBRemote.findAllLocations().size());
    }


    @Test
    public void findPassengerById() throws NamingException {
        FlightEJBRemote flightEJBRemote = lookup("ear-module-1.1/ejb-module-1.1/FlightEJB!com.realdolmen.project1.persistence.FlightEJBRemote");
       Flight f =  flightEJBRemote.findFlightById(1001);


    }


}