package demo.demo.vo;

/*
 * 返回某指定员工的状态
 */
public class OtherMemberStatusVO implements ViewObject {
	
	private int id;
	private String name;
	private String title = null;
	private String status;
	
	public OtherMemberStatusVO() {}
	public OtherMemberStatusVO(int id, String name, String status) {
		this.id = id;
		this.name = name;
		this.status = status;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
