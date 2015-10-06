package com.realdolmen.project1.REST;

import com.realdolmen.project1.domain.Location;
import com.realdolmen.project1.persistence.LocationEJB;

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

@Path("/locations")
public class LocationController {
    @Inject
    LocationEJB locationBean;


    @Path("/all")
    @GET
    @Produces("application/json")
    public JsonArray getAll(){
        JsonArrayBuilder builder = Json.createArrayBuilder();
        for(Location loc : locationBean.getAll()){
            builder.add(Json.createObjectBuilder().add("name", loc.getCity()).add("lng", loc.getLongitude()).add("lat", loc.getLatitude()));
        }

        return builder.build();
    }
}
