package com.realdolmen.project1.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by JVDAX31 on 6/10/2015.
 */

@Entity
public class Discount {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int nbrToCell;
    private double percentage;

    //true: nbr of sells are bound to a flight
    //false: nbr of sells are bound to all the flights sold by RDTravel
    private boolean boundToFlight;




}
