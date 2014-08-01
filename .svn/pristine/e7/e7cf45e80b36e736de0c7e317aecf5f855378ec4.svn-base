package com.tv.xeeng.reporttool.action;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.tv.xeeng.reporttool.beans.GoldenHourBean;
import com.tv.xeeng.reporttool.dao.GoldenhourDAO;
import com.tv.xeeng.reporttool.forms.GoldenHourForm;

public class AddGoldenHourAction extends Action {
	private static Logger logger = Logger.getLogger(AddAdvertisingAction.class);
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
			
			GoldenHourForm _gdhForm = (GoldenHourForm) form;
			boolean isValidate = true;
			boolean isActive = true;
			if(_gdhForm.getFhour().equals("") || _gdhForm.getFminutes().equals("") || _gdhForm.getFsecond().equals("") ) {
				isValidate = false;
			} else if(_gdhForm.getThour().equals("") || _gdhForm.getTminutes().equals("") || _gdhForm.getTsecond().equals("") ) {
				isValidate = false;
			} else if(_gdhForm.getFromDate().equals("")) {
				isValidate = false;			
			} else if(_gdhForm.getToDate().equals("")) {
				isValidate = false;			
			} else if(_gdhForm.getType() < 0 ) {
				isValidate = false;			
			} else if(_gdhForm.getBonusAmount() < 0) {
				isValidate = false;			
			} else if(_gdhForm.getPartnerId() < 0) {
				isValidate = false;			
			} else if(_gdhForm.getExternalParam().equals("")) {
				isValidate = false;			
			} else if(_gdhForm.getDescription().equals("")) {
				isValidate = false;			
			} else if(!_gdhForm.getFromDate().equals("") && !_gdhForm.getToDate().equals("")) {
				Date fdate = new SimpleDateFormat("yyyy-MM-dd").parse(_gdhForm.getFromDate());
				Date tdate = new SimpleDateFormat("yyyy-MM-dd").parse(_gdhForm.getToDate());
				if(fdate.compareTo(tdate) > 0) {
					isValidate = false;
				} else if(fdate.compareTo(tdate) == 0) {
					if(Integer.parseInt(_gdhForm.getFhour()) > Integer.parseInt(_gdhForm.getThour())) {
						isValidate = false;
					} else if(Integer.parseInt(_gdhForm.getFhour()) == Integer.parseInt(_gdhForm.getThour())) {
						if(Integer.parseInt(_gdhForm.getFminutes()) > Integer.parseInt(_gdhForm.getTminutes())) {
							isValidate = false;
						} else if(Integer.parseInt(_gdhForm.getFminutes()) == Integer.parseInt(_gdhForm.getTminutes())) {
							if(Integer.parseInt(_gdhForm.getFsecond()) > Integer.parseInt(_gdhForm.getTsecond()) ) {
								isValidate = false;
							}
						}
					} 
				}
			}
			
			if(Integer.parseInt(_gdhForm.getFhour()) < 10) {
				String fHour = _gdhForm.getFhour();
				_gdhForm.setFhour("0"+ fHour);
			}
			if(Integer.parseInt(_gdhForm.getFminutes()) < 10) {
				String fM = _gdhForm.getFminutes();
				_gdhForm.setFminutes("0"+ fM);
			}
			if(Integer.parseInt(_gdhForm.getFsecond()) < 10) {
				String fS = _gdhForm.getFsecond();
				_gdhForm.setFsecond("0"+ fS);
			}
			
			if(Integer.parseInt(_gdhForm.getThour()) < 10) {
				String tHour = _gdhForm.getThour();
				_gdhForm.setThour("0"+ tHour);
			}
			if(Integer.parseInt(_gdhForm.getTminutes()) < 10) {
				String fM = _gdhForm.getTminutes();
				_gdhForm.setTminutes("0"+ fM);
			}
			if(Integer.parseInt(_gdhForm.getTsecond()) < 10) {
				String fS = _gdhForm.getTsecond();
				_gdhForm.setTsecond("0"+ fS);
			} 
			
			
			//===============
			if(isValidate == true) {
				
				String fDate = _gdhForm.getFromDate()+" "+_gdhForm.getFhour()+":"+_gdhForm.getFminutes()+":"+_gdhForm.getFsecond();
				String tDate = _gdhForm.getToDate()+" "+_gdhForm.getThour()+":"+_gdhForm.getTminutes()+":"+_gdhForm.getTsecond();
				GoldenHourBean gdhBean = new GoldenHourBean();
				
				
				gdhBean.setFromDate(fDate);
				gdhBean.setToDate(tDate);
				gdhBean.setActive(_gdhForm.getActive());
				gdhBean.setBonusAmount(_gdhForm.getBonusAmount());
				gdhBean.setPartnerId(_gdhForm.getPartnerId());
				gdhBean.setExternalParam(_gdhForm.getExternalParam());
				gdhBean.setDescription(_gdhForm.getDescription());
				gdhBean.setType(_gdhForm.getType());
				
				int rs = new GoldenhourDAO().addGoldenHour(gdhBean);
				if(rs > 0) {
					request.setAttribute("msg_notifi", "Đã thêm giờ vàng thành công");
					return mapping.findForward("success");
				} else {
					request.setAttribute("msg_notifi", "Quá trình thất bại <br/> Bạn hãy xem lại và <br/> Điền thông tin chính xác hơn");
					return mapping.findForward("fail");
				}
				
			} else {
				request.setAttribute("msg_notifi", "Quá trình thất bại <br/> Bạn hãy xem lại và <br/> Điền đầy đủ");
				return mapping.findForward("fail");
			}
	}
	
}
