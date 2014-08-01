package com.tv.xeeng.reporttool.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.util.Date;

/**
 * Created by thanhnvt on 11/07/2014.
 */
@Entity
public class DeviceStatistics {
    @Column(name = "numOfAndroidDevices")
    private int numOfAndroid;
    @Column(name = "numOfIOSDevices")
    private int numOfIOS;
    @Column(name = "numOfAndroidDevicesMonthly")
    private int numOfAndroidMonthly;
    @Column(name = "numOfIOSDevicesMonthly")
    private int numOfIOSMonthly;
    @Column(name = "reportDate")
    private Date reportDate;
    
    public int getAndroidPercent() {
        if (numOfAndroid == 0 && numOfIOS == 0) {
            return 50;
        }

        return (numOfAndroid * 100) / (numOfAndroid + numOfIOS);
    }

    public int getIOSPercent() {
        return 100 - getAndroidPercent();
    }

    public int getNumOfAndroid() {
        return numOfAndroid;
    }

    public void setNumOfAndroid(int numOfAndroid) {
        this.numOfAndroid = numOfAndroid;
    }

    public int getNumOfIOS() {
        return numOfIOS;
    }

    public void setNumOfIOS(int numOfIOS) {
        this.numOfIOS = numOfIOS;
    }

    public int getNumOfAndroidMonthly() {
        return numOfAndroidMonthly;
    }

    public void setNumOfAndroidMonthly(int numOfAndroidMonthly) {
        this.numOfAndroidMonthly = numOfAndroidMonthly;
    }

    public int getNumOfIOSMonthly() {
        return numOfIOSMonthly;
    }

    public void setNumOfIOSMonthly(int numOfIOSMonthly) {
        this.numOfIOSMonthly = numOfIOSMonthly;
    }

    public Date getReportDate() {
        return reportDate;
    }

    public void setReportDate(Date reportDate) {
        this.reportDate = reportDate;
    }
}

