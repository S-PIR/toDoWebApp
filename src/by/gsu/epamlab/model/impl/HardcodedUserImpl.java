package by.gsu.epamlab.model.impl;


import java.util.HashMap;
import java.util.Map;

import by.gsu.epamlab.ifaces.IUserDAO;
import by.gsu.epamlab.model.beans.User;

public class HardcodedUserImpl implements IUserDAO{
	private static Map<User, String> users = new HashMap<User, String>();
	
	static {
		users.put(new User("sys", "sys", "sys@mail.by"), "111");
		users.put(new User("user", "user", "user@mail.by"), "000");
	}
//	@Override
//	public User getUser(String login, String password) {
//		final String ADMIN_LOGIN = "sys", ADMIN_PASSWORD = "111";
//		final String USER_LOGIN = "user", USER_PASSWORD = "000";
//		User user = new User(login);
//		Role role = Role.getRole(login, password, 
//				ADMIN_LOGIN, ADMIN_PASSWORD, Role.ADMIN);
//		if (role == Role.VISITOR) {
//			role = Role.getRole(login, password, 
//					USER_LOGIN, USER_PASSWORD, Role.USER);
//		}
//		return new User(login, role);
//	}

//	@Override
//	public boolean addUser(User user, String password) {
//		boolean flag = false;
//		if (!isFoundLogin(user)) {
//			users.put(user, password);
//			flag = true;
//		}
//		return flag;
//	}
//
//	@Override
//	public boolean isFoundUser(User user, String password) {
//		boolean flag = false;
//		String pass = users.get(user);
//		if (pass != null) {
//			flag = password.equals(pass);
//		}
//		return flag;
//	}
	

	@Override
	public User getUser(String login, String password) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User addAndGetUser(String login, String name, String password, String email) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
