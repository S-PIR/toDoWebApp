package by.gsu.epamlab.controllers;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.gsu.epamlab.exceptions.DAOException;
import by.gsu.epamlab.exceptions.ValidationException;
import by.gsu.epamlab.controllers.BaseController;
import by.gsu.epamlab.ifaces.IUserDAO;
import by.gsu.epamlab.model.beans.User;
import by.gsu.epamlab.model.factories.UserFactory;
import by.gsu.epamlab.model.managers.PropertyManager;
import by.gsu.epamlab.constants.*;

public class LoginController extends BaseController {
	private static final long serialVersionUID = 1L;

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		String param = config.getInitParameter(Constants.KEY_DAO);
		PropertyManager.init(param);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String login = request.getParameter(ConstantsJSP.KEY_LOGIN).trim();
		String password = request.getParameter(ConstantsJSP.KEY_PASSWORD).trim();
		try {
			IUserDAO userDAO = UserFactory.getClassFromFactory(PropertyManager.getStrDAO());
			validateFormData(login, password);
			User user = userDAO.getUser(login, password);
			authorizeUser(user, request, response);
		} catch (DAOException | ValidationException e) {
			forwardError(Constants.JUMP_INDEX, e.getMessage(), request, response);
		}
	}

}
