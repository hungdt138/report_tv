package com.tv.xeeng.reporttool.forms;

import java.util.List;

import org.apache.struts.action.ActionForm;

import com.tv.xeeng.reporttool.beans.WorkingUserBean;

public class WorkingUserListForm extends ActionForm {
	private List<WorkingUserBean> wuList;

	public WorkingUserListForm() {
		super();
		// TODO Auto-generated constructor stub
	}

	public WorkingUserListForm(List<WorkingUserBean> wuList) {
		super();
		this.wuList = wuList;
	}

	public List<WorkingUserBean> getWuList() {
		return wuList;
	}

	public void setWuList(List<WorkingUserBean> wuList) {
		this.wuList = wuList;
	}
	
	
}
