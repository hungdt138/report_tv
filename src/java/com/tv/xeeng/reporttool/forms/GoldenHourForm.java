package com.tv.xeeng.reporttool.forms;

import org.apache.struts.action.ActionForm;

public class GoldenHourForm extends ActionForm {
	private int id;
	private String fromDate;
	private String toDate;
	private boolean active;
	private int type;
	private int bonusAmount;
	private int partnerId;
	private String externalParam;
	private String description;
	
	private String fromTime;
	private String toTime;
	
	private String fhour;
	private String fminutes;
	private String fsecond;

	private String thour;
	private String tminutes;
	private String tsecond;
	
	public GoldenHourForm() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	


	public GoldenHourForm(int id, String fromDate, String toDate,
			boolean active, int type, int bonusAmount, int partnerId,
			String externalParam, String description, String fromTime,
			String toTime, String fhour, String fminutes, String fsecond,
			String thour, String tminutes, String tsecond) {
		super();
		this.id = id;
		this.fromDate = fromDate;
		this.toDate = toDate;
		this.active = active;
		this.type = type;
		this.bonusAmount = bonusAmount;
		this.partnerId = partnerId;
		this.externalParam = externalParam;
		this.description = description;
		this.fromTime = fromTime;
		this.toTime = toTime;
		this.fhour = fhour;
		this.fminutes = fminutes;
		this.fsecond = fsecond;
		this.thour = thour;
		this.tminutes = tminutes;
		this.tsecond = tsecond;
	}





	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFromDate() {
		return fromDate;
	}
	public void setFromDate(String fromDate) {
		this.fromDate = fromDate;
	}
	public String getToDate() {
		return toDate;
	}
	public void setToDate(String toDate) {
		this.toDate = toDate;
	}
	public boolean getActive() {
		return active;
	}
	public void setActive(boolean isActive) {
		this.active = isActive;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getBonusAmount() {
		return bonusAmount;
	}
	public void setBonusAmount(int bonusAmount) {
		this.bonusAmount = bonusAmount;
	}
	public int getPartnerId() {
		return partnerId;
	}
	public void setPartnerId(int partnerId) {
		this.partnerId = partnerId;
	}
	public String getExternalParam() {
		return externalParam;
	}
	public void setExternalParam(String externalParam) {
		this.externalParam = externalParam;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getFhour() {
		return fhour;
	}
	public void setFhour(String fhour) {
		this.fhour = fhour;
	}
	public String getFminutes() {
		return fminutes;
	}
	public void setFminutes(String fminutes) {
		this.fminutes = fminutes;
	}
	public String getFsecond() {
		return fsecond;
	}
	public void setFsecond(String fsecond) {
		this.fsecond = fsecond;
	}
	public String getThour() {
		return thour;
	}
	public void setThour(String thour) {
		this.thour = thour;
	}
	public String getTminutes() {
		return tminutes;
	}
	public void setTminutes(String tminutes) {
		this.tminutes = tminutes;
	}
	public String getTsecond() {
		return tsecond;
	}
	public void setTsecond(String tsecond) {
		this.tsecond = tsecond;
	}





	public String getFromTime() {
		return fromTime;
	}





	public void setFromTime(String fromTime) {
		this.fromTime = fromTime;
	}





	public String getToTime() {
		return toTime;
	}





	public void setToTime(String toTime) {
		this.toTime = toTime;
	}
	
	
	
	
}
