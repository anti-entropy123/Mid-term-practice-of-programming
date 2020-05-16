package demo.demo.vo;

import java.util.ArrayList;
import java.util.List;

import demo.demo.bo.ApplicationForBossBO;

/*
 * 总经理可以查看副总经理项目经理等领导做出的审批
 */
public class ApplicationForBossVO {

	private List<ApplicationForBossBO> otherManagerApprovalResults = new ArrayList<ApplicationForBossBO>();
	
	public ApplicationForBossVO(List<ApplicationForBossBO> list) {
		this.otherManagerApprovalResults = list;
	}
	
	public List<ApplicationForBossBO> getOtherManagerApprovalResults() {
		return this.otherManagerApprovalResults;
	}
	
}
