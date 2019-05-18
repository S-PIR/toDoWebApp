package by.gsu.epamlab.model.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import by.gsu.epamlab.constants.Constants;
import by.gsu.epamlab.constants.SqlQuery;
import by.gsu.epamlab.exceptions.DAOException;
import by.gsu.epamlab.ifaces.IUserDAO;
import by.gsu.epamlab.model.beans.User;
import by.gsu.epamlab.model.managers.PoolConnectionManager;

public class UserDBImpl implements IUserDAO {
	private static final Logger LOGGER = Logger.getLogger(UserDBImpl.class.getName());

	@Override
	public User addAndGetUser(String login, String name, String password, String email) {
		int isAddedUser = 0;
		try (Connection cn = PoolConnectionManager.getConnection();
				PreparedStatement psFindUser = cn.prepareStatement(SqlQuery.SELECT_FOUND_USER);
				PreparedStatement psAddUser = cn.prepareStatement(SqlQuery.INSERT_USER);){
			
			psFindUser.setString(SqlQuery.LOGIN_POSITION, login);
			psFindUser.setString(SqlQuery.PASSWORD_POSITION, password);
			
			psAddUser.setString(SqlQuery.LOGIN_POSITION, login);
			psAddUser.setString(SqlQuery.PASSWORD_POSITION, name);
			psAddUser.setString(SqlQuery.NAME_POSITION, password);
			psAddUser.setString(SqlQuery.EMAIL_POSITION, email);
			synchronized (UserDBImpl.class) {
				try(ResultSet rs = psFindUser.executeQuery()){
					if (!rs.next()){
						isAddedUser = psAddUser.executeUpdate();
					}
				}
			}
			if (isAddedUser != 0) {
				return new User(login, name, email);
			} else {
				throw new DAOException(Constants.LOGIN_EXISTS_ERROR);
			}
		} catch (SQLException e) {
			LOGGER.log(Level.SEVERE, e.toString(), e);
			throw new DAOException(Constants.CONNECTION_ERROR, e);
		} 
	}

	@Override
	public User getUser(String login, String password) {
		try (Connection cn = PoolConnectionManager.getConnection();
				PreparedStatement ps = cn.prepareStatement(SqlQuery.SELECT_FOUND_USER)) {
			ps.setString(SqlQuery.LOGIN_POSITION, login);
			ps.setString(SqlQuery.PASSWORD_POSITION, password);
			try (ResultSet rs = ps.executeQuery();) {
				if (rs.next()) {
					return new User(login);
				} else {
					throw new DAOException(Constants.LOGIN_PASSWORD_ERROR);
				}
			}
		} catch (SQLException e) {
			LOGGER.log(Level.SEVERE, e.toString(), e);
			throw new DAOException(Constants.CONNECTION_ERROR, e);
		}
	}

}
