package com.example.demo.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import pojo.Credentials;
import pojo.DepartmentBean;
import pojo.Roles;
import pojo.ServiceEngineer;

@Controller
public class AdminController {

	/*
	 * This method will get department List and redirects to Add Engineer jsp page
	 */
	@RequestMapping("/addeng")
	public ModelAndView addEng() {
		String url = "http://localhost:8081/usercontroller/viewdeptlist";
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<List<DepartmentBean>> res = restTemplate.exchange(url, HttpMethod.GET, null,
				new ParameterizedTypeReference<List<DepartmentBean>>() {
				});
		List<DepartmentBean> deptlist = res.getBody();

		ModelAndView modelandview = new ModelAndView("/addAdmin");
		modelandview.addObject("deptlist", deptlist);
		return modelandview;
	}

	/*
	 * This method gets details of Engineer to be added and passes them
	 *  to Admin Rest Controller
	 */
	@RequestMapping("AddingEngineer")
	public ModelAndView addEngineer(Credentials credentials, HttpServletRequest request, HttpServletResponse response) {
		String url = "http://localhost:8081/admincontroller/addcred";
		Roles roles = new Roles();
		roles.setRoleID(2);
		credentials.setRoles(roles);
		RestTemplate rt = new RestTemplate();
		String Status = null;
		boolean insertionStatus = rt.postForObject(url, credentials, Boolean.class);
		if (insertionStatus) {
			DepartmentBean dep = new DepartmentBean();
			dep.setDepartment_id(Integer.parseInt(request.getParameter("department_id")));
			ServiceEngineer se = new ServiceEngineer();
			se.setServiceengineer(credentials.getUsername());
			se.setCurrentpriority_ticket("no");
			se.setNo_of_tickets_worked(0);
			se.setDepartment(dep);
			String url1 = "http://localhost:8081/admincontroller/addtose";
			RestTemplate rt1 = new RestTemplate();
			String Status1 = rt1.postForObject(url1, se, String.class);
			Status = "Added Succesfull!";
		} else {
			Status = "Already Exist";
		}
		ModelAndView modelandview = new ModelAndView("/admin");
		modelandview.addObject("insertionStatus", Status);
		return modelandview;
	}
	/*
	 * This method will get user details whom Admin wants to add and pass them to
	 *  Admin rest controller 
	 */

	@RequestMapping("adduser")
	public ModelAndView addUser(Credentials credentials) {
		String url = "http://localhost:8081/admincontroller/addusercred";
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
	/*
	 * This method allows Admin to view all the Engineers and Users
	 */

	@RequestMapping("ViewEngineers")
	public ModelAndView viewEng() {
		RestTemplate resttemplate = new RestTemplate();
		String url = "http://localhost:8081/rest/getengineers";
		ResponseEntity<List<ServiceEngineer>> res = resttemplate.exchange(url, HttpMethod.GET, null,
				new ParameterizedTypeReference<List<ServiceEngineer>>() {
				});
		List<ServiceEngineer> englist = res.getBody();
		String url1 = "http://localhost:8081/rest/getUsers";
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
