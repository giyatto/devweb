package org.nhn.next.web;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.nhn.next.AbstractHttpServlet;
import org.nhn.next.user.PasswordEmptyException;
import org.nhn.next.user.PasswordMismatchException;
import org.nhn.next.user.User;
import org.nhn.next.user.UserEmptyException;
import org.nhn.next.user.UsersNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebServlet("/users/login")
public class LoginServlet extends AbstractHttpServlet {

	public static final String SESSION_USER_ID = "userId";
	static final Logger logger = LoggerFactory.getLogger(LoginServlet.class);

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String userId = req.getParameter(SESSION_USER_ID);
		String password = req.getParameter("password");
		String url = "/login.jsp";

		logger.debug("ID : {} / password : {}", userId, password);

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
