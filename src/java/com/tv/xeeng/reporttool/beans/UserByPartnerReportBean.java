package com.tv.xeeng.reporttool.beans;

public class UserByPartnerReportBean {
    private String datetime;
    private long totalregister;
    private long totalsms;
    private long totalcard;
    private long totalSMSVND;
    private long totalCardVND;
    private int partnerId;
    private String partnerName;

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

    public void setTotalSMSVND(long totalsmsVND) {
        this.totalSMSVND = totalsmsVND;
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

	public String getPartnerName() {
		return partnerName;
	}

	public void setPartnerName(String partnerName) {
		this.partnerName = partnerName;
	}
    
	public int getPartnerId() {
		return partnerId;
	}

	public void setPartnerId(int partnerId) {
		this.partnerId = partnerId;
	}
    
    
}
