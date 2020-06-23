package com.myproject.demo.myservelet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.myproject.demo.bean.Login;
import com.myproject.demo.database.Logindao;

/**
 * Servlet implementation class LogoutServlet
 */
public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
  	Login login=new Login();
  	Logindao logindao=new Logindao();
  	response.setContentType("text/html");  
   
  	PrintWriter out=response.getWriter();  
    request.getRequestDispatcher("login.html").include(request, response);  

    HttpSession session=request.getSession(); 
    String username=(String)session.getAttribute("user");
    session.invalidate();  
    
    out.print("You are successfully logged out!");  
    ServletContext ctx=getServletContext();  
    boolean logedoutStatus=(boolean) ctx.getAttribute("sessionExpiryFlag");  
    System.out.println("logged out--->"+logedoutStatus);
    try {
    	login.setUsername(username);
    	login.setIsloggedin("N");
		logindao.updateLogin(login);
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

    }

}
