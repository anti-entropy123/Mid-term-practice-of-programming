package demo.demo.bo;

import java.util.ArrayList;
import java.util.List;
/*
 * 大含量外出申请
 */
public class MOutApplicationBO extends ApplicationBO {

	private final String type = "out";
	private int authority = 0;
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

	public void setAuthority() {
		authority = leadersOpinion.size();
	}
	
	public int getAuthority() {
		return authority;
	}

	public void setAuthority(int authority) {
		this.authority = authority;
	}
	
}
