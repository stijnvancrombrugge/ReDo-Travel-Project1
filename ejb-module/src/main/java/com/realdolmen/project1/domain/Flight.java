package com.realdolmen.project1.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by JVDAX31 on 3/10/2015.
 */
@Entity
public class Flight implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    private Location From;

    @ManyToOne
    private Location To;

    @Basic(optional = false)
    private Date departureTime;

    @Basic(optional = false)
    private Date arrivalTime;

    @Basic(optional = false)
    private int totalPlaces;

    @Basic(optional = false)
    private int availablePlaces;

    private int initialAvailablePlaces;

    @Basic(optional = false)
    private Double pricePerSeat;

    @Basic(optional = false)
    private Double pricePerSeatByEmployee;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Discount> discounts = new ArrayList<>();

    @Transient
    private int totalFlightsSold;
    @Transient
    private double totalTurnover;
    @Transient
    private double totalDiscount;


    @ManyToMany
    @JoinTable(name="jnd_trip_flight",
        joinColumns = @JoinColumn(name="flight_fk"),
        inverseJoinColumns = @JoinColumn(name="trip_fk"))
    private  List<Trip> trips = new ArrayList<>();

    public Flight(Location from, int availablePlaces, Location to, Date departureTime, Date arrivalTime, int totalPlaces, Double pricePerSeat) {
        From = from;
        this.availablePlaces = availablePlaces;
        To = to;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.totalPlaces = totalPlaces;
        this.pricePerSeat = pricePerSeat;
        this.pricePerSeatByEmployee = pricePerSeat;
        this.initialAvailablePlaces = availablePlaces;
    }

    public Flight(Location from, int availablePlaces, Location to, Date departureTime, Date arrivalTime, int totalPlaces) {
        From = from;
        this.availablePlaces = availablePlaces;
        To = to;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.totalPlaces = totalPlaces;
        this.initialAvailablePlaces = availablePlaces;
    }


    public int getTotalFlightsSold(){
        return initialAvailablePlaces - availablePlaces;
    }


    public double getTotalTurnover(){
        return (pricePerSeat * getTotalFlightsSold());
    }


    public double getTotalDiscount(){
        int sold = getTotalFlightsSold();
        double totalDiscount = 0.0;
        for(Discount disc:discounts){
            if(disc.getNbrToCell() <= sold){
                totalDiscount += disc.getPercentage();
            }
        }

        return (getTotalTurnover() * totalDiscount);
    }


    public void addTrip(Trip trip){
        trips.add(trip);
    }

    public void addDiscount(Discount discount){
        discounts.add(discount);
    }


    protected Flight() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getAvailablePlaces() {
        return availablePlaces;
    }

    public void setAvailablePlaces(int availablePlaces) {
        this.availablePlaces = availablePlaces;
    }

    public Location getFrom() {
        return From;
    }

    public void setFrom(Location from) {
        From = from;
    }

    public Location getTo() {
        return To;
    }

    public void setTo(Location to) {
        To = to;
    }

    public Date getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(Date departureTime) {
        this.departureTime = departureTime;
    }

    public Date getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(Date arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public int getTotalPlaces() {
        return totalPlaces;
    }

    public void setTotalPlaces(int totalPlaces) {
        this.totalPlaces = totalPlaces;
    }


    public Double getPricePerSeatByEmployee() {
        return pricePerSeatByEmployee;
    }

    public void setPricePerSeatByEmployee(Double pricePerSeatByEmployee) {
        this.pricePerSeatByEmployee = pricePerSeatByEmployee;
    }


    public Double getPricePerSeat() {
        return pricePerSeat;
    }

    public void setPricePerSeat(Double pricePerSeat) {
        this.pricePerSeat = pricePerSeat;
    }

    public List<Trip> getTrips() {
        return trips;
    }

    public void setTrips(List<Trip> trips) {
        this.trips = trips;
    }

    public List<Discount> getDiscounts() {
        return discounts;
    }

    public void setDiscounts(List<Discount> discounts) {
        this.discounts = discounts;
    }

    public int getInitialAvailablePlaces() {
        return initialAvailablePlaces;
    }

    public void setInitialAvailablePlaces(int initialAvailablePlaces) {
        this.initialAvailablePlaces = initialAvailablePlaces;
    }
}
