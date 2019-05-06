package by.gsu.epamlab.controllers;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.gsu.epamlab.constants.Constants;
import by.gsu.epamlab.exceptions.ValidationException;
import by.gsu.epamlab.model.beans.User;

public class BaseController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void forward(String url, HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
		rd.forward(request, response);
	}
	
	protected void forwardError(String errorPage, String message, HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setAttribute(Constants.KEY_ERROR_MESSAGE, message);
		forward(errorPage, request, response);
	}
	
	protected void authorizeUser(User user, HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		session.setAttribute(Constants.KEY_USER, user);
		response.sendRedirect(Constants.TASK_CONTROLLER);
	}
	
	protected void validateFormData(String ... inputs) throws ValidationException {
		if (inputs == null) {
			throw new ValidationException(Constants.NULL_ERROR);
		}
		for(String input : inputs) {
			if(input == null) {
				throw new ValidationException(Constants.NULL_ERROR);
			}
			if(input.trim().isEmpty()) {
				throw new ValidationException(Constants.EMPTY_ERROR);		
			}
		}
	}
	
	
}
