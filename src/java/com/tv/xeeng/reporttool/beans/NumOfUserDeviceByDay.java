package com.tv.xeeng.reporttool.beans;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by thanhnvt on 16/07/2014.
 */
public class NumOfUserDeviceByDay {
    private Date reportDate;
    private List<UserDeviceCount> deviceCounts;

    public int getTotalAndroid() {
        int count = 0;
        for (UserDeviceCount device : deviceCounts) {
            if (device.getDeviceName().contains("android")) {
                count += device.getDeviceCount();
            }
        }

        return count;
    }

    public int getTotalIOS() {
        int count = 0;
        for (UserDeviceCount device : deviceCounts) {
            if (device.getDeviceName().contains("iOS")) {
                count += device.getDeviceCount();
            }
        }

        return count;
    }

    public int getTotalJava() {
        int count = 0;
        for (UserDeviceCount device : deviceCounts) {
            if (device.getDeviceName().contains("java")) {
                count += device.getDeviceCount();
            }
        }

        return count;
    }

    public NumOfUserDeviceByDay() {
        this.deviceCounts = new ArrayList<>();
    }

    public Date getReportDate() {
        return reportDate;
    }

    public void setReportDate(Date reportDate) {
        this.reportDate = reportDate;
    }

    public List<UserDeviceCount> getDeviceCounts() {
        return deviceCounts;
    }

    public void setDeviceCounts(List<UserDeviceCount> deviceCounts) {
        this.deviceCounts = deviceCounts;
    }
}
