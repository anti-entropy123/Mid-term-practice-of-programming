package demo.demo.service;

public class ApplicationIdService {
	
	private int id;
	private static ApplicationIdService applicationIdService;
	
	private ApplicationIdService() {
		id = 1;
	}
	
	public synchronized static ApplicationIdService getInstance() {
		if (applicationIdService == null) {
			applicationIdService = new ApplicationIdService();
		}
		return applicationIdService;
	}
	
	public int getId() {
		return id++;
	}

}
