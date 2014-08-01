package com.tv.xeeng.reporttool.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.tv.xeeng.reporttool.beans.AdvertisingBean;
import com.tv.xeeng.reporttool.dao.AdvertisDAO;
import com.tv.xeeng.reporttool.forms.AdvertisingForm;

public class GetAdvertisingAction extends Action {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
			AdvertisingForm _advForm = (AdvertisingForm) form;
			int advId = Integer.parseInt(request.getParameter("advertisingId"));
			if(advId > 0 ) {
				AdvertisingBean advBean = new AdvertisDAO().getAdvById(advId);
				_advForm.setAdvertisingId(advBean.getAdvertisingId());
				_advForm.setCreatedDate(advBean.getCreatedDate());
				_advForm.setContent(advBean.getContent());
				_advForm.setIsDisplay(advBean.isDisplay());
				_advForm.setPartnerId(advBean.getPartnerId());
				_advForm.setStartDate(advBean.getStartDate());
				_advForm.setEndDate(advBean.getEndDate());
				_advForm.setStartTime(advBean.getStartTime());
				_advForm.setEndTime(advBean.getEndTime());
				_advForm.setCreatedTime(advBean.getCreatedTime());
				
				request.setAttribute("ch", advBean.getChour());
				request.setAttribute("cm", advBean.getCminutes());
				request.setAttribute("cs", advBean.getCsecond());
				
				request.setAttribute("sh", advBean.getShour());
				request.setAttribute("sm", advBean.getSminutes());
				request.setAttribute("ss", advBean.getSsecond());
				
				request.setAttribute("eh", advBean.getEhour());
				request.setAttribute("em", advBean.getEminutes());
				request.setAttribute("es", advBean.getEsecond());
				return mapping.findForward("success");
			} else {
				request.setAttribute("msg_notifi", "Đã có lỗi xảy ra!<br/> Bạn hãy kiểm tra lại");
				return mapping.findForward("fail");
			}
			
	}
	
}
