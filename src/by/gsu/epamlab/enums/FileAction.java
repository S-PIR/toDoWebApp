package by.gsu.epamlab.enums;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import by.gsu.epamlab.constants.Constants;
import by.gsu.epamlab.exceptions.ValidationException;
import by.gsu.epamlab.ifaces.ITaskDAO;
import by.gsu.epamlab.model.factories.TaskFactory;
import by.gsu.epamlab.model.managers.PropertyManager;

public enum FileAction {
	UPLOAD {
		public void performAction(HttpServletRequest request, HttpServletResponse response, String directory, String idTask)
				throws ValidationException, IOException, ServletException {
			final Part filePart = request.getPart(Constants.KEY_FILE);
			final String fileName = filePart.getSubmittedFileName();
			OutputStream out = null;
			InputStream fileContent = null;
			File dirFile = new File(directory);
			if (!dirFile.exists()) {
				dirFile.mkdirs();
			}
			try {
				out = new FileOutputStream(new File(dirFile + File.separator + fileName));
				fileContent = filePart.getInputStream();
//				System.out.println(dirFile);
				int read = 0;
				final byte[] bytes = new byte[1024];
				while ((read = fileContent.read(bytes)) != -1) {
					out.write(bytes, 0, read);
				}
				ITaskDAO taskDAO = TaskFactory.getClassFromFactory(PropertyManager.getStrDAO());
				taskDAO.editFile(idTask, fileName);
				response.sendRedirect(Constants.TASK_CONTROLLER);
			} catch (FileNotFoundException e) {
				throw new ValidationException(Constants.NO_FILE_ERROR);
			} finally {
				if (out != null) {
					out.close();
				}
				if (fileContent != null) {
					fileContent.close();
				}
			}
		}
	},

	DOWNLOAD {
		public void performAction(HttpServletRequest request, HttpServletResponse response, String directory, String idTask)
				throws IOException, ValidationException {
			String fileName = request.getParameter(Constants.KEY_FILE_NAME_TASK);
			response.setContentType("APPLICATION/OSTET-STREAM");
			response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
			OutputStream out = null;
			FileInputStream fis = null;
			try {
				out = response.getOutputStream();
				File dirFile = new File(directory + File.separator + fileName);
				fis = new FileInputStream(dirFile);
				int i;
				while ((i = fis.read()) != -1) {
					out.write(i);
				}
			} catch (IOException e) {
				throw new ValidationException(e.getMessage());
			} finally {
				if (fis != null) {
					fis.close();
				}
				if (out != null) {
					out.close();
				}
			}

		}
	},

	DELETE {
		public void performAction(HttpServletRequest request, HttpServletResponse response, String directory, String idTask)
				throws IOException {
			File dirFile = new File(directory);
			deleteFile(dirFile);
			ITaskDAO taskDao = TaskFactory.getClassFromFactory(PropertyManager.getStrDAO());
			taskDao.editFile(idTask, Constants.NO_FILE);
			if (!dirFile.exists()) {
				response.sendRedirect(Constants.TASK_CONTROLLER);
			}
		}
	};

	public abstract void performAction(HttpServletRequest request, HttpServletResponse response, String directory,
			String idTask) throws IOException, ServletException, ValidationException;

	
	private static void deleteFile(File element) {
		if (element.isDirectory()) {
			for (File sub : element.listFiles()) {
				deleteFile(sub);
			}
		}
		element.delete();
	}
}
