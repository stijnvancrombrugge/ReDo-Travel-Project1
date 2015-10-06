package com.realdolmen.project1.persistence;

import com.realdolmen.project1.domain.Location;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created by SVCAX33 on 6/10/2015.
 */

@Stateless
public class LocationEJB {

    @PersistenceContext
    private EntityManager em;


    public List<Location> getAll(){
        TypedQuery<Location> query = em.createQuery("select l from Location l", Location.class);
        return query.getResultList();
    }
}
