package com.realdolmen.project1.controller;

import javax.enterprise.context.Conversation;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

/**
 * Created by SVCAX33 on 7/10/2015.
 */

@Named
@RequestScoped
public class CreateBookingControllerConversation implements Serializable {

    @Inject
    private Conversation conversation;

    @Inject
    private CreateBookingController createBookingController;


    public String startCreationTrip(){
        conversation.begin();
        return createBookingController.startCreationTrip();
    }


    public String saveFlight(){
        //String go =  createBookingController.saveBooking();
        conversation.end();
        return "go";

    }

}
