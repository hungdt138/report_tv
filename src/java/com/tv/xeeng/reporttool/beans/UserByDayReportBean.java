package com.tv.xeeng.reporttool.beans;

public class UserByDayReportBean {
    private String datetime;
    private long totalregister;
    private long totalsms;
    private long totalSMSVND;
    private long totalcard;
    private long totalCardVND;

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public long getTotalregister() {
        return totalregister;
    }

    public void setTotalregister(long totalregister) {
        this.totalregister = totalregister;
    }

    public long getTotalsms() {
        return totalsms;
    }

    public void setTotalsms(long totalsms) {
        this.totalsms = totalsms;
    }

    public long getTotalSMSVND() {
        return totalSMSVND;
    }

    public void setTotalSMSVND(long totalSMSVND) {
        this.totalSMSVND = totalSMSVND;
    }

    public long getTotalcard() {
        return totalcard;
    }

    public void setTotalcard(long totalcard) {
        this.totalcard = totalcard;
    }

    public long getTotalCardVND() {
        return totalCardVND;
    }

    public void setTotalCardVND(long totalCardVND) {
        this.totalCardVND = totalCardVND;
    }
}
