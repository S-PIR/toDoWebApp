package by.gsu.epamlab.model.managers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

import by.gsu.epamlab.exceptions.DAOException;

import static by.gsu.epamlab.constants.Constants.*;

public class DbConnectionManager {
	private static final Logger LOGGER = Logger.getLogger(DbConnectionManager.class.getName());

	static {
		try {
			Class.forName(DRIVER_NAME);
		} catch (ClassNotFoundException e) {
			LOGGER.log(Level.SEVERE, e.toString(), e);
			throw new DAOException(CONNECTION_ERROR, e);
		}
	}

	public static Connection createConnection() {
		try {
			return DriverManager.getConnection(DB_URL, USER_NAME, PASSWORD);
		} catch (SQLException e) {
			LOGGER.log(Level.SEVERE, e.toString(), e);
			throw new DAOException(CONNECTION_ERROR, e);
		}
	}

	public static void closeConnection(Connection connection) {
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				LOGGER.log(Level.SEVERE, e.toString(), e);
			}
		}
	}

	public static void closeResultSet(ResultSet resourse) {
		if (resourse != null) {
			try {
				resourse.close();
			} catch (SQLException e) {
				LOGGER.log(Level.SEVERE, e.toString(), e);
			}
		}
	}

	public static void closeStatement(Statement statement) {
		if (statement != null) {
			try {
				statement.close();
			} catch (SQLException e) {
				LOGGER.log(Level.SEVERE, e.toString(), e);
			}
		}
	}

	public static void closeStatements(Statement... statements) {
		for (Statement st : statements) {
			if (st != null) {
				try {
					st.close();
				} catch (SQLException e) {
					LOGGER.log(Level.SEVERE, e.toString(), e);
				}
			}
		}
	}

	public static void closeStatementAndConnection(Connection cn, Statement... statements) {
		closeStatements(statements);
		closeConnection(cn);
	}
	
	public static void closeResourses(ResultSet rs, Connection cn, Statement... statements) {
		closeResultSet(rs);
		closeStatements(statements);
		closeConnection(cn);
	}


}
