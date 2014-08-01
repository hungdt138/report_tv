package com.tv.xeeng.reporttool.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.tv.xeeng.reporttool.dao.GiffCodeDAO;
import com.tv.xeeng.reporttool.forms.GiftCodeListForm;

public class GetGiftCodeByMoneyAction extends Action {
	 private static Logger logger = Logger.getLogger(AccountAction.class);
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
			GiftCodeListForm _gcLForm = (GiftCodeListForm) form;
			if(request.getAttribute("money") != null || request.getParameter("money") != null) {
				int money = 0;
				if(request.getAttribute("money") != null) {
					money = Integer.parseInt(request.getAttribute("money").toString());
				} else {
					money = Integer.parseInt(request.getParameter("money").toString());
				}
				if(request.getParameter("page") != null && money >= 0) {
					int pageC = Integer.parseInt(request.getParameter("page"));
					if(pageC > 0) { 
						_gcLForm.setGcList(new GiffCodeDAO().getGiftCodeByName(pageC, money));
						if(_gcLForm.getGcList().size() > 0) {
//							logger.info("Đã tìm kiếm theo money! moneyS = " + money);
							request.setAttribute("page", pageC);
							request.setAttribute("moneyS", money);
							request.setAttribute("ttRc", _gcLForm.getGcList().get(0).getTotalRecord());
							return mapping.findForward("success");
						} else {
							request.setAttribute("msg_notifi", "Không tìm thấy bản ghi nào theo số tiền đã nhập <br/> Bạn hãy tìm theo dữ liệu khác");
							return mapping.findForward("fail");
						}
					} else {
						request.setAttribute("msg_notifi", "Trang yêu cầu không hợp lệ <br/> Bạn đừng nghịch :))");
						return mapping.findForward("fail");
					}
					
				} else {
					_gcLForm.setGcList(new GiffCodeDAO().getGiftCodeByName(1, money));
					if(_gcLForm.getGcList().size() > 0) {
						request.setAttribute("page", 1);
						request.setAttribute("moneyS", money);
						request.setAttribute("ttRc", _gcLForm.getGcList().get(0).getTotalRecord());
						return mapping.findForward("success");
					} else {
						request.setAttribute("msg_notifi", "Không tìm thấy bản ghi nào theo số tiền đã nhập <br/> Bạn hãy tìm theo dữ liệu khác");
						return mapping.findForward("fail");
					}
				}
			} else {
				request.setAttribute("msg_notifi", "Truy cập không hợp lệ!");
				return mapping.findForward("fail");
			}
			
	}
	
}
