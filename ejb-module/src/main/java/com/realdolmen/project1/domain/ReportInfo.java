package com.realdolmen.project1.domain;

import java.io.Serializable;

/**
 * Created by JVDAX31 on 11/10/2015.
 */
public class ReportInfo implements Serializable{

    private Long count;
    private Double sum;
    private Double min;
    private Double max;
    private Double avg;


    public ReportInfo(Long count, Double min, Double max, Double sum, Double avg) {
        this.avg = avg;
        this.count = count;
        this.sum = sum;
        this.min = min;
        this.max = max;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public Double getSum() {
        return sum;
    }

    public void setSum(Double sum) {
        this.sum = sum;
    }

    public Double getMin() {
        return min;
    }

    public void setMin(Double min) {
        this.min = min;
    }

    public Double getMax() {
        return max;
    }

    public void setMax(Double max) {
        this.max = max;
    }

    public Double getAvg() {
        return avg;
    }

    public void setAvg(Double avg) {
        this.avg = avg;
    }
}
