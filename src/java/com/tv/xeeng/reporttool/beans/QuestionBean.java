package com.tv.xeeng.reporttool.beans;

public class QuestionBean {
	private int questionId;
	private String detail;
	private String choix1;
	private String choix2;
	private String choix3;
	private String choix4;
	private int answer;
	private int levelId;
	private int domainId;
	private int totalPage;
	private int totalRecord;
	
	public QuestionBean() {
	}

	

	public QuestionBean(int questionId, String detail, String choix1,
			String choix2, String choix3, String choix4, int answer,
			int levelId, int domainId, int totalPage, int totalRecord) {
		super();
		this.questionId = questionId;
		this.detail = detail;
		this.choix1 = choix1;
		this.choix2 = choix2;
		this.choix3 = choix3;
		this.choix4 = choix4;
		this.answer = answer;
		this.levelId = levelId;
		this.domainId = domainId;
		this.totalPage = totalPage;
		this.totalRecord = totalRecord;
	}



	public int getQuestionId() {
		return questionId;
	}



	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}



	public String getDetail() {
		return detail;
	}



	public void setDetail(String detail) {
		this.detail = detail;
	}



	public String getChoix1() {
		return choix1;
	}



	public void setChoix1(String choix1) {
		this.choix1 = choix1;
	}



	public String getChoix2() {
		return choix2;
	}



	public void setChoix2(String choix2) {
		this.choix2 = choix2;
	}



	public String getChoix3() {
		return choix3;
	}



	public void setChoix3(String choix3) {
		this.choix3 = choix3;
	}



	public String getChoix4() {
		return choix4;
	}



	public void setChoix4(String choix4) {
		this.choix4 = choix4;
	}



	public int getAnswer() {
		return answer;
	}



	public void setAnswer(int answer) {
		this.answer = answer;
	}



	public int getLevelId() {
		return levelId;
	}



	public void setLevelId(int levelId) {
		this.levelId = levelId;
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



	public int getDomainId() {
		return domainId;
	}



	public void setDomainId(int domainId) {
		this.domainId = domainId;
	}

	
	
}
