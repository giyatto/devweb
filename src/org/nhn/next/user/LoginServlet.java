package org.nhn.next.user;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.nhn.next.AbstractHttpServlet;

@WebServlet("/users/login")
public class LoginServlet extends AbstractHttpServlet {
	
	public static final String SESSION_USER_ID = "userId";

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		String userId = req.getParameter(SESSION_USER_ID);
		String password = req.getParameter("password");
		String url = "/login.jsp";
		
		
		//System.out.println("ID :" + userId + " / password : " + password);
		
		
		try {
			try {
	            User.login(userId, password);
            } catch (SQLException e) {
	            e.printStackTrace();
            }
			HttpSession session = req.getSession();
			session.setAttribute(SESSION_USER_ID, userId);
			
				
			resp.sendRedirect("/");
				
		} catch (UsersNotFoundException | PasswordMismatchException | UserEmptyException | PasswordEmptyException e) {
			req.setAttribute("errorMessage", e.getMessage());
			forward(req, resp, url);
		}
	}
}
