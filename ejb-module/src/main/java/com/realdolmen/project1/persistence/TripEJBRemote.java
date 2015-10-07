package com.realdolmen.project1.persistence;

import com.realdolmen.project1.domain.Trip;

import javax.ejb.Remote;
import java.util.List;

/**
 * Created by JVDAX31 on 7/10/2015.
 */
@Remote
public interface TripEJBRemote {

    List<Trip> getAllTrips();

    Trip getTripForID(int id);
}