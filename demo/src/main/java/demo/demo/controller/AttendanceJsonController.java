package demo.demo.controller;

import java.util.List;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import demo.demo.bo.MinLeaveApplicationBO;
import demo.demo.bo.MinOutApplicationBO;
import demo.demo.bo.UserDataBO;
import demo.demo.requestbody.IdInfo;
import demo.demo.requestbody.LeaveInfo;
import demo.demo.requestbody.LogInfo;
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
import demo.demo.vo.MessagesVO;
import demo.demo.vo.OtherMemberStatusVO;
import demo.demo.vo.OutMembersVO;
import demo.demo.vo.OverTimeMembersVO;
import demo.demo.vo.PersonRecordVO;
import demo.demo.vo.UserDataVO;
import demo.demo.vo.UserListVO;

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
	 * 用户登录
	 * done1
	 */
	@PostMapping("/api/user/")
	void logIn(ServletResponse response,@RequestBody LogInfo logInfo) {
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		Cookie cookie = new Cookie("token", userService.logIn(logInfo));
		httpResponse.addCookie(cookie);
	}
	
//	/*
//	 * 行政部上传Excel文件，登记打卡记录
//	 */
//	void signIn(@RequestBody ) {
//		
//	}
	
	/*
	 * 加班登记
	 * done1
	 */
	@PostMapping("/api/record/overtime")
	void overtimeRecord(@RequestBody OvertimeInfo overtimeInfo) {
		overtimeService.addOvertimeRecord(overtimeInfo);
	}
	
	/*
	 * 请假申请
	 * done1
	 * waiting //没有完成修改剩余假期时间 和 添加通知
	 */
	@PostMapping("/api/application/leave")
	void leaveApplication(@RequestBody LeaveInfo leaveInfo) {
		leaveService.addLeaveApplication(leaveInfo);
	}
	
	/*
	 * 外出申请
	 * done1
	 * waiting // 没有完成添加通知
	 */
	@PostMapping("/api/application/out")
	void outApplication(@RequestBody OutInfo outInfo) {
		outService.addOutApplication(outInfo);
	}
	
	/*
	 * 补签申请
	 * next version
	 */
	@PostMapping("/api/application/remedy")
	void remedyApplication(@RequestBody RemedyInfo remedyInfo) {
		remedyService.addRemedyApplication(remedyInfo);
	}
	
	/*
	 * 修改请假申请
	 * done1
	 * waiting //没有完成修改剩余假期时间 和 添加通知
	 */
	@PostMapping("/api/application/mod/leave")
	void updateLeaveApplication(@RequestBody ModifyLeaveInfo modifyLeaveInfo) {
		leaveService.modifyLeaveApplication(modifyLeaveInfo);
	}
	
	/*
	 * 修改外出申请
	 * done1
	 * waiting // 没有完成添加通知
	 */
	@PostMapping("/api/application/mod/out")
	void updateOutApplication(@RequestBody ModifyOutInfo modifyOutInfo) {
		outService.modifyOutApplication(modifyOutInfo);
	}
	
	/*
	 * 修改补签申请
	 * next version
	 */
	@PostMapping("/api/application/mod/remedy")
	void updateRemedyApplication(@RequestBody ModifyRemedyInfo modifyRemedyInfo) {
		remedyService.modifyRemedyApplication(modifyRemedyInfo);
	}
	
	/*
	 * 查看个人考勤记录
	 * done1
	 */
	@GetMapping("/api/record")
	PersonRecordVO getRecords(@RequestBody IdInfo id) {
		return new PersonRecordVO(recordService.getRecordsById(id.getId()));
	}
	
	/*
	 * 查看个人状态
	 * done1
	 */
	@GetMapping("/api/user/status")
	CurrentStatusVO getStatus(@RequestBody IdInfo id) {
		String status = userService.getStatusById(id.getId());//String
		return new CurrentStatusVO(status);
	}
	
	/*
	 * 查看个人假期剩余情况
	 * done1
	 */
	@GetMapping("/api/user/Holidaybalance")
	HolidayBalanceVO getHolidayBalance(@RequestBody IdInfo id) {
		return new HolidayBalanceVO(leaveService.getHolidayBalanceById(id.getId()));
	}
	
	/*
	 * 查看全体员工列表
	 * done1
	 */
	@GetMapping("/api/user/employees")
	UserListVO getMembersList(@RequestBody IdInfo id) {
		return new UserListVO(userService.getMembers());
	}
	
	/*
	 * 查看其他员工状态
	 * done1
	 */
	@GetMapping("/api/user/employees/{otherUserId}")
	OtherMemberStatusVO getOthersStatus(@RequestBody IdInfo id, @PathVariable int otherUserId) {
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
	UserDataVO getUserDataById(@RequestBody IdInfo id) {
		String userName = userService.getNameById(id.getId());
		List<MinLeaveApplicationBO> leaves = leaveService.getMinLeaveApplicationById(id.getId());
		List<MinOutApplicationBO> outs = outService.getMinOutApplicationById(id.getId());
		int unsigned = recordService.getUnsignedById(id.getId());
		int overTime = overtimeService.getOvertimeById(id.getId());
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
	@GetMapping("/api/user/messages/application/")
	MessagesVO getApplications(@RequestParam(name = "type") int type, @RequestBody IdInfo id) {
		if (type == 0) {
			return new MessagesVO(leaveService.getApplicationResultById(id.getId()));
		} else {
			return new MessagesVO(outService.getApplicationResultById(id.getId()));
		}
	}
	
	/*
	 * 获取自己要处理的信息
	 * done1
	 */
	@GetMapping("/api/user/messages/process-application/")
	MessagesVO getProcess(@RequestParam(name = "type") int type, @RequestBody IdInfo id) {
		if (type == 0) {
			return new MessagesVO(leaveService.getNeedToProcessById(id.getId()));
		} else {
			return new MessagesVO(outService.getNeedToProcessById(id.getId()));
		}
	}
	
	/*
	 * 处理请假申请
	 * done1
	 * waiting 没有完成添加通知
	 */
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
	@GetMapping("/api/manager/approval-results")
	ApplicationForBossVO getApplicationResults(@RequestBody IdInfo id) {
		return new ApplicationForBossVO(leaveService.getProcessApplicationResultsById(id.getId()));
	}
	
	/*
	 * 查看全体请假员工
	 */
	@GetMapping("/api/manager/all/leaving-members")
	LeaveMembersVO getLeaveMembers(@RequestBody IdInfo id) {
		LeaveMembersVO lmv = new LeaveMembersVO(leaveService.getLeaveMembers());
		return lmv;
	}
	
	/*
	 * 查看全体外出员工
	 */
	@GetMapping("/api/manager/all/outing-members")
	OutMembersVO getOutMembers(@RequestBody IdInfo id) {
		OutMembersVO omv = new OutMembersVO(outService.getOutMembers());
		return omv;
	}
	
	/*
	 * 查看全体加班员工
	 */
	@GetMapping("/api/manager/all/overtime-members")
	OverTimeMembersVO getOvertimeMembers(@RequestBody IdInfo id) {
		OverTimeMembersVO otv = new OverTimeMembersVO(overtimeService.getOvertimeMembers());
		return otv;
	}
}
