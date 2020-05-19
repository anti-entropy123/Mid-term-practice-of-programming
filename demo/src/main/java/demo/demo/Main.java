package demo.demo;

import demo.demo.service.UserService;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		UserService userService = new UserService();
		System.out.println(userService.getStatusById(1));
	}

}
