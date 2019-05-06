package by.gsu.epamlab.ifaces;

import by.gsu.epamlab.model.beans.User;

public interface IUserDAO {
	public User getUser(String login, String password);
	public User addAndGetUser(String login, String name, String password, String email);
	
}
