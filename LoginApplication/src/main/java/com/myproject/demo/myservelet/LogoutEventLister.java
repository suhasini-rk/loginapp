package com.myproject.demo.myservelet;

import javax.servlet.ServletContext;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * Application Lifecycle Listener implementation class LogoutEventLister
 *
 */
@WebListener
public class LogoutEventLister implements HttpSessionListener {
	boolean isSessionExpired=false;
	  ServletContext ctx=null;  
    public LogoutEventLister() {
        // TODO Auto-generated constructor stub
    }

	
    public void sessionCreated(HttpSessionEvent se)  { 
     System.out.println("logout initiated");
     isSessionExpired=true;
     ctx=se.getSession().getServletContext();  
     ctx.setAttribute("sessionExpiryFlag", isSessionExpired);  
     
    }

	
    public void sessionDestroyed(HttpSessionEvent se)  { 
    	System.out.println("logout destroyed");
    }
	
}
