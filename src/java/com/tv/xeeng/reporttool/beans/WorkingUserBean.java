package com.tv.xeeng.reporttool.beans;

import java.util.Calendar;
import java.util.Date;

public class WorkingUserBean {

    private int userId;
    private String name;
    private String password;
    private String phonenumber;
    private String sessionId;
    private int wonPlayNumber;
    private int cash;
    private int xeeng;
    private int playNumber;
    private int level;
    private int experience;
    private int vipId;
    private String stateOnline;
    private int avartaId;
    private Date lastLongin;
    private String email;
    private boolean stateActive;
    private int partnerId;
    private String partner;
    private int avartaFileId;
    private int biaFileId;
    private int sex;
    private int timeQuay;
    private String status;
    private String mobileVersion;
    private Date registerDate;
    private String fromDevice;
    private String refCode;
    private String gameVersion;
    private int totalPage;
    private int totalRecord;
    private String nameQ;
    private Date lastSecondLogin;
    private String loginName;
    private String cmnd;
    private Date lockExpired;
    private Date chatLockExpired;
    private boolean avatarLocked;

    public WorkingUserBean() {
        super();
        // TODO Auto-generated constructor stub
    }

    public WorkingUserBean(int userId, String name, String password,
                           String phonenumber, String sessionId, int wonPlayNumber, int cash,
                           int playNumber, int level, int experience, int vipId,
                           String stateOnline, int avartaId, Date lastLongin, String email,
                           boolean stateActive, int partnerId, int avartaFileId,
                           int biaFileId, int sex, int timeQuay, String status,
                           String mobileVersion, Date registerDate, String fromDevice,
                           String refCode, String gameVersion, int totalPage, int totalRecord, String nameQ) {
        super();
        this.userId = userId;
        this.name = name;
        this.password = password;
        this.phonenumber = phonenumber;
        this.sessionId = sessionId;
        this.wonPlayNumber = wonPlayNumber;
        this.cash = cash;
        this.playNumber = playNumber;
        this.level = level;
        this.experience = experience;
        this.vipId = vipId;
        this.stateOnline = stateOnline;
        this.avartaId = avartaId;
        this.lastLongin = lastLongin;
        this.email = email;
        this.stateActive = stateActive;
        this.partnerId = partnerId;
        this.avartaFileId = avartaFileId;
        this.biaFileId = biaFileId;
        this.sex = sex;
        this.timeQuay = timeQuay;
        this.status = status;
        this.mobileVersion = mobileVersion;
        this.registerDate = registerDate;
        this.fromDevice = fromDevice;
        this.refCode = refCode;
        this.gameVersion = gameVersion;
        this.totalPage = totalPage;
        this.totalRecord = totalRecord;
        this.nameQ = nameQ;
    }

    public boolean getLocked() {
        if (lockExpired == null) {
            return false;
        }

        return lockExpired.after(Calendar.getInstance().getTime());
    }

    public boolean getChatLocked() {
        if (chatLockExpired == null) {
            return false;
        }

        return chatLockExpired.after(Calendar.getInstance().getTime());
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public int getWonPlayNumber() {
        return wonPlayNumber;
    }

    public void setWonPlayNumber(int wonPlayNumber) {
        this.wonPlayNumber = wonPlayNumber;
    }

    public int getCash() {
        return cash;
    }

    public void setCash(int cash) {
        this.cash = cash;
    }

    public int getPlayNumber() {
        return playNumber;
    }

    public void setPlayNumber(int playNumber) {
        this.playNumber = playNumber;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public int getVipId() {
        return vipId;
    }

    public void setVipId(int vipId) {
        this.vipId = vipId;
    }

    public String getStateOnline() {
        return stateOnline;
    }

    public void setStateOnline(String stateOnline) {
        this.stateOnline = stateOnline;
    }

    public int getAvartaId() {
        return avartaId;
    }

    public void setAvartaId(int avartaId) {
        this.avartaId = avartaId;
    }

    public Date getLastLongin() {
        return lastLongin;
    }

    public void setLastLongin(Date lastLongin) {
        this.lastLongin = lastLongin;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isStateActive() {
        return stateActive;
    }

    public void setStateActive(boolean stateActive) {
        this.stateActive = stateActive;
    }

    public int getPartnerId() {
        return partnerId;
    }

    public void setPartnerId(int partnerId) {
        this.partnerId = partnerId;
    }

    public int getAvartaFileId() {
        return avartaFileId;
    }

    public void setAvartaFileId(int avartaFileId) {
        this.avartaFileId = avartaFileId;
    }

    public int getBiaFileId() {
        return biaFileId;
    }

    public void setBiaFileId(int biaFileId) {
        this.biaFileId = biaFileId;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public int getTimeQuay() {
        return timeQuay;
    }

    public void setTimeQuay(int timeQuay) {
        this.timeQuay = timeQuay;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMobileVersion() {
        return mobileVersion;
    }

    public void setMobileVersion(String mobileVersion) {
        this.mobileVersion = mobileVersion;
    }

    public Date getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }

    public String getFromDevice() {
        return fromDevice;
    }

    public void setFromDevice(String fromDevice) {
        this.fromDevice = fromDevice;
    }

    public String getRefCode() {
        return refCode;
    }

    public void setRefCode(String refCode) {
        this.refCode = refCode;
    }

    public String getGameVersion() {
        return gameVersion;
    }

    public void setGameVersion(String gameVersion) {
        this.gameVersion = gameVersion;
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

    public String getNameQ() {
        return nameQ;
    }

    public void setNameQ(String nameQ) {
        this.nameQ = nameQ;
    }

    public int getSex() {
        return sex;
    }

    public Date getLastSecondLogin() {
        return lastSecondLogin;
    }

    public void setLastSecondLogin(Date lastSecondLogin) {
        this.lastSecondLogin = lastSecondLogin;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getCmnd() {
        return cmnd;
    }

    public void setCmnd(String cmnd) {
        this.cmnd = cmnd;
    }

    public int getXeeng() {
        return xeeng;
    }

    public void setXeeng(int xeeng) {
        this.xeeng = xeeng;
    }

    public Date getLockExpired() {
        return lockExpired;
    }

    public void setLockExpired(Date lockExpired) {
        this.lockExpired = lockExpired;
    }

    public Date getChatLockExpired() {
        return chatLockExpired;
    }

    public void setChatLockExpired(Date chatLockExpired) {
        this.chatLockExpired = chatLockExpired;
    }

    public boolean isAvatarLocked() {
        return avatarLocked;
    }

    public void setAvatarLocked(boolean avatarLocked) {
        this.avatarLocked = avatarLocked;
    }

    public String getPartner() {
        return partner;
    }

    public void setPartner(String partner) {
        this.partner = partner;
    }
}
