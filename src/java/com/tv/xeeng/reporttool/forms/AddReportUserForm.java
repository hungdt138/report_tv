package com.tv.xeeng.reporttool.forms;

import org.apache.struts.action.ActionForm;

public class AddReportUserForm extends ActionForm {
	private int userid;
	private String name;
	private String password;
	private int partnerId;
	private int userTypeId;
	private String passwordRetype;
	
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




	public int getPartnerId() {
		return partnerId;
	}




	public void setPartnerId(int partnerId) {
		this.partnerId = partnerId;
	}




	public int getUserTypeId() {
		return userTypeId;
	}




	public void setUserTypeId(int userTypeId) {
		this.userTypeId = userTypeId;
	}

	


	public int getUserid() {
		return userid;
	}




	public String getPasswordRetype() {
		return passwordRetype;
	}




	public void setPasswordRetype(String passwordRetype) {
		this.passwordRetype = passwordRetype;
	}




	public void setUserid(int userid) {
		this.userid = userid;
	}




	public AddReportUserForm() {
        super();
        // TODO Auto-generated constructor stub
    }
	
}
