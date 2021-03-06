package org.nhn.next.web;

import java.io.IOException;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.nhn.next.user.User;
import org.nhn.next.user.UserDAO;

import core.MyValidatorFactory;

@WebServlet("/users/create")
public class CreatUserServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String userId = req.getParameter("userId");
		String password = req.getParameter("password");
		String name = req.getParameter("name");
		String email = req.getParameter("email");

		User user = new User(userId, password, name, email);
		Validator validator = MyValidatorFactory.createValidator();
		Set<ConstraintViolation<User>> constraintViolations = validator.validate(user);
		if (constraintViolations.size() > 0) {
			req.setAttribute("user", user);
			String errorMessage = constraintViolations.iterator().next().getMessage();
			forwardJSP(req, resp, errorMessage);
			return;
		}
		UserDAO userDAO = new UserDAO();
		userDAO.addUser(user);
	}

	private void forwardJSP(HttpServletRequest req, HttpServletResponse resp, String errorMessage)
	        throws ServletException, IOException {
		req.setAttribute("errorMessage", errorMessage);
		RequestDispatcher rd = req.getRequestDispatcher("/form.jsp");
		rd.forward(req, resp);
	}

}
