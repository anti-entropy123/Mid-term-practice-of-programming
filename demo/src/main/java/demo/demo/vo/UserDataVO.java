package demo.demo.vo;

import demo.demo.bo.UserDataBO;

/*
 * 存放某用户的全部请假 外出 加班 打卡信息
 */
public class UserDataVO implements ViewObject {
	
	private UserDataBO data;
	
	public UserDataVO() {}
	public UserDataVO(UserDataBO data) {
		this.data = data;
	}

	public UserDataBO getData() {
		return data;
	}
	
	public void setData(UserDataBO data) {
		this.data = data;
	}
	
}
