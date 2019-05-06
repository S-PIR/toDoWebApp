package by.gsu.epamlab.model.beans;

import java.sql.Date;

public class Task {

	private int id;
	private int idUser;
	private String contentTask;
	private Date date;
	private boolean flagFix;
	private boolean flagRecycle;
	private String fileName;

	public Task() {
	}

	public Task(String contentTask, Date date) {
		super();
		this.contentTask = contentTask;
		this.date = date;
	}

	public Task(int id, String contentTask, Date date) {
		super();
		this.id = id;
		this.contentTask = contentTask;
		this.date = date;
	}

	public Task(int id, String contentTask, Date date, String fileName) {
		super();
		this.id = id;
		this.contentTask = contentTask;
		this.date = date;
		this.fileName = fileName;
	}

	public Task(int id, int idUser, String contentTask, Date date, String fileName) {
		super();
		this.id = id;
		this.idUser = idUser;
		this.contentTask = contentTask;
		this.date = date;
		this.fileName = fileName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdUser() {
		return idUser;
	}

	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}

	public String getContentTask() {
		return contentTask;
	}

	public void setContentTask(String contentTask) {
		this.contentTask = contentTask;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public boolean isFlagFix() {
		return flagFix;
	}

	public void setFlagFix(boolean flagFix) {
		this.flagFix = flagFix;
	}

	public boolean isFlagRecycle() {
		return flagRecycle;
	}

	public void setFlagRecycle(boolean flagRecycle) {
		this.flagRecycle = flagRecycle;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	@Override
	public String toString() {
		return "Task [id=" + id + ", idUser=" + idUser + ", contentTask=" + contentTask + ", date=" + date
				+ ", flagFix=" + flagFix + ", flagRecycle=" + flagRecycle + "]";
	}

}
