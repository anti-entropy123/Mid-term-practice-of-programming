package demo.demo.vo;

/*
 * 某员工当前状态
 */
public class CurrentStatusVO implements ViewObject {
	
	private String status;
	
	public CurrentStatusVO(String status) {
		this.status = status;
	}
	
	public String getStatus() {
		return status;
	}

}
