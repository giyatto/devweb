package org.nhn.next.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.nhn.next.AbstractHttpServlet;
import org.nhn.next.user.User;


@WebServlet("/users/createform")
public class CreatFormUserServlet extends AbstractHttpServlet {
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	req.setAttribute("user", new User());
		forward(req, resp, "/form.jsp");
		
	}
	
}
