package demo.demo.bo;

import java.util.ArrayList;
import java.util.List;
/*
 * 大含量外出申请
 */
public class MOutApplicationBO extends ApplicationBO {

	private final String type = "out";
	private List<LeaderOpinionBO> leadersOpinion = new ArrayList<LeaderOpinionBO>();
	
	public MOutApplicationBO() {
		super();
	}
	
	public String getType() {
		return type;
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
