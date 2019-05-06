package by.gsu.epamlab.controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.gsu.epamlab.controllers.BaseController;
import by.gsu.epamlab.ifaces.IUserDAO;
import by.gsu.epamlab.model.beans.User;
import by.gsu.epamlab.model.factories.UserFactory;
import by.gsu.epamlab.model.managers.PropertyManager;
import by.gsu.epamlab.constants.*;
import by.gsu.epamlab.exceptions.DAOException;
import by.gsu.epamlab.exceptions.ValidationException;

public class RegistrateController extends BaseController {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String login = request.getParameter(ConstantsJSP.KEY_LOGIN).trim();
		String name = request.getParameter(ConstantsJSP.KEY_NAME).trim();
		String email = request.getParameter(ConstantsJSP.KEY_EMAIL).trim();
		String password = request.getParameter(ConstantsJSP.KEY_PASSWORD).trim();
		String passwordRep = request.getParameter(ConstantsJSP.KEY_REP_PASSWORD).trim();
		try {
			validateFormData(login, name, email, password, passwordRep);
			checkPasswordEqual(password, passwordRep);
			IUserDAO userDAO = UserFactory.getClassFromFactory(PropertyManager.getStrDAO());
			User user = userDAO.addAndGetUser(login, name, password, email);
			authorizeUser(user, request, response);
		} catch (DAOException | ValidationException e) {
			forwardError(Constants.JUMP_INDEX, e.getMessage(), request, response);
		}

	}
	
	private void checkPasswordEqual(String password, String passwordRep) throws ValidationException {
		if (!password.equals(passwordRep)) {
			throw new ValidationException(Constants.PASSWORD_REPEAT_ERROR);
		}
	}
	

}
