package by.gsu.epamlab.controllers;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.gsu.epamlab.constants.Constants;
import by.gsu.epamlab.exceptions.DAOException;
import by.gsu.epamlab.ifaces.ITaskDAO;
import by.gsu.epamlab.model.beans.Task;
import by.gsu.epamlab.model.beans.User;
import by.gsu.epamlab.model.factories.TaskFactory;
import by.gsu.epamlab.model.managers.PropertyManager;

@WebServlet("/task")
public class TaskController extends BaseController {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
        User user = (User) session.getAttribute(Constants.KEY_USER);
        String section = request.getParameter(Constants.KEY_SECTION);
        if (section == null) {
			section = Constants.TODAY.toUpperCase();
		}
        try {
        	ITaskDAO taskDAO = TaskFactory.getClassFromFactory(PropertyManager.getStrDAO());
        	List<Task> tasks = taskDAO.getTasks(user, section); 
			request.setAttribute(Constants.KEY_TASKS, tasks);
			request.setAttribute(Constants.KEY_SECTION, section);
			forward(Constants.JUMP_MAIN, request, response);
		} catch (DAOException e) {
			forwardError(Constants.JUMP_MAIN, e.getMessage(), request, response);
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
