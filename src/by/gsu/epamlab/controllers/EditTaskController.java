package by.gsu.epamlab.controllers;

import java.io.File;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.gsu.epamlab.constants.Constants;
import by.gsu.epamlab.exceptions.DAOException;
import by.gsu.epamlab.exceptions.ValidationException;
import by.gsu.epamlab.ifaces.ITaskDAO;
import by.gsu.epamlab.model.beans.User;
import by.gsu.epamlab.model.factories.TaskFactory;
import by.gsu.epamlab.model.managers.DeleteFileManager;
import by.gsu.epamlab.model.managers.PropertyManager;

@WebServlet("/editTask")
public class EditTaskController extends BaseController {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String section = request.getParameter(Constants.KEY_SECTION);
		String editFunction = request.getParameter(Constants.KEY_EDIT_FUNCTION);
		String[] idTasks = request.getParameterValues(Constants.KEY_EDIT_CHECKBOX);
		try {
			ITaskDAO taskDAO = TaskFactory.getClassFromFactory(PropertyManager.getStrDAO());
			validateFormData(idTasks);
			validateFormData(editFunction);
			taskDAO.editTask(idTasks, editFunction);
			request.setAttribute(Constants.KEY_SECTION, section);
			if (Constants.FUNCTION_REMOVE.equals(editFunction)) {
				HttpSession session = request.getSession();
				User user = (User) session.getAttribute(Constants.KEY_USER);
				String login = user.getLogin();
				String appPath = getServletContext().getRealPath(Constants.NAME_WEB_INF_ROOT);
				String mainDir = appPath + File.separator + Constants.SAVE_PATH + File.separator + login;
				DeleteFileManager.deleteDirsAndFiles(idTasks, mainDir);
			}
			forward(Constants.JUMP_TASK_CONTROLLER, request, response);
		} catch (DAOException | ValidationException e) {
			forwardError(Constants.JUMP_TASK_CONTROLLER, e.getMessage(), request, response);
		}
	}

}
