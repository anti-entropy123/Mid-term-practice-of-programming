package demo.demo.bo;

/*
 * 某个领导意见
 */
public class LeaderOpinionBO implements BusinessObject {
	
	private String title;
	private String name;
	private String result;
	private String opinion;
	
	public LeaderOpinionBO() {}
	public LeaderOpinionBO(
							String title, String name, 
							String result, String opinion
						   ) {
		this.title = title;
		this.name = name;
		this.result = result;
		this.opinion = opinion;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getResult() {
		return result;
	}
	
	public void setResult(String result) {
		this.result = result;
	}
	
	public String getOpinion() {
		return opinion;
	}
	
	public void setOpinion(String opinion) {
		this.opinion = opinion;
	}
	
}
