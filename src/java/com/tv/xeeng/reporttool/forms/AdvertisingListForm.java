package com.tv.xeeng.reporttool.forms;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts.action.ActionForm;

import com.tv.xeeng.reporttool.beans.AdvertisingBean;

public class AdvertisingListForm extends ActionForm {
	private List<AdvertisingBean> advList ;
	
	public AdvertisingListForm() {
		this.advList = new ArrayList<AdvertisingBean>();
	}

	public AdvertisingListForm(List<AdvertisingBean> advList) {
		this.advList = advList;
	}

	public List<AdvertisingBean> getAdvList() {
		return advList;
	}

	public void setAdvList(List<AdvertisingBean> advList) {
		this.advList = advList;
	}
	
	
	
}
