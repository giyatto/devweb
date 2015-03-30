package org.nhn.next.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/users/update")
public class UpdateUserServlet extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		// 사용자의 로그인 정보를 확인
		HttpSession session=req.getSession();
		String sessionUserId = SessionUtils.getStringValue(session, LoginServlet.SESSION_USER_ID);
		if(sessionUserId==null){
			resp.sendRedirect("/");
			return;
		}
		
		// 사용자가 업데이트를 위해 전달한 userId와 현재 로그인을 한 세션에 들어있는 userId가 같아야 한다.
		String userId = req.getParameter("userId");
		
		if(!sessionUserId.equals("userId")){
			resp.sendRedirect("/");
			return;
		}
		
		String password = req.getParameter("password");
		String name = req.getParameter("name");
		String email = req.getParameter("email");
			
		
		User user = new User(userId, password, name, email);
		UserDAO userDAO = new UserDAO();
	        userDAO.updateUser(user);
		
		resp.sendRedirect("/");
	}
}
