package by.gsu.epamlab.constants;

public class SqlQuery {
	public static final String SELECT_FOUND_LOGIN = "SELECT * FROM users WHERE login = ?";
	public static final String SELECT_FOUND_USER = "SELECT * FROM users WHERE login = ? AND password = ?";
	public static final String INSERT_USER = "insert into users (login, password, name, email) values(?,?,?,?)";
	public static final int LOGIN_POSITION = 1;
	public static final int PASSWORD_POSITION = 2;
	public static final int NAME_POSITION = 3;
	public static final int EMAIL_POSITION = 4;
	public static final int FILE_NAME_POSITION = 1;
	public static final int ID_POSITION = 2;
	
	public static final String SELECT_TASKS = "SELECT * FROM tasks WHERE tasks.idUser = (SELECT id FROM users WHERE users.login = ?) AND ";
	public static final String WERE_TODAY_LIST_TASK = "tasks.date <= curdate() AND tasks.flagFix = 0 AND tasks.flagRecycle = 0"; 
	public static final String WERE_TOMORROW_LIST_TASK = "tasks.date = DATE_ADD(curdate(), INTERVAL 1 DAY) AND tasks.flagFix = 0 AND tasks.flagRecycle = 0";
	public static final String WERE_SOMEDAY_LIST_TASK = "tasks.date > DATE_ADD(curdate(), INTERVAL 1 DAY) AND tasks.flagFix = 0 AND tasks.flagRecycle = 0";
	public static final String WHERE_FIXED_LIST_TASK = "flagFix != 0 AND flagRecycle = 0";
	public static final String WHERE_RECYCLE_LIST_TASK = "flagRecycle != 0";

	public static final int TASK_ID_POSITION = 1;
	public static final int TASK_CONTENT_POSITION = 3;
    public static final int TASK_DATE_POSITION = 4;
    public static final int TASK_FILE_NAME_POSITION = 7;
    public static final int USER_LOGIN_POSITION = 2;
    
    public static final String INSERT_TASK = "INSERT INTO tasks(idUser, content, date, flagFix, flagRecycle)" +
            "VALUES((SELECT id FROM users WHERE login=?), ?, ?, ?, ?)";
    public static final String EDIT_TASK = "UPDATE tasks SET content=?, date=? WHERE id=?"; 
    
    
    public static final String UPDATE_TASK = "UPDATE tasks ";
    public static final String WITH_ID = " WHERE id=?";
    public static final String BY_FIX = "SET flagFix=1";
    public static final String BY_UNFIX = "SET flagFix=0";
    public static final String BY_RECYCLE = "SET flagRecycle=1";
    public static final String BY_RESTORE = "SET flagRecycle=0, flagFix=0";
    public static final String BY_FILE = "SET fileName = ?";

    public static final String REMOVE_TASK = "DELETE FROM tasks";
    
    public static final int TASK_CONT_POSITION = 2;
    public static final int TASK_DAT_POSITION = 3;
    public static final int TASK_FIX_POSITION = 4;
    public static final int TASK_RECYCLE_POSITION = 5;
    public static final int FLAG_FIX_ZERO = 0;
    public static final int FLAG_RECYCLE_ZERO = 0;
    
}
