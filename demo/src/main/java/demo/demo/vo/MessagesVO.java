package demo.demo.vo;

import java.util.ArrayList;
import java.util.List;

import demo.demo.bo.ApplicationBO;

/*
 * 查看自己的申请情况或需要自己审批的申请
 */
public class MessagesVO implements ViewObject {
	/*
	 * 自己的申请
	 */
	private List<ApplicationBO> applicationResult = new ArrayList<ApplicationBO>();
	
	public MessagesVO(List<ApplicationBO> applicationResult ) {
		this.applicationResult = applicationResult;
	}
	
	public List<ApplicationBO> getApplicationResult() {
		return this.applicationResult;
	}

}
