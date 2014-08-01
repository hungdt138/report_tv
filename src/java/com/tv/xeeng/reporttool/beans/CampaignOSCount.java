package com.tv.xeeng.reporttool.beans;

/**
 * Created by thanhnvt on 28/07/2014.
 */
public class CampaignOSCount {
    private String osName;
    private int osCount;

    public CampaignOSCount(String osName, int osCount) {
        this.osName = osName;
        this.osCount = osCount;
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
