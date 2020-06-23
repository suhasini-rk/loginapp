package com.myproject.demo.myservelet;

import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import com.myproject.demo.bean.Login;
import com.myproject.demo.database.Logindao;

/**
 * Application Lifecycle Listener implementation class SessionExpirySession
 *
 */
@WebListener
public class SessionExpirySession implements HttpSessionListener {
    Login login=new Login();
    Logindao logindao=new Logindao();
	ServletContext ctx=null;
    public SessionExpirySession() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see HttpSessionListener#sessionCreated(HttpSessionEvent)
     */
    public void sessionCreated(HttpSessionEvent se)  { 
    	System.out.println("session expiry initiated.");
    	
    }

	/**
     * @see HttpSessionListener#sessionDestroyed(HttpSessionEvent)
     */ 
    public void sessionDestroyed(HttpSessionEvent se)  { 
         System.out.println("session exiry destroyed and updating status to DB");
         String username=(String)se.getSession().getAttribute("user");
         LoginServelet loginServelet=new LoginServelet();
         System.out.println("before----"+se.getSession().getServletContext().getAttribute("globleSessionId"));   
         se.getSession().getServletContext().setAttribute("globleSessionId", null);
         System.out.println("after----"+se.getSession().getServletContext().getAttribute("globleSessionId"));  
          login.setIsloggedin("N");
          login.setUsername(username);
          try {
			logindao.updateLogin(login);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
	
}
