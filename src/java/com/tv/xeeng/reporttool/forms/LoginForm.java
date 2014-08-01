/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tv.xeeng.reporttool.forms;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

/**
 *
 * @author PHUCTV
 */
public class LoginForm extends org.apache.struts.action.ActionForm {

    String username;
    String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    /**
     *
     */
    public LoginForm() {
        super();
        // TODO Auto-generated constructor stub
    }
    public ActionErrors validate(ActionMapping mapping, HttpServletRequest request) {
        ActionErrors errors = new ActionErrors();
        if (getUsername() == null || getUsername().length() < 1 || getPassword() == null || getPassword().length() < 1) {
            errors.add("invalid", new ActionMessage("login.invalid"));
            return errors;
        }
        Integer failCount = 0;
        if(request.getSession().getAttribute("failCount") != null){
             failCount = (Integer)request.getSession().getAttribute("failCount");
        }        
        
        return errors;
    }
}
