package com.tv.xeeng.reporttool.beans;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class UserBean {

    private final int ROLE_ADMIN = 1;
    private final int ROLE_OPERATOR = 2;
    private final int ROLE_GM = 3;
    private final int ROLE_IA = 4;

    @Column(name = "userId")
    private long id;
    @Column(name = "name")
    private String username;
    @Column(name = "password")
    private String password;
    private boolean isLogged;
    private int userType;
    private int partnerId;
    private int money;
    private int online;
    private String lastLogin;
    private int totalPage;
    private int totalRecord;
    @Column(name = "active")
    private boolean active;
    @Column(name = "roleId")
    private int roleId;
    @Column(name = "roleName")
    private String roleName;


    public boolean isCanChangeUserInfo() {
        return (roleId != ROLE_IA);
    }

    public boolean isCanBlockUser() {
        return (roleId != ROLE_IA);
    }

    public boolean isCanEditALTPQuestion() {
        return (roleId != ROLE_IA);
    }

    public boolean isCanAddInGameAnnouncement() {
        return (roleId != ROLE_IA);
    }

    public boolean isCanViewRevenue() {
        return (roleId == ROLE_ADMIN || roleId == ROLE_IA);
    }

    public boolean isCanViewOperationInfo() {
        return (roleId == ROLE_ADMIN || roleId == ROLE_OPERATOR);
    }

    public boolean getIsAdmin() {
        return roleId == ROLE_ADMIN;
    }

    public boolean getIsOperator() {
        return roleId == ROLE_OPERATOR;
    }

    public boolean getIsGameMaster() {
        return roleId == ROLE_GM;
    }

    public boolean getIsIA() {
        return roleId == ROLE_IA;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getTotalRecord() {
        return totalRecord;
    }

    public void setTotalRecord(int totalRecord) {
        this.totalRecord = totalRecord;
    }

    public int getPartnerId() {
        return partnerId;
    }

    public void setPartnerId(int partnerId) {
        this.partnerId = partnerId;
    }

    public int getUserType() {
        return userType;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }

    public boolean isIsLogged() {
        return isLogged;
    }

    public void setIsLogged(boolean isLogged) {
        this.isLogged = isLogged;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    /**
     * @return the isLogged
     */
    public boolean isLogged() {
        return isLogged;
    }

    /**
     * @param isLogged the isLogged to set
     */
    public void setLogged(boolean isLogged) {
        this.isLogged = isLogged;
    }

    /**
     * @return the money
     */
    public int getMoney() {
        return money;
    }

    /**
     * @param money the money to set
     */
    public void setMoney(int money) {
        this.money = money;
    }

    /**
     * @return the online
     */
    public int getOnline() {
        return online;
    }

    /**
     * @param online the online to set
     */
    public void setOnline(int online) {
        this.online = online;
    }

    /**
     * @return the lastLogin
     */
    public String getLastLogin() {
        return lastLogin;
    }

    /**
     * @param lastLogin the lastLogin to set
     */
    public void setLastLogin(String lastLogin) {
        this.lastLogin = lastLogin;
    }


    public boolean isActive() {
        return active;
    }

    public void setActive(boolean isActive) {
        this.active = isActive;
    }

    public int getRoleId() {
        return roleId;
    }

    public void setRoleId(int roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
