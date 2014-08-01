/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tv.xeeng.reporttool.forms;


/**
 *
 * @author PHUCTV
 */
public class ChangePasswordForm extends org.apache.struts.action.ActionForm {

    private String passOld;
    private String passNew;
    private String passNewRetype;

    public String getPassOld() {
        return passOld;
    }

    public void setPassOld(String passOld) {
        this.passOld = passOld;
    }

    public String getPassNew() {
        return passNew;
    }

    public void setPassNew(String passNew) {
        this.passNew = passNew;
    }

    public String getPassNewRetype() {
        return passNewRetype;
    }

    public void setPassNewRetype(String passNewRetype) {
        this.passNewRetype = passNewRetype;
    }
    
    
}
