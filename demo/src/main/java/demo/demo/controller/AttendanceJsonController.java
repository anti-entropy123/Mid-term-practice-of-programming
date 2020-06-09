package demo.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import demo.demo.bo.ApplicationBO;
import demo.demo.bo.MinLeaveApplicationBO;
import demo.demo.bo.MinOutApplicationBO;
import demo.demo.bo.UserDataBO;
import demo.demo.requestbody.LeaveInfo;
import demo.demo.requestbody.ModifyLeaveInfo;
import demo.demo.requestbody.ModifyOutInfo;
import demo.demo.requestbody.ModifyRemedyInfo;
import demo.demo.requestbody.OutInfo;
import demo.demo.requestbody.OvertimeInfo;
import demo.demo.requestbody.ProcessInfo;
import demo.demo.requestbody.RemedyInfo;
import demo.demo.service.LeaveService;
import demo.demo.service.OutService;
import demo.demo.service.OvertimeService;
import demo.demo.service.RecordService;
import demo.demo.service.RemedyService;
import demo.demo.service.UserService;
import demo.demo.vo.ApplicationForBossVO;
import demo.demo.vo.CurrentStatusVO;
import demo.demo.vo.HolidayBalanceVO;
import demo.demo.vo.LeaveMembersVO;
import demo.demo.vo.LoginStatusVO;
import demo.demo.vo.MessagesVO;
import demo.demo.vo.OtherMemberStatusVO;
import demo.demo.vo.OutMembersVO;
import demo.demo.vo.OverTimeMembersVO;
import demo.demo.vo.PersonRecordVO;
import demo.demo.vo.UserDataVO;
import demo.demo.vo.UserListVO;
import demo.utils.JwtTokenUtil;

@RestController
public class AttendanceJsonController {
	
	/*
	 * 用户信息处理service
	 */
	@Autowired
	private UserService userService;
	
	/*
	 * 加班信息处理service
	 */
	@Autowired
	private OvertimeService overtimeService;
	
	/*
	 * 打卡信息处理service
	 */
	@Autowired
	private RecordService recordService;
	
	/*
	 * 请假信息处理service
	 */
	@Autowired
	private LeaveService leaveService;
	
	/*
	 * 外出信息处理service
	 */
	@Autowired
	private OutService outService;
	
	/*
	 * 处理补签信息的service
	 */
	@Autowired
	private RemedyService remedyService;
	/*
	 * 用于解码token
	 */
	@Autowired 
	private JwtTokenUtil jwtTokenUtil;
	// ! 登录方法被 demo.demo.controller.AuthController 中的 Login 方法取代
	// @PostMapping("/api/user/")
	// void logIn(ServletResponse response,@RequestBody LogInfo logInfo) {
	// 	HttpServletResponse httpResponse = (HttpServletResponse) response;
	// 	Cookie cookie = new Cookie("token", userService.logIn(logInfo));
	// 	httpResponse.addCookie(cookie);
	// }
	
	/*
	 * 通过token获取用户的 id 和 username 等数据
	 * 6.5 日新增 by yjn
	 * 
	 */ 
	@GetMapping("/api/user")
	LoginStatusVO loginStatus(@RequestHeader HttpHeaders headers){
		String token = headers.get("Authorization").get(0).substring("Bearer ".length());
		int userId = Integer.valueOf(jwtTokenUtil.getUsernameFromToken(token));
		return new LoginStatusVO(userService.getLoginSatus(userId));
	}

	/*
	 * 行政部上传Excel文件，登记打卡记录
	 * done1
	 * 5.26测试通过
	 */
	@PreAuthorize("hasRole('行政部员工')")
	@PostMapping("/api/administration-department/after-process/data")
	void signIn(@RequestParam("file") MultipartFile file) {
		recordService.saveRecords(file);
	}
	
	/*
	 * 加班登记
	 * done1
	 */

	@PreAuthorize("hasRole('普通员工')")
	@PostMapping("/api/record/overtime")
	void overtimeRecord(@RequestBody OvertimeInfo overtimeInfo) {
		overtimeService.addOvertimeRecord(overtimeInfo);
	}
	
	/*
	 * 请假申请
	 * done1
	 * waiting //没有完成修改剩余假期时间 和 添加通知
	 */
	@PreAuthorize("hasRole('普通员工')")
	@PostMapping("/api/application/leave")
	void leaveApplication(@RequestBody LeaveInfo leaveInfo) {
		leaveService.addLeaveApplication(leaveInfo);
	}
	
	/*
	 * 外出申请
	 * done1
	 * waiting // 没有完成添加通知
	 */
	@PreAuthorize("hasRole('普通员工')")
	@PostMapping("/api/application/out")
	void outApplication(@RequestBody OutInfo outInfo) {
		outService.addOutApplication(outInfo);
	}
	
	/*
	 * 补签申请
	 * next version
	 */
	@PreAuthorize("hasRole('普通员工')")
	@PostMapping("/api/application/remedy")
	void remedyApplication(@RequestBody RemedyInfo remedyInfo) {
		remedyService.addRemedyApplication(remedyInfo);
	}
	
	/*
	 * 修改请假申请
	 * done1
	 * waiting //没有完成修改剩余假期时间 和 添加通知
	 */
	@PreAuthorize("hasRole('普通员工')")
	@PostMapping("/api/application/mod/leave")
	void updateLeaveApplication(@RequestBody ModifyLeaveInfo modifyLeaveInfo) {
		leaveService.modifyLeaveApplication(modifyLeaveInfo);
	}
	
	/*
	 * 修改外出申请
	 * done1
	 * waiting // 没有完成添加通知
	 */
	@PreAuthorize("hasRole('普通员工')")
	@PostMapping("/api/application/mod/out")
	void updateOutApplication(@RequestBody ModifyOutInfo modifyOutInfo) {
		outService.modifyOutApplication(modifyOutInfo);
	}
	
	/*
	 * 修改补签申请
	 * next version
	 */
	@PreAuthorize("hasRole('普通员工')")
	@PostMapping("/api/application/mod/remedy")
	void updateRemedyApplication(@RequestBody ModifyRemedyInfo modifyRemedyInfo) {
		remedyService.modifyRemedyApplication(modifyRemedyInfo);
	}
	
	/*
	 * 查看个人考勤记录
	 * done1
	 */
	@PreAuthorize("hasRole('普通员工')")
	@GetMapping("/api/record")
	PersonRecordVO getRecords(@RequestParam int id) {
		return new PersonRecordVO(recordService.getRecordsById(id));
	}
	
	/*
	 * 查看个人状态
	 * done1
	 */
	@PreAuthorize("hasRole('普通员工')")
	@GetMapping("/api/user/status")
	CurrentStatusVO getStatus(@RequestParam int id) {
		String status = userService.getStatusById(id);//String
		return new CurrentStatusVO(status);
	}
	
	/*
	 * 查看个人假期剩余情况
	 * done1
	 */
	@PreAuthorize("hasRole('普通员工')")
	@GetMapping("/api/user/Holidaybalance")
	HolidayBalanceVO getHolidayBalance(@RequestParam int id) {
		return new HolidayBalanceVO(leaveService.getHolidayBalanceById(id));
	}
	
	/*
	 * 查看全体员工列表
	 * done1
	 */
	@PreAuthorize("hasRole('普通员工')")
	@GetMapping("/api/user/employees")
	UserListVO getMembersList() {
		return new UserListVO(userService.getMembers());
	}
	
	/*
	 * 查看其他员工状态
	 * done1
	 */
	@PreAuthorize("hasRole('普通员工')")
	@GetMapping("/api/user/employees/{otherUserId}")
	OtherMemberStatusVO getOthersStatus(@PathVariable int otherUserId) {
		int userId = otherUserId;
		String name = userService.getNameById(otherUserId);
		String status = userService.getStatusById(otherUserId);
		return new OtherMemberStatusVO(userId, name, status);
	}
	
	/*
	 * 获取系统处理后的数据
	 * 某个人的全部数据
	 * done1
	 */
	@GetMapping("/api/administration-department/after-process/data")
	UserDataVO getUserDataById(@RequestParam int id) {
		String userName = userService.getNameById(id);
		List<MinLeaveApplicationBO> leaves = leaveService.getMinLeaveApplicationById(id);
		List<MinOutApplicationBO> outs = outService.getMinOutApplicationById(id);
		int unsigned = recordService.getUnsignedById(id);
		int overTime = overtimeService.getOvertimeById(id);
		UserDataBO data = new UserDataBO();
		data.setUserName(userName);
		data.setLeaves(leaves);
		data.setOuts(outs);
		data.setUnsigned(unsigned);
		data.setOverTime(overTime);
		return new UserDataVO(data);
	}
	
	/*
	 * 获取自己的申请信息
	 * done1
	 */
	@PreAuthorize("hasRole('普通员工')")
	@GetMapping("/api/user/messages/application")
	MessagesVO getApplications(@RequestParam(name = "type") int type, @RequestParam int id) {
		if (type == 0) {
			return new MessagesVO(leaveService.getApplicationResultById(id));
		} else if (type == 1){
			return new MessagesVO(outService.getApplicationResultById(id));
		} else {
			List<ApplicationBO> list1 = leaveService.getApplicationResultById(id);
			List<ApplicationBO> list2 = outService.getApplicationResultById(id);
			list1.addAll(list2);
			return new MessagesVO(list1);
		}
	}
	
	/*
	 * 获取自己要处理的信息
	 * done1
	 */

	@PreAuthorize("hasRole('项目经理')")
	@GetMapping("/api/user/messages/process-application")
	MessagesVO getProcess(@RequestParam(name = "type") int type, @RequestParam int id) {
		if (type == 0) {
			return new MessagesVO(leaveService.getNeedToProcessById(id));
		} else if (type == 1){
			return new MessagesVO(outService.getNeedToProcessById(id));
		} else {
			List<ApplicationBO> list1 = leaveService.getNeedToProcessById(id);
			List<ApplicationBO> list2 = outService.getNeedToProcessById(id);
			list1.addAll(list2);
			return new MessagesVO(list1);
		}
	}
	
	/*
	 * 处理请假申请
	 * done1
	 * waiting 没有完成添加通知
	 */
	@PreAuthorize("hasRole('项目经理')")
	@PostMapping("/api/application/process-command")
	void processApplication(@RequestBody ProcessInfo processInfo) {
		if (!leaveService.addOpinion(processInfo)) {
			outService.addOpinion(processInfo);
		}
	}
	
	/*
	 * 查看其他管理者的审批情况
	 * done1
	 */
	@PreAuthorize("hasRole('副总经理')")
	@GetMapping("/api/manager/approval-results")
	ApplicationForBossVO getApplicationResults(@RequestParam int id) {
		return new ApplicationForBossVO(leaveService.getProcessApplicationResultsById(id));
	}
	
	/*
	 * 查看全体请假员工
	 */
	@PreAuthorize("hasRole('副总经理')")
	@GetMapping("/api/manager/all/leaving-members")
	LeaveMembersVO getLeaveMembers() {
		LeaveMembersVO lmv = new LeaveMembersVO(leaveService.getLeaveMembers());
		return lmv;
	}
	
	/*
	 * 查看全体外出员工
	 */
	@PreAuthorize("hasRole('副总经理')")
	@GetMapping("/api/manager/all/outing-members")
	OutMembersVO getOutMembers() {
		OutMembersVO omv = new OutMembersVO(outService.getOutMembers());
		return omv;
	}
	
	/*
	 * 查看全体加班员工
	 */
	@PreAuthorize("hasRole('副总经理')")
	@GetMapping("/api/manager/all/overtime-members")
	OverTimeMembersVO getOvertimeMembers() {
		OverTimeMembersVO otv = new OverTimeMembersVO(overtimeService.getOvertimeMembers());
		return otv;
	}
}
