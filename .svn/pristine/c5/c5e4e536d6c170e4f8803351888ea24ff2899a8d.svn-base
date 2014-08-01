package com.tv.xeeng.reporttool.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.tv.xeeng.reporttool.beans.GoldenHourBean;
import com.tv.xeeng.reporttool.dao.GoldenhourDAO;
import com.tv.xeeng.reporttool.forms.GoldenHourForm;

public class GetAGoldenHourAction extends Action {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
			GoldenHourForm _gdhForm = (GoldenHourForm) form;
			int gdhId = Integer.parseInt(request.getParameter("goldenhourId"));
			if(gdhId > 0) { 
				try {
					GoldenHourBean gdhBean = new GoldenhourDAO().getGoldenHourById(gdhId);
					_gdhForm.setId(gdhId);
					_gdhForm.setFromDate(gdhBean.getFromDate());
					_gdhForm.setToDate(gdhBean.getToDate());
					_gdhForm.setActive(gdhBean.getIsActive());
					_gdhForm.setBonusAmount(gdhBean.getBonusAmount());
					_gdhForm.setPartnerId(gdhBean.getPartnerId());
					_gdhForm.setExternalParam(gdhBean.getExternalParam());
					_gdhForm.setDescription(gdhBean.getDescription());
					_gdhForm.setType(gdhBean.getType());
					_gdhForm.setFromTime(gdhBean.getFromTime());
					_gdhForm.setToTime(gdhBean.getToTime());
					
					request.setAttribute("fh", gdhBean.getFhour());
					request.setAttribute("fm", gdhBean.getFminutes());
					request.setAttribute("fs", gdhBean.getFsecond());
					
					request.setAttribute("th", gdhBean.getThour());
					request.setAttribute("tm", gdhBean.getTminutes());
					request.setAttribute("ts", gdhBean.getTsecond());
					if( gdhBean.getIsActive() == true)
						request.setAttribute("at", 1);
					else
						request.setAttribute("at", 0);
					return mapping.findForward("success");
				} catch (Exception e) {
					request.setAttribute("msg_notifi", "Không tìm thấy kết quả với id = " + gdhId);
					return mapping.findForward("fail");
				}
					
				
			} else {
				request.setAttribute("msg_notifi", "Không tìm thấy kết quả với id = " + gdhId);
				return mapping.findForward("fail");
			}
	}
	
}
