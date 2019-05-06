package by.gsu.epamlab.controllers;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.gsu.epamlab.constants.Constants;
import by.gsu.epamlab.exceptions.DAOException;
import by.gsu.epamlab.exceptions.ValidationException;
import by.gsu.epamlab.ifaces.ITaskDAO;
import by.gsu.epamlab.model.beans.Task;
import by.gsu.epamlab.model.beans.User;
import by.gsu.epamlab.model.factories.TaskFactory;
import by.gsu.epamlab.model.managers.PropertyManager;

@WebServlet("/addTask")
public class AddTaskController extends BaseController {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute(Constants.KEY_USER);
		String contentTask = request.getParameter(Constants.KEY_CONTENT_TASK);
		String dateTask = request.getParameter(Constants.KEY_DATE_TASK);
		String editFunction = request.getParameter(Constants.KEY_EDIT_FUNCTION);
		String section = request.getParameter(Constants.KEY_SECTION);
		try {
			Task task = checkAndGetTask(contentTask, dateTask, section);
			ITaskDAO taskDAO = TaskFactory.getClassFromFactory(PropertyManager.getStrDAO());
			taskDAO.addTask(task, user, editFunction);
			response.sendRedirect(Constants.TASK_CONTROLLER);
		} catch (DAOException | ValidationException | IllegalArgumentException e) {
			forwardError(Constants.JUMP_MAIN, e.getMessage(), request, response);
		}
	}

	private Task checkAndGetTask(String contentTask, String dateTask, String section) throws ValidationException {
		final long dayInMillis = 86400000;
		validateFormData(contentTask, section);
		if (Constants.TODAY.equals(section)) {
			Date date = new Date(System.currentTimeMillis());
			return new Task(contentTask, date);
		}
		if (Constants.TOMORROW.equals(section)) {
			Date date = new Date(System.currentTimeMillis() + dayInMillis);
			return new Task(contentTask, date);
		} else {
			try {
				dateTask = dateTask.trim().replace(':', '-').replace('.', '-');
				Date date = Date.valueOf(dateTask);
				return new Task(contentTask, date);
			} catch (IllegalArgumentException e) {
				throw new ValidationException(Constants.DATE_FORMAT_ERROR);
			}
		}
	}

}
