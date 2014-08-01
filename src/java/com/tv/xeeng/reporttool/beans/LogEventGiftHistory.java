/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tv.xeeng.reporttool.beans;

/**
 *
 * @author hungdt
 */
public class LogEventGiftHistory {

    private int id = 0;
    private int userId = 0;
    private String evgfCode = "";
    private int value = 0;
    private String type = "";
    private String message = "";
    private String useDate = "";
    private int fromValue = 0;
    private int toValue = 0;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getEvgfCode() {
        return evgfCode;
    }

    public void setEvgfCode(String evgfCode) {
        this.evgfCode = evgfCode;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUseDate() {
        return useDate;
    }

    public void setUseDate(String useDate) {
        this.useDate = useDate;
    }

    public int getFromValue() {
        return fromValue;
    }

    public void setFromValue(int fromValue) {
        this.fromValue = fromValue;
    }

    public int getToValue() {
        return toValue;
    }

    public void setToValue(int toValue) {
        this.toValue = toValue;
    }

}
