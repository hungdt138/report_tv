package com.tv.xeeng.reporttool.beans;

/**
 * Created by thanhnvt on 16/07/2014.
 */
public class UserDeviceCount {
    private String deviceName;
    private int deviceCount;

    public UserDeviceCount(String deviceName, int deviceCount) {
        this.deviceName = deviceName;
        this.setDeviceCount(deviceCount);
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public int getDeviceCount() {
        return deviceCount;
    }

    public void setDeviceCount(int deviceCount) {
        this.deviceCount = deviceCount;
    }
}
