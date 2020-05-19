package demo.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import demo.demo.dao.ApplicationDao;
import demo.demo.requestbody.ModifyRemedyInfo;
import demo.demo.requestbody.RemedyInfo;
/*
 * next version
 */
@Service
public class RemedyService {

	@Autowired
	private ApplicationDao applicationDao;
	
	/*
	 * 添加一个补签信息
	 */
	public void addRemedyApplication(RemedyInfo remedyInfo) {
		// TODO Auto-generated method stub
	}

	/*
	 * 修改一个补签信息
	 */
	public void modifyRemedyApplication(ModifyRemedyInfo modifyRemedyInfo) {
		// TODO Auto-generated method stub
	}

}
