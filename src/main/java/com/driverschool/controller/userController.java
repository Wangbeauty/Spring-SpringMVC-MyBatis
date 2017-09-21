package com.driverschool.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.driverschool.model.address;
import com.driverschool.model.subscribe;
import com.driverschool.model.subscribeList;
import com.driverschool.model.user;
import com.driverschool.service.subscribeService;
import com.driverschool.service.userService;

@Controller
@RequestMapping("/user")
public class userController {
	private Logger logger = LoggerFactory.getLogger(userController.class);
	HttpSession session = null;
	
	@Resource
	private userService obj;
	
	@Resource
	private subscribeService dao;
	
	/*登录页*/
	@RequestMapping("/index")
	public ModelAndView showLogin(HttpServletRequest request) {
		return new ModelAndView("index");
	}
	/*主页*/
	@RequestMapping("/driverSchool")
	public ModelAndView showIndex(HttpServletRequest request) {
		return new ModelAndView("driverSchoolIndex");
	}
	/*详情页*/
	@RequestMapping("/detail")
	public String showDetail(HttpServletRequest request) {
		return "driverSchoolDetail";
	}
	
	/**
	 * 用户登陆
	 * @param request
	 * @return
	 */
	@RequestMapping("/login")
	@ResponseBody
	public String userLogin(HttpServletRequest request) {
		session = request.getSession();
		String usernamelogin = request.getParameter("usernamelogin");
		String passwordlogin = request.getParameter("passwordlogin");
		System.out.println("usernamelogin="+usernamelogin+" passwordlogin="+passwordlogin);
		
		user user = obj.findUserByUserName(usernamelogin);
		if(user != null) {
			logger.info("当前登录用户存在---开始验证密码");
			String password = user.getPassword();
			if(password.equals(passwordlogin)) {
				logger.info("当前登录用户存在---密码正确");
				session.setAttribute("user", user);
				session.setAttribute("isLogin", "true");
				return "success";
			} else {
				logger.error("当前登录用户存在---密码错误");
				return "failure";
			}
		} else {
			logger.error("当前登录用户不存在");
			return "failure";
		}
	}
	
	/**
	 * 用户注册
	 * @param request
	 * @param user
	 * @return
	 */
	@RequestMapping("/register")
	@ResponseBody
	public String registerUser(HttpServletRequest request, user user) {
		String username = user.getUsername();
		String password = user.getPassword();
		String addressId = user.getAddressId();
		user.setIdentityId("3");
		System.out.println("username="+username+" password="+password+" addressId="+addressId);
		
		int i = obj.addUser(user);
		if(i == 0) {
			logger.error("注册失败");
			return "failure";
		}
		logger.info("注册成功");
		return "success";
	}
	
	/**
	 * 注册下拉框初始化
	 * @param request
	 * @return
	 */
	@RequestMapping("/select")
	@ResponseBody
	public List<address> selectShow(HttpServletRequest request) {
		List<address> result = new ArrayList<address>();
		result = obj.findAddress();
		return result;
	}
	
	/*******************************学员登陆**************************************/
	
	/**
	 * 学员学车时间显示
	 * @param request
	 * @return
	 */
	@RequestMapping("/subscribeDate")
	@ResponseBody
	public subscribe showSubscribeDate(HttpServletRequest request) {
		user user = (user)session.getAttribute("user");
		subscribe ss = dao.findSubscribeByUserId(user.getId());
		return ss;
	}
	
	/**
	 * 学员确认学车时间
	 * @param request
	 * @param isConfirm
	 * @return
	 */
	@RequestMapping("/confirmArrange")
	@ResponseBody
	public String confirmArrange(HttpServletRequest request,String isConfirm) {
		logger.info("学员开始确认-----isConfirm="+isConfirm);
		user user = (user)session.getAttribute("user");
		subscribe ss = new subscribe();
		ss.setUserId(user.getId());
		ss.setIsConfirm(isConfirm);
		int i = dao.updateSubscribeIsConfirm(ss);
		if(i == 0) {
			logger.error("学员确认失败");
			return "failure";
		}
		logger.info("学员确认成功");
		return "success";
	}
	
	/*******************************用户登陆**************************************/
	
	/**
	 * 用户报名
	 * @param request
	 * @return
	 */
	@RequestMapping("/afterEnrol")
	@ResponseBody
	public String afterEnrol(HttpServletRequest request) {
		logger.info("用户开始报名");
		user user = (user)session.getAttribute("user");
		//设置为学员
		user.setIdentityId("2");
		int i = obj.updateUser(user);
		if(i == 0) {
			logger.error("用户报名失败");
			return "failure";
		}
		subscribe ss = new subscribe();
		ss.setUserId(user.getId());
		//添加到报名表
		int j = dao.addSubscribeIsNotSubscribe(ss);
		if(j == 0) {
			logger.error("用户报名失败");
			return "failure";
		}
		logger.info("用户报名成功");
		return "success";
	}
	
	/*******************************教练登陆**************************************/
	
	/**
	 * 获取报名表和预约表
	 * @param request
	 * @return
	 */
	@RequestMapping("/subscribeList")
	@ResponseBody
	public subscribeList showSubscribeList(HttpServletRequest request) {
		subscribeList result = new subscribeList();
		//已安排学车时间列表
		List<subscribe> isSubscribeList = dao.findSubscribeIsSubscribe();
		//已报名学员时间列表
		List<subscribe> isNotSubscribeList = dao.findSubscribeIsNotSubscribe();
		
		result.setIsSubscribeList(isSubscribeList);
		result.setIsNotSubscribeList(isNotSubscribeList);
		
		return result;
	}
	
	/**
	 * 增加预约
	 * @param request
	 * @param ss
	 * @return
	 */
	@RequestMapping("/addSubscribe")
	@ResponseBody
	public String addSubscribe(HttpServletRequest request,subscribe ss) {
		String subscribeStr = ss.getSubscribeStr();
		logger.info("预约开始-----预约时间" + subscribeStr);
//		subscribe sub = dao.findSubscribeBySubscribeStr(subscribeStr);
//		if(sub != null) {
//			logger.info("预约时间发生冲突,需要换一个时间");
//			return "conflict";
//		}
		int i = dao.updateSubscribeIsSubscribe(ss);
		if(i == 0) {
			logger.error("预约增加失败");
			return "false";
		}
		logger.info("预约增加成功");
		return "success";
	}

}
