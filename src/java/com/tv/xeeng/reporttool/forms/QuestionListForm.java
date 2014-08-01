package com.tv.xeeng.reporttool.forms;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts.action.ActionForm;

import com.tv.xeeng.reporttool.beans.QuestionBean;

public class QuestionListForm extends ActionForm {
	private List<QuestionBean> qbList;
	
	public QuestionListForm() {
		this.qbList = new ArrayList<QuestionBean>();
	}

	public QuestionListForm(List<QuestionBean> qbList) {
		super();
		this.qbList = qbList;
	}

	public List<QuestionBean> getQbList() {
		return qbList;
	}

	public void setQbList(List<QuestionBean> qbList) {
		this.qbList = qbList;
	}

	
}
