package com.tv.xeeng.reporttool.beans;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by thanhnvt on 28/07/2014.
 */
public class CampaignStatistics {
    private String campaignName;
    private List<CampaignOSCount> osCounts;

    public int getSumOfCount() {
        int sum = 0;
        for (CampaignOSCount osCount : osCounts) {
            sum += osCount.getOsCount();
        }

        return sum;
    }

    public CampaignStatistics() {
        osCounts = new ArrayList<>();
    }

    public String getCampaignName() {
        return campaignName;
    }

    public void setCampaignName(String campaignName) {
        this.campaignName = campaignName;
    }

    public List<CampaignOSCount> getOsCounts() {
        return osCounts;
    }

    public void setOsCounts(List<CampaignOSCount> osCounts) {
        this.osCounts = osCounts;
    }
}
