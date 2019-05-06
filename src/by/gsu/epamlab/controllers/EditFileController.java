package by.gsu.epamlab.controllers;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.gsu.epamlab.constants.Constants;
import by.gsu.epamlab.enums.FileAction;
import by.gsu.epamlab.exceptions.DAOException;
import by.gsu.epamlab.exceptions.ValidationException;
import by.gsu.epamlab.model.beans.User;

@WebServlet("/editFile")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, maxFileSize = 1024 * 1024 * 10, maxRequestSize = 1024 * 1024 * 50)
public class EditFileController extends BaseController {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter(Constants.KEY_FILE_ACTION);
		String idTask = request.getParameter(Constants.KEY_ID_TASK);
		String section = request.getParameter(Constants.KEY_SECTION);
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute(Constants.KEY_USER);
		String login = user.getLogin();
		String appPath = getServletContext().getRealPath(Constants.NAME_WEB_INF_ROOT);
		String directory = appPath + File.separator + Constants.SAVE_PATH + File.separator + login + File.separator + idTask;
//		System.out.println(directory);
		FileAction fileAction = FileAction.valueOf(action.toUpperCase());
		request.setAttribute(Constants.KEY_SECTION, section);
		try {
			fileAction.performAction(request, response, directory, idTask);
		} catch (DAOException | ValidationException | IOException e) {
			forwardError(Constants.JUMP_TASK_CONTROLLER, e.getMessage(), request, response);
		}
	}

}
