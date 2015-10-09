package com.realdolmen.project1.persistence;

import com.realdolmen.project1.domain.Booking;
import com.realdolmen.project1.domain.Location;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by JVDAX31 on 9/10/2015.
 */

@Stateless
@LocalBean
public class BookingEJB implements BookingEJBRemote {

    @PersistenceContext
    EntityManager entityManager;



    @Override
    public List<Booking> findAllBookings() {
        return entityManager.createQuery("select b from Booking b", Booking.class).getResultList();
    }

}
