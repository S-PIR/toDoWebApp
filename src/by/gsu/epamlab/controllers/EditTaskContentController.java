package by.gsu.epamlab.controllers;

import java.io.IOException;
import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.gsu.epamlab.constants.Constants;
import by.gsu.epamlab.exceptions.DAOException;
import by.gsu.epamlab.exceptions.ValidationException;
import by.gsu.epamlab.ifaces.ITaskDAO;
import by.gsu.epamlab.model.beans.Task;
import by.gsu.epamlab.model.factories.TaskFactory;
import by.gsu.epamlab.model.managers.PropertyManager;

@WebServlet("/editContent")
public class EditTaskContentController extends BaseController {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String idTask = request.getParameter(Constants.KEY_ID);
		String contentTask = request.getParameter(Constants.KEY_CONTENT_TASK);
		String dateTask = request.getParameter(Constants.KEY_DATE_TASK);
		String editFunction = request.getParameter(Constants.KEY_EDIT_FUNCTION);
		//System.out.println(idTask + contentTask + dateTask + editFunction);
		try {
			Task task = checkAndGetTask(idTask, contentTask, dateTask);
			ITaskDAO taskDAO = TaskFactory.getClassFromFactory(PropertyManager.getStrDAO());
			taskDAO.editTaskContent(task, editFunction);
			response.sendRedirect(Constants.TASK_CONTROLLER);
		} catch (DAOException | ValidationException | IllegalArgumentException e) {
			forwardError(Constants.JUMP_TASK_CONTROLLER, e.getMessage(), request, response);
		}
	}

	private Task checkAndGetTask(String idTask, String contentTask, String dateTask) throws ValidationException {
		validateFormData(idTask, contentTask, dateTask);
		try {
			int id = Integer.parseInt(idTask);
			dateTask = dateTask.trim().replace(':', '-').replace('.', '-');
			Date date = Date.valueOf(dateTask);
			return new Task(id, contentTask, date);
		} catch (IllegalArgumentException e) {
			throw new ValidationException(Constants.DATE_FORMAT_ERROR);
		}
	}

}
