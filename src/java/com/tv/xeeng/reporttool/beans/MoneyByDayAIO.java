package com.tv.xeeng.reporttool.beans;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yeuchimse on 26/05/2014.
 */
public class MoneyByDayAIO {
    private String day;
    private List<Long> money;
    public long totalMoney;

    public MoneyByDayAIO() {
        money = new ArrayList<Long>();
    }

    public long getTotalMoney() {
        long tm = 0;

        for (long m: getMoney()) {
            tm += m;
        }

        return tm;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public List<Long> getMoney() {
        return money;
    }

    public void setMoney(List<Long> money) {
        this.money = money;
    }
}
