package demo.demo.vo;

/*
 * 假期剩余情况
 */
public class HolidayBalanceVO implements ViewObject {
	
	/*
	 * 年假
	 */
	private int annualLeave;
	/*
	 * 产假
	 */
	private int maternityLeave;
	/*
	 * 探亲假
	 */
	private int homeLeave;
	
	public HolidayBalanceVO() {}

	public int getAnnualLeave() {
		return annualLeave;
	}

	public void setAnnualLeave(int annualLeave) {
		this.annualLeave = annualLeave;
	}

	public int getMaternityLeave() {
		return maternityLeave;
	}

	public void setMaternityLeave(int maternityLeave) {
		this.maternityLeave = maternityLeave;
	}

	public int getHomeLeave() {
		return homeLeave;
	}

	public void setHomeLeave(int homeLeave) {
		this.homeLeave = homeLeave;
	}
	
}
