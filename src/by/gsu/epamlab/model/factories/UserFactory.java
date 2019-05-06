package by.gsu.epamlab.model.factories;

import by.gsu.epamlab.ifaces.IUserDAO;
import by.gsu.epamlab.model.impl.HardcodedUserImpl;
import by.gsu.epamlab.model.impl.UserDBImpl;

public class UserFactory {
	private static IUserDAO userDAO;
	
	public static IUserDAO getClassFromFactory(String strDAO) {
		userDAO = "mem".equals(strDAO) ? new HardcodedUserImpl() : new UserDBImpl();
		return userDAO;
	}
	
}
