package com.tv.xeeng.reporttool.beans;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * Created by thanhnvt on 28/07/2014.
 */
@Entity
public class CampaignOSCountRecord {
    @Column(name = "campaignName")
    private String campaignName;
    @Column(name = "osName")
    private String osName;
    @Column(name = "osCount")
    private int osCount;

    public String getCampaignName() {
        return campaignName;
    }

    public void setCampaignName(String campaignName) {
        this.campaignName = campaignName;
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
