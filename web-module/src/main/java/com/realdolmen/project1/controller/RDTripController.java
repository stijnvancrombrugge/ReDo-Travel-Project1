package com.realdolmen.project1.controller;

import com.realdolmen.project1.domain.Trip;
import com.realdolmen.project1.persistence.TripEJB;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.event.ActionEvent;
import javax.faces.event.AjaxBehaviorEvent;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

/**
 * Created by JVDAX31 on 7/10/2015.
 */

@Named
@SessionScoped
public class RDTripController implements Serializable {

    @EJB
    private TripEJB tripEJB;

    private List<Trip> allTrips;
    private Trip detailedTrip;
    private double newPrice;

    public String goToTripOverview(){
        allTrips = tripEJB.getAllTrips();
        return "tripOverview";
    }


    public String showDetailedTrip(int id){
        detailedTrip = tripEJB.getTripForID(id);
        System.out.println(detailedTrip.getDestination().getCity());
        return "detailedTripOverview";
    }

    public void updPrice(ActionEvent actionEvent){
        //System.out.println(id);
        System.out.println(newPrice);
    }

    public TripEJB getTripEJB() {
        return tripEJB;
    }

    public void setTripEJB(TripEJB tripEJB) {
        this.tripEJB = tripEJB;
    }

    public List<Trip> getAllTrips() {
        return allTrips;
    }

    public void setAllTrips(List<Trip> allTrips) {
        this.allTrips = allTrips;
    }

    public Trip getDetailedTrip() {
        return detailedTrip;
    }

    public void setDetailedTrip(Trip detailedTrip) {
        this.detailedTrip = detailedTrip;
    }

    public double getNewPrice() {
        return newPrice;
    }

    public void setNewPrice(double newPrice) {
        this.newPrice = newPrice;
    }
}
