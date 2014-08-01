package com.tv.xeeng.reporttool.beans;

import com.tv.xeeng.reporttool.util.BlahBlahUtil;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.util.Date;

/**
 * Created by yeuchimse on 26/05/2014.
 */
@Entity
public class StatisticsByDay {
    @Column(name = "id")
    private int id;

    @Column(name="reportDate")
    private Date reportDate;

    @Column(name="xeengInByTool")
    private long xeengInByTool;
    @Column(name="xeengInBySMS")
    private long xeengInBySMS;
    @Column(name="xeengInByCard")
    private long xeengInByCard;

    @Column(name="xeengOutByTool")
    private long xeengOutByTool;
    @Column(name="xeengOutByExchange")
    private long xeengOutByExchange;
    @Column(name="xeengInWorkingUsers")
    private long xeengInWorkingUsers;

    @Column(name="goldInByTool")
    private long goldInByTool;
    @Column(name="goldInByExchange")
    private long goldInByExchange;
    @Column(name="goldInByAddFriend")
    private long goldInByAddFriend;
    @Column(name="goldInByFreeGold")
    private long goldInByFreeGold;
    @Column(name="goldInByRegister")
    private long goldInByRegister;
    @Column(name="goldInByGiftCode")
    private long goldInByGiftCode;
    @Column(name="goldInByWrongCalc")
    private long goldInByWrongCalc;

    @Column(name="goldOutByTool")
    private long goldOutByTool;
    @Column(name="goldOutByGame")
    private long goldOutByGame;
    @Column(name="goldInWorkingUsers")
    private long goldInWorkingUsers;

    @Column(name="newUsers")
    private long newUsers;
    @Column(name="activeUsers")
    private long activeUsers;
    @Column(name="returnUsers")
    private long returnUsers;

    public String getXeengInTotalString() {
        return BlahBlahUtil.getHumanNumberString(getXeengInTotal());
    }

    public String getXeengOutTotalString() {
        return BlahBlahUtil.getHumanNumberString(getXeengOutTotal());
    }

    public String getXeengInByToolString() {
        return BlahBlahUtil.getHumanNumberString(xeengInByTool);
    }

    public String getXeengInBySMSString() {
        return BlahBlahUtil.getHumanNumberString(xeengInBySMS);
    }

    public String getXeengInByCardString() {
        return BlahBlahUtil.getHumanNumberString(xeengInByCard);
    }

    public long getXeengInTotal() {
        return xeengInByTool + xeengInBySMS + xeengInByCard;
    }

    public long getXeengOutTotal() {
        return xeengOutByTool +xeengOutByExchange;
    }

    public long getGoldInTotal() {
        return goldInByTool + goldInByExchange + goldInByGiftCode + goldInByAddFriend + goldInByFreeGold + goldInByRegister + goldInByWrongCalc;
    }

    public long getGoldOutTotal() {
        return goldOutByTool + goldOutByGame;
    }

    public long getXeengInByTool() {
        return xeengInByTool;
    }

    public void setXeengInByTool(long xeengInByTool) {
        this.xeengInByTool = xeengInByTool;
    }

    public long getXeengInBySMS() {
        return xeengInBySMS;
    }

    public void setXeengInBySMS(long xeengInBySMS) {
        this.xeengInBySMS = xeengInBySMS;
    }

    public long getXeengInByCard() {
        return xeengInByCard;
    }

    public void setXeengInByCard(long xeengInByCard) {
        this.xeengInByCard = xeengInByCard;
    }

    public long getXeengOutByTool() {
        return xeengOutByTool;
    }

    public void setXeengOutByTool(long xeengOutByTool) {
        this.xeengOutByTool = xeengOutByTool;
    }

    public long getXeengOutByExchange() {
        return xeengOutByExchange;
    }

    public void setXeengOutByExchange(long xeengOutByExchange) {
        this.xeengOutByExchange = xeengOutByExchange;
    }

    public long getXeengInWorkingUsers() {
        return xeengInWorkingUsers;
    }

    public void setXeengInWorkingUsers(long xeengInWorkingUsers) {
        this.xeengInWorkingUsers = xeengInWorkingUsers;
    }

    public long getGoldInByTool() {
        return goldInByTool;
    }

    public void setGoldInByTool(long goldInByTool) {
        this.goldInByTool = goldInByTool;
    }

    public long getGoldInByExchange() {
        return goldInByExchange;
    }

    public void setGoldInByExchange(long goldInByExchange) {
        this.goldInByExchange = goldInByExchange;
    }

    public long getGoldInByAddFriend() {
        return goldInByAddFriend;
    }

    public void setGoldInByAddFriend(long goldInByAddFriend) {
        this.goldInByAddFriend = goldInByAddFriend;
    }

    public long getGoldInByGiftCode() {
        return goldInByGiftCode;
    }

    public void setGoldInByGiftCode(long goldInByGiftCode) {
        this.goldInByGiftCode = goldInByGiftCode;
    }

    public long getGoldOutByTool() {
        return goldOutByTool;
    }

    public void setGoldOutByTool(long goldOutByTool) {
        this.goldOutByTool = goldOutByTool;
    }

    public long getGoldOutByGame() {
        return goldOutByGame;
    }

    public void setGoldOutByGame(long goldOutByGame) {
        this.goldOutByGame = goldOutByGame;
    }

    public long getGoldInWorkingUsers() {
        return goldInWorkingUsers;
    }

    public void setGoldInWorkingUsers(long goldInWorkingUsers) {
        this.goldInWorkingUsers = goldInWorkingUsers;
    }

    public Date getReportDate() {
        return reportDate;
    }

    public void setReportDate(Date reportDate) {
        this.reportDate = reportDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getNewUsers() {
        return newUsers;
    }

    public void setNewUsers(long newUsers) {
        this.newUsers = newUsers;
    }

    public long getActiveUsers() {
        return activeUsers;
    }

    public void setActiveUsers(long activeUsers) {
        this.activeUsers = activeUsers;
    }

    public long getReturnUsers() {
        return returnUsers;
    }

    public void setReturnUsers(long returnUsers) {
        this.returnUsers = returnUsers;
    }

    public long getGoldInByFreeGold() {
        return goldInByFreeGold;
    }

    public void setGoldInByFreeGold(long goldInByFreeGold) {
        this.goldInByFreeGold = goldInByFreeGold;
    }

    public long getGoldInByRegister() {
        return goldInByRegister;
    }

    public void setGoldInByRegister(long goldInByRegister) {
        this.goldInByRegister = goldInByRegister;
    }

    public long getGoldInByWrongCalc() {
        return goldInByWrongCalc;
    }

    public void setGoldInByWrongCalc(long goldInByWrongCalc) {
        this.goldInByWrongCalc = goldInByWrongCalc;
    }
}
