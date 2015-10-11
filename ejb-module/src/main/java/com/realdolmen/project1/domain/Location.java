package com.realdolmen.project1.domain;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by JVDAX31 on 3/10/2015.
 */

@Entity
public class Location implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Basic(optional=false)
    private String continent;

    private String country;

    @Basic(optional=false)
    private double latitude;

    @Basic(optional=false)
    private double longitude;

    @Basic(optional=false)
    @Column(unique = true)
    private String city;

    @Basic(optional=false)
    private String code;


    public Location(String continent, String country, String city, String code, double latitude, double longitude) {
        this.continent = continent;
        this.country = country;
        this.city = city;
        this.code = code;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    protected Location() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContinent() {
        return continent;
    }

    public void setContinent(String continent) {
        this.continent = continent;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(long latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(long longitude) {
        this.longitude = longitude;
    }
}
