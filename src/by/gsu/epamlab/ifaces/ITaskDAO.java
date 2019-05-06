package by.gsu.epamlab.ifaces;

import java.util.List;

import by.gsu.epamlab.model.beans.Task;
import by.gsu.epamlab.model.beans.User;

public interface ITaskDAO {
	List<Task> getTasks(User user, String param);
	void editTask(String[] idTasks, String editFunction);
	boolean addTask(Task task, User user, String editFunction);
	boolean editTaskContent(Task task, String editFunction);
	boolean editFile(String idTask, String fileName);
}
