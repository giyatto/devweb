package org.nhn.next.user;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.nhn.next.AbstractHttpServlet;

@WebServlet("/users/updateform")
public class UpdataFormUserServlet extends AbstractHttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		HttpSession session=req.getSession();
		//String userId = SessionUtils.getStringValue(session, LoginServlet.SESSION_USER_ID);
		String userId = (String) session.getAttribute("userId");
		if(userId==null){
			resp.sendRedirect("/");
			return;
		}
		
		
		UserDAO userDAO = new UserDAO();
        try {
        	User user = userDAO.findByUserId(userId);
        	
        	req.setAttribute("user", user);
    		forward(req, resp, "/form.jsp");
	        
        } catch (SQLException e) {
        }

	}
}
