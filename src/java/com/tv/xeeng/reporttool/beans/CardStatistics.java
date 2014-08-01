package com.tv.xeeng.reporttool.beans;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * Created by yeuchimse on 13/06/2014.
 */
@Entity
public class CardStatistics {
    @Column(name = "reportDate")
    private
    String reportDate;
    @Column(name = "itemMoney")
    private
    int itemMoney;
    @Column(name = "itemCount")
    private
    int itemCount;

    public String getReportDate() {
        return reportDate;
    }

    public void setReportDate(String reportDate) {
        this.reportDate = reportDate;
    }

    public int getItemMoney() {
        return itemMoney;
    }

    public void setItemMoney(int itemMoney) {
        this.itemMoney = itemMoney;
    }

    public int getItemCount() {
        return itemCount;
    }

    public void setItemCount(int itemCount) {
        this.itemCount = itemCount;
    }
}
