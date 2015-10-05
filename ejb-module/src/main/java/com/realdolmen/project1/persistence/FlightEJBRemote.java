package com.realdolmen.project1.persistence;

import com.realdolmen.project1.domain.Flight;
import com.realdolmen.project1.domain.Location;

import javax.ejb.Remote;
import java.util.Date;
import java.util.List;

/**
 * Created by JVDAX31 on 5/10/2015.
 */
@Remote
public interface FlightEJBRemote {

    List<Flight> findAllFlights();

    List<Location> findAllLocations();

    Flight findFlightById(int id);

    void updateFlight(Flight flight);

    void createFlight(Flight flight);

   void createFlight(Date departureDate, Date arrivalDate, int totalPlaces, int freePlaces, int locationfromid, int locationtoid);



}
