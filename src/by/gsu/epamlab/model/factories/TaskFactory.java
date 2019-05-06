package by.gsu.epamlab.model.factories;

import by.gsu.epamlab.ifaces.ITaskDAO;
import by.gsu.epamlab.model.impl.TaskDBImpl;

public class TaskFactory {
	private static ITaskDAO taskDAO;
	
	public static ITaskDAO getClassFromFactory(String strDAO) {
		taskDAO = new TaskDBImpl();
		return taskDAO;
	}
}
