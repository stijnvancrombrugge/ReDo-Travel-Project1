package com.realdolmen.project1.controller;

import com.realdolmen.project1.domain.Flight;

import javax.enterprise.context.Conversation;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

/**
 * Created by JVDAX31 on 6/10/2015.
 */
@Named
@RequestScoped
public class CreateFlightControllerConversation implements Serializable {

    @Inject
    private Conversation conversation;

    @Inject
    private CreateFlightController createFlightController;

    public String startCreation(){
        conversation.begin();
        return createFlightController.startCreation();
    }


    public String saveFlight(){
        String go =  createFlightController.saveFlight();
           conversation.end();
            return go;

        }


}
