package by.gsu.epamlab.model.managers;

import java.io.File;

public class DeleteFileManager {
	public static void deleteDirsAndFiles(String[] idTasks, String mainDir) {
		for (int i = 0; i < idTasks.length; i++) {
			File dirFile = new File(mainDir + File.separator + idTasks[i]);
			deleteFile(dirFile);
		}
	}

	private static void deleteFile(File element) {
		if (element.isDirectory()) {
			for (File sub : element.listFiles()) {
				deleteFile(sub);
			}
		}
		element.delete();
	}
}
