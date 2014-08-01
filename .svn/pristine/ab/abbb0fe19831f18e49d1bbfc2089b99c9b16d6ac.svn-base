package com.tv.xeeng.reporttool.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by thanhnvt on 17/06/2014.
 */
@Entity
public class ChargingDailyStatistics {
    @Column(name = "reportDate")
    private
    Date reportDate;
    @Column(name = "numOfPayer")
    private
    int numOfPayer;
    private List<Integer> itemCounts;

    public ChargingDailyStatistics() {
        itemCounts = new ArrayList<Integer>();
    }

    public Date getReportDate() {
        return reportDate;
    }

    public void setReportDate(Date reportDate) {
        this.reportDate = reportDate;
    }

    public int getNumOfPayer() {
        return numOfPayer;
    }

    public void setNumOfPayer(int numOfPayer) {
        this.numOfPayer = numOfPayer;
    }

    public List<Integer> getItemCounts() {
        return itemCounts;
    }

    public void setItemCounts(List<Integer> itemCounts) {
        this.itemCounts = itemCounts;
    }
}
