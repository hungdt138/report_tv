package com.tv.xeeng.reporttool.action;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.tv.xeeng.reporttool.beans.UserBean;
import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.tv.xeeng.reporttool.beans.AdvertisingBean;
import com.tv.xeeng.reporttool.dao.AdvertisDAO;
import com.tv.xeeng.reporttool.forms.AdvertisingForm;

public class AddAdvertisingAction extends Action {
	 private static Logger logger = Logger.getLogger(AddAdvertisingAction.class);

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
            UserBean loggedInUser = (UserBean) request.getSession().getAttribute("loggedInUser");

			AdvertisingForm _advForm = (AdvertisingForm) form;

			boolean isValidate = true;
			if(_advForm.getContent().equals("")) {
				isValidate = false;
			} else if(_advForm.getStartDate().equals("")) {
				isValidate = false;
			} else if(_advForm.getShour().equals("") || _advForm.getSminutes().equals("") || _advForm.getSsecond().equals("") ) {
				isValidate = false;
			} else if(_advForm.getEndDate().equals("")) {
				isValidate = false;
			} else if(_advForm.getEhour().equals("") || _advForm.getEminutes().equals("") || _advForm.getEsecond().equals("") ) {
				isValidate = false;
			}
			if(Integer.parseInt(_advForm.getShour()) < 10) {
				String sHour = _advForm.getShour();
				_advForm.setShour("0"+ sHour);
			}
			if(Integer.parseInt(_advForm.getSminutes()) < 10) {
				String sM = _advForm.getSminutes();
				_advForm.setSminutes("0"+ sM);
			}
			if(Integer.parseInt(_advForm.getSsecond()) < 10) {
				String sS = _advForm.getSsecond();
				_advForm.setSsecond("0"+ sS);
			}
			
			
			if(Integer.parseInt(_advForm.getEhour()) < 10) {
				String eHour = _advForm.getEhour();
				_advForm.setEhour("0"+ eHour);
			}
			if(Integer.parseInt(_advForm.getEminutes()) < 10) {
				String eM = _advForm.getEminutes();
				_advForm.setEminutes("0"+ eM);
			}
			if(Integer.parseInt(_advForm.getEsecond()) < 10) {
				String eS = _advForm.getEsecond();
				_advForm.setEsecond("0"+ eS);
			}
			//testing the datetime
			Date startDate = new Date();
			startDate = new SimpleDateFormat("yyyy-MM-dd").parse(_advForm.getStartDate());
			Date endDate = new Date();
			endDate = new SimpleDateFormat("yyyy-MM-dd").parse(_advForm.getEndDate());
			
		/*	Date startTime = new Date();
			startTime = new SimpleDateFormat("HH:mm:ss").parse(_advForm.getShour() + );
			
			if(startDate.compareTo(endDate) > 0){
				isValidate= false;
			} else if()  {
				
			}*/
			
			if(isValidate == true) {
				
				String sDate = _advForm.getStartDate()+" "+_advForm.getShour()+":"+_advForm.getSminutes()+":"+_advForm.getSsecond();
				String eDate = _advForm.getEndDate()+" "+_advForm.getEhour()+":"+_advForm.getEminutes()+":"+_advForm.getEsecond();
				logger.info("sDate: " + sDate + "eDate : " + eDate);
				AdvertisingBean advBean = new AdvertisingBean();
				advBean.setContent(_advForm.getContent());
				advBean.setDisplay(_advForm.getIsDisplay());
				advBean.setPartnerId(_advForm.getPartnerId());
				advBean.setStartDate(sDate);
				advBean.setEndDate(eDate);
				
				int rs = new AdvertisDAO().addAdvertising(advBean, loggedInUser);
				if(rs > 0) {
					request.setAttribute("msg_notifi", "Đã thêm thành công tin Quảng cáo");
					return mapping.findForward("success");
				} else{
					request.setAttribute("msg_notifi", "Đã có lỗi xảy ra!<br/> Bạn hãy kiểm tra lại");
					return mapping.findForward("fail");
				}
				
			} else {
				request.setAttribute("msg_notifi", "Bạn bắt buộc phải điền chính xác<br/>Nội dung quảng cáo<br/>Ngày bắt đầu<br/>Ngày kết thúc");
				return mapping.findForward("fail");
			}
	}

}
