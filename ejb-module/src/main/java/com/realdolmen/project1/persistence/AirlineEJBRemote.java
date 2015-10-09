package com.realdolmen.project1.persistence;

import com.realdolmen.project1.domain.*;

import javax.ejb.Remote;
import java.util.Date;
import java.util.List;

/**
 * Created by JVDAX31 on 7/10/2015.
 */
@Remote
public interface AirlineEJBRemote  {

    public AirlineCompany getAirlineOfPartner(String username);

    List<Location> getFromLocationForAirline(String username);

    List<Flight> getFlightsOfAirlineTo(String airlinename, int id);

    List<Flight> getFlightsOfAirlineBetween(String airlinename, Date from, Date to);

    List<Flight> getFlightsOfAirlineToWild(String airlinename, String id);

    List<Flight> getFlightsOfAirlineBetween(String airlinename, Date from, Date to, String toloc);

    List<Location> allLocationsWithWildcard(String wild);

    List<Flight> getFlightsOfAirlineBetweenDateStrings(String airlinename, String from, String to, String toloc);

}
