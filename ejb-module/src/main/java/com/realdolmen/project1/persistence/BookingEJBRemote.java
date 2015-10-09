package com.realdolmen.project1.persistence;

import com.realdolmen.project1.domain.Booking;

import javax.ejb.Remote;
import java.util.List;

/**
 * Created by JVDAX31 on 9/10/2015.
 */

@Remote
public interface BookingEJBRemote {

    List<Booking> findAllBookings();


}
