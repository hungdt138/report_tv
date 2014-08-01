package com.tv.xeeng.reporttool.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.util.Date;

/**
 * Created by thanhnvt on 07/07/2014.
 */
@Entity
public class ReportUserLog {
    @Column(name="logDate")
    private Date logDate;
    @Column(name="name")
    private String username;
    @Column(name="action")
    private String action;
    @Column(name="refId")
    private int refId;

    public Date getLogDate() {
        return logDate;
    }

    public void setLogDate(Date logDate) {
        this.logDate = logDate;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public int getRefId() {
        return refId;
    }

    public void setRefId(int refId) {
        this.refId = refId;
    }
}
