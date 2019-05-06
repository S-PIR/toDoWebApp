package by.gsu.epamlab.model.managers;

public class PropertyManager {
	private static String strDAO;
	
	public static void init(String param) {
		if (strDAO == null) {
			strDAO = param.toLowerCase().trim();
		}
	}

	public static String getStrDAO() {
		return strDAO;
	}
}
