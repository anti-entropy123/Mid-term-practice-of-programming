package demo.utils;

public class ApplicationIdUtil {
	
	private int id;
	private static ApplicationIdUtil applicationIdService;
	
	private ApplicationIdUtil() {
		id = 1;
	}
	
	public synchronized static ApplicationIdUtil getInstance() {
		if (applicationIdService == null) {
			applicationIdService = new ApplicationIdUtil();
		}
		return applicationIdService;
	}
	
	public int getId() {
		return id++;
	}

}
