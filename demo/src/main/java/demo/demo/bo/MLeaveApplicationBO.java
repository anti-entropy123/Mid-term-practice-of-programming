package demo.demo.bo;

import java.util.ArrayList;
import java.util.List;

/*
 * 大含量请假申请
 */
public class MLeaveApplicationBO extends ApplicationBO {

	private String type;
	private List<LeaderOpinionBO> leadersOpinion = new ArrayList<LeaderOpinionBO>();
	
	public MLeaveApplicationBO() {
		super();
	}
	
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		
	}

	public List<LeaderOpinionBO> getLeadersOpinion() {
		return leadersOpinion;
	}

	public void setLeadersOpinion(List<LeaderOpinionBO> leadersOpinion) {
		this.leadersOpinion = leadersOpinion;
	}
	
	public void addLeaderOpinion(LeaderOpinionBO leaderOpinion) {
		this.leadersOpinion.add(leaderOpinion);
	}
	
}
