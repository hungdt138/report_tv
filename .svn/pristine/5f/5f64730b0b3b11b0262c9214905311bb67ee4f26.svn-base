/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tv.xeeng.reporttool.forms;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author PHUCTV
 */
public class ChargingDetailReportForm extends org.apache.struts.action.ActionForm {
    private int partnerId;
    private boolean SMS;
    private boolean card;

    public int getPartnerId() {
        return partnerId;
    }

    public boolean isCard() {
        return card;
    }

    public boolean isSMS() {
        return SMS;
    }

    public void setCard(boolean card) {
        this.card = card;
    }

    public void setSMS(boolean SMS) {
        this.SMS = SMS;
    }

    public void setPartnerId(int partnerId) {
        this.partnerId = partnerId;
    }
    
    
    public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
        ActionErrors errors = new ActionErrors();
//        if (getName() == null || getName().length() < 1) {
//            errors.add("name", new ActionMessage("error.name.required"));
//            // TODO: add 'error.name.required' key to your resources
//        }
        return errors;
    }
}
