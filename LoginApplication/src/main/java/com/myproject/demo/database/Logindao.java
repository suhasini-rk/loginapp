package com.myproject.demo.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.myproject.demo.bean.Login;

public class Logindao {
	public Connection getConnection() throws SQLException{
		Connection conn =null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			 conn = DriverManager
		            .getConnection("jdbc:mysql://localhost:3306/myschema?allowPublicKeyRetrieval=true&amp;useSSL=false", "root", "suhasini@27");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return conn;
				
	}
	
	public boolean validate(Login login) throws ClassNotFoundException {
        boolean status = false;


        try (

             PreparedStatement preparedStatement = getConnection()
            .prepareStatement("select * from login where username = ? and password = ? ")) {

             preparedStatement.setString(1, login.getUsername());
             preparedStatement.setString(2, login.getPassword());

            System.out.println(preparedStatement);

            ResultSet rs = preparedStatement.executeQuery();
            status = rs.next();
            if(status){
            	/*PreparedStatement pstmt=connection.prepareStatement("update login set isloggedin='Y' where username='"+login.getUsername()+"' ");
            	pstmt.execute(); */
            }

        } catch (SQLException e) {

        }
        return status;
    }
	public boolean isAppLoggedin(Login login) throws ClassNotFoundException{
		boolean status = false;

    try (
         PreparedStatement preparedStatement = getConnection()
        .prepareStatement("select * from login where username = ?")) {
         preparedStatement.setString(1, login.getUsername());
         System.out.println(preparedStatement);
          ResultSet rs = preparedStatement.executeQuery();
          while(rs!=null && rs.next()){
        	  if(rs.getString("isloggedin").equals("Y")){
        		  status= true;
        	  }
          }
    } catch (SQLException e) {}
    
        return status;
    }
	public void updateLogin(Login login) throws SQLException{
	 
	    String updateQuery="update login set isloggedin='"+login.getIsloggedin()+"' where username ='"+login.getUsername()+"'  ";
	    PreparedStatement pstmt=null;
		 try {
			 pstmt = getConnection().prepareStatement(updateQuery);
			int row= pstmt.executeUpdate();
			System.out.println("row--->"+row);
			 
		} catch (SQLException e) {
			pstmt.close();
		}

  }




}
