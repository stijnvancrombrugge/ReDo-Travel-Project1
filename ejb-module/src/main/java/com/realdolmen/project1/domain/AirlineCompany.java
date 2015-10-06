package com.realdolmen.project1.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by JVDAX31 on 6/10/2015.
 */

@Entity
public class AirlineCompany {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String Name;

    @OneToMany
    private List<Flight> flights = new ArrayList<>();

    @OneToMany
    private List<PartnerEmployee> partners = new ArrayList<>();



    protected AirlineCompany(){

    }

    public void addFlight(Flight f){
        flights.add(f);
    }

    public void addPartner(PartnerEmployee partnerEmployee){
        partners.add(partnerEmployee);
    }


    public AirlineCompany(String name) {
        Name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public List<Flight> getFlights() {
        return flights;
    }

    public void setFlights(List<Flight> flights) {
        this.flights = flights;
    }

    public List<PartnerEmployee> getPartners() {
        return partners;
    }

    public void setPartners(List<PartnerEmployee> partners) {
        this.partners = partners;
    }


}
