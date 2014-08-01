package com.tv.xeeng.reporttool.forms;

import java.util.List;

import org.apache.struts.action.ActionForm;

import com.tv.xeeng.reporttool.beans.WorkingUserBean;

public class WorkingUserListByNameForm extends ActionForm {
	private List<WorkingUserBean> wuList;

	public List<WorkingUserBean> getWuList() {
		return wuList;
	}

	public void setWuList(List<WorkingUserBean> wuList) {
		this.wuList = wuList;
	}
	
	
}
