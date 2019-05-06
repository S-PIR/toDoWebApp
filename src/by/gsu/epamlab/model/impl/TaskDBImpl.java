package by.gsu.epamlab.model.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import by.gsu.epamlab.constants.Constants;
import by.gsu.epamlab.exceptions.DAOException;
import by.gsu.epamlab.ifaces.ITaskDAO;
import by.gsu.epamlab.model.beans.Task;
import by.gsu.epamlab.model.beans.User;
import by.gsu.epamlab.model.managers.DbConnectionManager;

import static by.gsu.epamlab.constants.SqlQuery.*;

public class TaskDBImpl implements ITaskDAO {
	private static final Logger LOGGER = Logger.getLogger(TaskDBImpl.class.getName());

	@Override
	public List<Task> getTasks(User user, String param) {
		List<Task> tasks = new ArrayList<>();
		Connection cn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			Sections section = Sections.valueOf(param.toUpperCase());;
			String sqlQuery = section.getSqlQuery();
			cn = DbConnectionManager.createConnection();
			ps = cn.prepareStatement(sqlQuery);
			ps.setString(LOGIN_POSITION, user.getLogin());
			rs = ps.executeQuery();
			while (rs.next()) {
				int idTask = rs.getInt(TASK_ID_POSITION); 
				String content = rs.getString(TASK_CONTENT_POSITION); 
				Date date = rs.getDate(TASK_DATE_POSITION);
				String fileName = rs.getString(TASK_FILE_NAME_POSITION);
				tasks.add(new Task(idTask, content, date, fileName));
			}
			return tasks;
		} catch (SQLException e) {
			LOGGER.log(Level.SEVERE, e.toString(), e);
			throw new DAOException(Constants.CONNECTION_ERROR, e);
		} finally {
			DbConnectionManager.closeResourses(rs, cn, ps);
		}
	}
	
	public void editTask(String[] idTasks, String editFunction) {
		Connection cn = null;
		PreparedStatement ps = null;
		try {
			Function function = Function.valueOf(editFunction.toUpperCase());
			String sqlQuery = function.getSqlQuery();
			cn = DbConnectionManager.createConnection();
			cn.setAutoCommit(false);
			ps = cn.prepareStatement(sqlQuery);
			for (String idTask : idTasks) {
				int id = Integer.parseInt(idTask);
				ps.setInt(TASK_ID_POSITION, id);
				ps.addBatch();
			}
			ps.executeBatch();
			cn.commit();
		} catch (SQLException e) {
			try {
				cn.rollback();
			} catch (SQLException e1) {
				LOGGER.log(Level.SEVERE, e.toString(), e);
				throw new DAOException(Constants.CONNECTION_ERROR, e);
			}
			LOGGER.log(Level.SEVERE, e.toString(), e);
			throw new DAOException(Constants.CONNECTION_ERROR, e);
		} finally {
			DbConnectionManager.closeStatementAndConnection(cn, ps);
		}
	}
	
	public boolean addTask(Task task, User user, String editFunction) {
		boolean flag = false;
		Connection cn = null;
		PreparedStatement ps = null;
		try {
			Function function = Function.valueOf(editFunction.toUpperCase());
			String sqlQuery = function.getSqlQuery();
			cn = DbConnectionManager.createConnection();
			ps = cn.prepareStatement(sqlQuery);
			if (function == Function.ADD) {
				ps.setString(LOGIN_POSITION, user.getLogin());
				ps.setInt(TASK_RECYCLE_POSITION, FLAG_RECYCLE_ZERO);
				ps.setInt(TASK_FIX_POSITION, FLAG_FIX_ZERO);
			} 
			if (function == Function.EDIT) {
				ps.setInt(TASK_ID_POSITION, task.getId());
			}
			ps.setString(TASK_CONT_POSITION, task.getContentTask());
			ps.setDate(TASK_DAT_POSITION, task.getDate());
			if(ps.executeUpdate() != 0) {
				flag = true;
			};
			return flag;
		} catch (SQLException e) {
			LOGGER.log(Level.SEVERE, e.toString(), e);
			throw new DAOException(Constants.CONNECTION_ERROR, e);
		} finally {
			DbConnectionManager.closeStatementAndConnection(cn, ps);
		}
	}
	
	public boolean editTaskContent(Task task, String editFunction) {
		boolean flag = false;
		Connection cn = null;
		PreparedStatement ps = null;
		try {
			Function function = Function.valueOf(editFunction.toUpperCase());
			String sqlQuery = function.getSqlQuery();
			cn = DbConnectionManager.createConnection();
			ps = cn.prepareStatement(sqlQuery);
			ps.setString(1, task.getContentTask());
			ps.setDate(2, task.getDate());
			ps.setInt(3, task.getId());
			if(ps.executeUpdate() != 0) {
				flag = true;
			};
			return flag;
		} catch (SQLException e) {
			LOGGER.log(Level.SEVERE, e.toString(), e);
			throw new DAOException(Constants.CONNECTION_ERROR, e);
		} finally {
			DbConnectionManager.closeStatementAndConnection(cn, ps);
		}
	}
	
	@Override
	public boolean editFile(String idTask, String fileName) {
		boolean flag = false;
		Connection cn = null;
		PreparedStatement ps = null;
		try {
			cn = DbConnectionManager.createConnection();
			int id = Integer.parseInt(idTask);
			Function function = Function.FILE;
			String sqlQuery = function.getSqlQuery();
			ps = cn.prepareStatement(sqlQuery);
			ps.setString(FILE_NAME_POSITION, fileName);
			ps.setInt(ID_POSITION, id);
			if(ps.executeUpdate() != 0) {
				flag = true;
			};
			return flag;
		} catch (SQLException e) {
			LOGGER.log(Level.SEVERE, e.toString(), e);
			throw new DAOException(Constants.CONNECTION_ERROR, e);
		} finally {
			DbConnectionManager.closeStatementAndConnection(cn, ps);
		}
	}
	
	private enum Sections {
		TODAY(WERE_TODAY_LIST_TASK), 
		TOMORROW(WERE_TOMORROW_LIST_TASK), 
		SOMEDAY(WERE_SOMEDAY_LIST_TASK),
		FIXED(WHERE_FIXED_LIST_TASK), 
		RECYCLE_BIN(WHERE_RECYCLE_LIST_TASK);

		private String sqlQuery;

		private Sections(String sqlQuery) {
			this.sqlQuery = SELECT_TASKS + sqlQuery;
		}

		public String getSqlQuery() {
			return sqlQuery;
		}

	}
	
	private enum Function{
		ADD(INSERT_TASK),
		EDIT(EDIT_TASK),
		FIX(UPDATE_TASK + BY_FIX + WITH_ID),
		UNFIX(UPDATE_TASK + BY_UNFIX + WITH_ID),
		RECYCLE(UPDATE_TASK + BY_RECYCLE + WITH_ID),
		REMOVE(REMOVE_TASK + WITH_ID),
		RESTORE(UPDATE_TASK + BY_RESTORE + WITH_ID),
		FILE(UPDATE_TASK + BY_FILE + WITH_ID);
		
		private String sqlQuery;

		private Function(String sqlQuery) {
			this.sqlQuery = sqlQuery;
		}

		public String getSqlQuery() {
			return sqlQuery;
		}

	}

	
	
	
}
