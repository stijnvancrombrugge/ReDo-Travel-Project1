package com.realdolmen.project1.REST;

import com.realdolmen.project1.domain.Location;
import com.realdolmen.project1.domain.Trip;
import com.realdolmen.project1.persistence.LocationEJB;
import com.realdolmen.project1.persistence.TripEJB;

import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 * Created by SVCAX33 on 6/10/2015.
 */

@Path("/trips")
public class TripController {
    @Inject
    TripEJB tripEJB;


    @Path("/all")
    @GET
    @Produces("application/json")
    public JsonArray getAll(){
        JsonArrayBuilder builder = Json.createArrayBuilder();
        for(Trip trip : tripEJB.getAllTrips()){
            Location loc = trip.getDestination();
            builder.add(Json.createObjectBuilder().add("name", loc.getCity()).add("lng", loc.getLongitude()).add("lat", loc.getLatitude()).add("description", loc.getContinent()).add("hide", "yes"));
        }

        return builder.build();
    }
}
