package com.tv.xeeng.reporttool.beans;

/**
 * Created by yeuchimse on 23/05/2014.
 */
public class XeengInOutBean {
    private long inFromReportTool;
    private long inFromSMS;
    private long inFromCard;

    private long outInWorkingUser;
    private long outInExchange;

    public long getInFromReportTool() {
        return inFromReportTool;
    }

    public void setInFromReportTool(long inFromReportTool) {
        this.inFromReportTool = inFromReportTool;
    }

    public long getInFromSMS() {
        return inFromSMS;
    }

    public void setInFromSMS(long inFromSMS) {
        this.inFromSMS = inFromSMS;
    }

    public long getInFromCard() {
        return inFromCard;
    }

    public void setInFromCard(long inFromCard) {
        this.inFromCard = inFromCard;
    }

    public long getOutInWorkingUser() {
        return outInWorkingUser;
    }

    public void setOutInWorkingUser(long outInWorkingUser) {
        this.outInWorkingUser = outInWorkingUser;
    }

    public long getOutInExchange() {
        return outInExchange;
    }

    public void setOutInExchange(long outInExchange) {
        this.outInExchange = outInExchange;
    }
}
