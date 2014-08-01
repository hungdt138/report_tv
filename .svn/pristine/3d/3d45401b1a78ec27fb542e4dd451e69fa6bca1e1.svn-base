package com.tv.xeeng.reporttool.beans;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * Created by thanhnvt on 16/07/2014.
 */
@Entity
public class UserOSCount {
    @Column(name = "os")
    private String osName;
    @Column(name = "num")
    private int osCount;

    public UserOSCount() {

    }

    public UserOSCount(String deviceName, int deviceCount) {
        this.osName = deviceName;
        this.setOsCount(deviceCount);
    }

    public String getOsName() {
        return osName;
    }

    public void setOsName(String osName) {
        this.osName = osName;
    }

    public int getOsCount() {
        return osCount;
    }

    public void setOsCount(int osCount) {
        this.osCount = osCount;
    }
}
