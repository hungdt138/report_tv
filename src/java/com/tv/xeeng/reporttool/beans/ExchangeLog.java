package com.tv.xeeng.reporttool.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.util.Date;

/**
 * Created by thanhnvt on 20/06/2014.
 */
@Entity
public class ExchangeLog {
    @Column(name = "userId")
    private
    long userId;
    @Column(name = "loginName")
    private
    String loginName;
    @Column(name = "moneyDiff")
    private
    int numOfXeeng;
    @Column(name = "moneyAfter")
    private
    int balance;
    @Column(name = "modifyDate")
    private
    Date exchangeDate;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public int getNumOfXeeng() {
        return numOfXeeng;
    }

    public void setNumOfXeeng(int numOfXeeng) {
        this.numOfXeeng = numOfXeeng;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public Date getExchangeDate() {
        return exchangeDate;
    }

    public void setExchangeDate(Date exchangeDate) {
        this.exchangeDate = exchangeDate;
    }
}
