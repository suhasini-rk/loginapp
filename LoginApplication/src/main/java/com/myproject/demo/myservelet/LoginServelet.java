package com.myproject.demo.myservelet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.myproject.demo.bean.Login;
import com.myproject.demo.database.Logindao;


/**
 * Servlet implementation class LoginServelet
 */
public class LoginServelet extends HttpServlet {
	private static final long serialVersionUID = 102831973239L;
	// String globleSessionId=null;
   public void init() {
       //Logindao logindao=new Logindao();

   }
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	 Login login=new Login();
	 Logindao logindao=new Logindao();
	
	 String user=request.getParameter("user");
	 String pwd=request.getParameter("password");
	 login.setUsername(user);
	 login.setPassword(pwd);
	
	 HttpSession session=request.getSession();
	 String currSessionId=session.getId();
	
	 if( session.getServletContext().getAttribute("globleSessionId")==null){
		// globleSessionId=currSessionId;
		 session.getServletContext().setAttribute("globleSessionId", currSessionId);
	 }
	 
	 try {
		 
		 boolean isloggedIn=logindao.isAppLoggedin(login);
		 if(!isloggedIn && session.getServletContext().getAttribute("globleSessionId").equals(currSessionId)){
			 if(logindao.validate(login)){
				 System.out.println("user is not logged in"); 
				 session.setAttribute("user", user);
				 session.setMaxInactiveInterval(1*60);
				 response.sendRedirect("LoginSuccess.jsp");
				 System.out.println("user is logged in to application"); 
				 login.setIsloggedin("Y");
                 logindao.updateLogin(login);
			 }else{
				 RequestDispatcher rd = getServletContext().getRequestDispatcher("/login.html");
			  	 PrintWriter out= response.getWriter();
				 out.println("<font color=red>Either user name or password is wrong.</font>");
				 rd.include(request, response);
			 }

		 }else{
			 
			 response.sendRedirect("activeSession.jsp");
			 System.out.println("User already loggedin to another browser....."); 
			 session.invalidate();  
		 }
			} catch (ClassNotFoundException | SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}

}
