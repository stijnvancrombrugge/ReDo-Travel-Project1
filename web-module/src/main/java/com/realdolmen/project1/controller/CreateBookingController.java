package com.realdolmen.project1.controller;

import com.realdolmen.project1.domain.Location;
import com.realdolmen.project1.domain.Trip;
import com.realdolmen.project1.persistence.TripEJB;

import javax.ejb.EJB;
import javax.enterprise.context.ConversationScoped;
import javax.faces.event.ValueChangeEvent;
import javax.inject.Inject;
import javax.inject.Named;
import javax.print.attribute.standard.Destination;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by SVCAX33 on 7/10/2015.
 */

@Named
@ConversationScoped
public class CreateBookingController implements Serializable {

    @EJB
    private TripEJB tripEJB;


    private String selectedDestinationName;
    private List<Location> allDestinations = new ArrayList<>();

    public String startCreationTrip(){
        allDestinations = tripEJB.getAllDestinations();
        return "/secured/searchTrip.xhtml";
    }


    public List<Location> getAllDestinations() {
        return allDestinations;
    }

    public void setAllDestinations(List<Location> allDestinations) {
        this.allDestinations = allDestinations;
    }

    public String getSelectedDestinationName() {
        return selectedDestinationName;
    }

    public void setSelectedDestinationName(String selectedDestinationName) {
        this.selectedDestinationName = selectedDestinationName;
    }
}
