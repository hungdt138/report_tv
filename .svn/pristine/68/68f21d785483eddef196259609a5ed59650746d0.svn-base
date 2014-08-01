package com.tv.xeeng.reporttool.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.tv.xeeng.reporttool.beans.GiffCodeBean;
import com.tv.xeeng.reporttool.dao.GiffCodeDAO;
import com.tv.xeeng.reporttool.forms.GiftCodeForm;

public class GetAGiftCodeAction extends Action {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
			
			GiftCodeForm _gcForm = (GiftCodeForm) form;
			boolean isValidate = true;
			int gcId;
			if(_gcForm.getBonusMoney().equals("") && _gcForm.getGiftCodeId().equals("")) {
				isValidate = false;
				request.setAttribute("msg_notifi", "Bạn phải điền lại thông tin tìm kiếm! ");
				return mapping.findForward("fail");
			} 
			if(isValidate) {
				if(!_gcForm.getGiftCodeId().equals("")) {
					gcId = Integer.parseInt(_gcForm.getGiftCodeId());
					if(gcId > 0) {
						try {
							GiffCodeBean gcBean = new GiffCodeDAO().getAGiftCodeById(gcId);
							if(!gcBean.getSerial().equals("")) {
								_gcForm.setSerial(gcBean.getSerial());
								_gcForm.setCreatedDate(gcBean.getCreatedDate() + " " + gcBean.getCreatedTime());
								if(gcBean.getUsedDate() == null) { 
									_gcForm.setUsedDate("NULL");
								} else {
									_gcForm.setUsedDate(gcBean.getUsedDate() + " " + gcBean.getUsedTime());
								}
								_gcForm.setBonusMoney(String.valueOf(gcBean.getBonusMoney()));
								_gcForm.setMarketingChannelId(gcBean.getMarketingChannelId());
								request.setAttribute("ttRc", 1);
								return mapping.findForward("success");
							} else {
								request.setAttribute("msg_notifi", "Bạn phải điền lại mã giftcode cần tìm kiếm! ");
								return mapping.findForward("fail");
							}
							
						} catch (Exception ex) {
							request.setAttribute("msg_notifi", "Đã xảy ra lỗi<br/> Hãy kiểm tra lại");
							return mapping.findForward("fail");
						}
					} else {
						request.setAttribute("msg_notifi", "Bạn phải điền lại số tiền hoặc mã giftcode cần tìm kiếm! ");
						return mapping.findForward("fail");
					}
					
				} else {
					int bnMoney = Integer.parseInt(_gcForm.getBonusMoney());
					if(bnMoney >= 0) {
						request.setAttribute("money", bnMoney);
						return mapping.findForward("searchbymoney");
					} else {
						request.setAttribute("msg_notifi", "Bạn phải điền lại số tiền cần tìm kiếm! ");
						return mapping.findForward("fail");
					}
				}
			} else {
				request.setAttribute("msg_notifi", "Bạn phải điền lại thông tin tìm kiếm! ");
				return mapping.findForward("fail");
			}
	}
	
}
