package com.realdolmen.project1.domain;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by JVDAX31 on 6/10/2015.
 */

@Entity
public class Discount implements Serializable {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int nbrToCell;
    private double percentage;



    protected Discount(){

    }

    public Discount(int nbrToCell, double percentage) {
        this.nbrToCell = nbrToCell;
        this.percentage = percentage;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNbrToCell() {
        return nbrToCell;
    }

    public void setNbrToCell(int nbrToCell) {
        this.nbrToCell = nbrToCell;
    }

    public double getPercentage() {
        return percentage;
    }

    public void setPercentage(double percentage) {
        this.percentage = percentage;
    }

}
