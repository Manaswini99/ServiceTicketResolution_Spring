package controller;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import comakeit.assesment.app.Constants;
import pojo.*;

@Controller
public class AdminController {
	@Autowired
	Environment environment;
	
	//redirects to add Engineer jsp with departments available
	@RequestMapping("/addeng")
	public ModelAndView addEng() {
		String url = Constants.url+"/userController/viewdeptlist";
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<List<DepartmentBean>> res = restTemplate.exchange(url, HttpMethod.GET, null,
				new ParameterizedTypeReference<List<DepartmentBean>>() {
				});
		List<DepartmentBean> deptlist = res.getBody();
		ModelAndView modelandview = new ModelAndView("/addAdmin");
		modelandview.addObject("deptlist", deptlist);
		return modelandview;
	}

	//Adds Engineer
	@RequestMapping("AddingEngineer")
	public ModelAndView addEngineer(Credentials credentials, HttpServletRequest request, HttpServletResponse response) {
		String url = Constants.url+"/adminController/addCredentials";
		Roles roles = new Roles();
		roles.setRoleID(2);
		credentials.setRoles(roles);
		RestTemplate restTemplate = new RestTemplate();
		String Status = null;
		boolean insertionStatus = restTemplate.postForObject(url, credentials, Boolean.class);
		if (insertionStatus) {
			DepartmentBean department = new DepartmentBean();
			department.setDepartment_id(Integer.parseInt(request.getParameter("department_id")));
			ServiceEngineer se = new ServiceEngineer();
			se.setServiceengineer(credentials.getUsername());
			se.setCurrentpriority_ticket("no");
			se.setNo_of_tickets_worked(0);
			se.setDepartment(department);
			String url1 = Constants.url+"/adminController/addEngineer";
			RestTemplate resttemplate1 = new RestTemplate();
			String Status1 = resttemplate1.postForObject(url1, se, String.class);
			Status = "Added Succesfull!";
		} else {
			Status = "Already Exist";
		}
		ModelAndView modelandview = new ModelAndView("/admin");
		modelandview.addObject("insertionStatus", Status);
		return modelandview;
	}
	
	//adds user
	@RequestMapping("adduser")
	public ModelAndView addUser(Credentials credentials) {
		String url = Constants.url+"/adminController/addUserCredentials";
		Roles roles = new Roles();
		roles.setRoleID(3);
		credentials.setRoles(roles);
		String Status = "Not Added";
		RestTemplate resttemplate = new RestTemplate();
		boolean insertionStatus = resttemplate.postForObject(url, credentials, Boolean.class);
		if (insertionStatus) {
			Status = "Successfully added!!";
		} else {
			Status = "Try to add username with unique username";
		}
		if (Status.equals("Successfully added!!")) {
			ModelAndView mv = new ModelAndView("/admin");
			mv.addObject("insertionStatus", Status);
			return mv;
		} else {
			ModelAndView modelandview = new ModelAndView("/AddUser");
			modelandview.addObject("insertionStatus", Status);
			return modelandview;
		}

	}
	
	//views all engineers and users
	@RequestMapping("ViewEngineers")
	public ModelAndView viewAll() {
		RestTemplate resttemplate = new RestTemplate();
		String url = Constants.url+"/rest/getengineers";
		ResponseEntity<List<ServiceEngineer>> res = resttemplate.exchange(url, HttpMethod.GET, null,
				new ParameterizedTypeReference<List<ServiceEngineer>>() {
				});
		List<ServiceEngineer> englist = res.getBody();
		String url1 = Constants.url+"/rest/getUsers";
		ResponseEntity<List<Credentials>> response = resttemplate.exchange(url1, HttpMethod.GET, null,
				new ParameterizedTypeReference<List<Credentials>>() {
				});
		List<Credentials> userlist = response.getBody();
		ModelAndView modelandview = new ModelAndView("/view");
		modelandview.addObject("userlist", userlist);
		modelandview.addObject("englist", englist);
		return modelandview;
	}

}
