package com.realdolmen.project1.persistence;
import com.realdolmen.project1.XML.TripElement;
import com.realdolmen.project1.domain.Flight;
import com.realdolmen.project1.domain.Location;
import com.realdolmen.project1.domain.Trip;

import javax.ejb.Remote;
import java.util.Date;
import java.util.List;

/**
 * Created by SVCAX33 on 7/10/2015.
 */

@Remote
public interface TripEJBRemote {

    public List<Trip> getAllTrips();

    public List<Location> getAllDestinations();

    Location getDestinationForName(String destinationName);

    Trip getTripForID(int id);

    void storeNewTrips(List<TripElement> lst);

    Flight findFlightById(int id);

    Location findLocationByCode(String code);




    List<Trip> getPossibleTrips(Location destination, Date departureDate, Date arrivalDate, int numberOfPersons);
}