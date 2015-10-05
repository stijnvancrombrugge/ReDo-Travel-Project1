package com.realdolmen.project1.persistence;

import com.realdolmen.project1.domain.Flight;

import javax.ejb.Remote;
import java.util.List;

/**
 * Created by JVDAX31 on 5/10/2015.
 */
@Remote
public interface FlightEJBRemote {

    List<Flight> findAllFlights();

    Flight findFlightById(int id);

    void updateFlight(Flight flight);

    void createFlight(Flight flight);



}
