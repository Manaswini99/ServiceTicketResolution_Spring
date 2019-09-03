package com.example.demo.controller;

import java.time.LocalDate;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpSession;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import pojo.DepartmentBean;
import pojo.TicketBean;

@Controller
public class UserController {

	/*
	 * This method will redirect to raise ticket jsp
	 * 
	 */
	@RequestMapping("/raiseticket")
	public ModelAndView Raiseticket() {

		String url = "http://localhost:8081/usercontroller/viewdeptlist";
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<List<DepartmentBean>> res = restTemplate.exchange(url, HttpMethod.GET, null,
				new ParameterizedTypeReference<List<DepartmentBean>>() {
				});
		List<DepartmentBean> deptlist = res.getBody();

		ModelAndView mv = new ModelAndView("/raiseticket");
		mv.addObject("deptlist", deptlist);
		return mv;
	}

	/*
	 * This method will get details of issue so that
	 * it can be assigned to corresponding department Engineer
	 */
	@RequestMapping("/ticket")
	public ModelAndView AddTicket(TicketBean ticket, HttpSession session, HttpServletRequest request) {
		ticket.setRequestedend_date(LocalDate.parse(request.getParameter("end_date")));
		DepartmentBean dep = new DepartmentBean();
		dep.setDepartment_id(Integer.parseInt(request.getParameter("department_id")));
		ticket.setDepartment(dep);
		ticket.setCustomer((String) session.getAttribute("user"));
		ticket.setStart_date(LocalDate.now());
		ticket.setStatus("pending");
		String url = "http://localhost:8081/usercontroller/addticket";
		RestTemplate rt = new RestTemplate();
		String insertionStatus = rt.postForObject(url, ticket, String.class);
		ModelAndView mv = new ModelAndView("/user");
		return mv;
	}

	/*
	 * This method allows user to view solved and unsolved tickets raised by him
	 */
	@RequestMapping("/userview")
	public ModelAndView viewTickets() {
		String url = "http://localhost:8081/usercontroller/viewticketlist";
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<List<TicketBean>> res = restTemplate.exchange(url, HttpMethod.GET, null,
				new ParameterizedTypeReference<List<TicketBean>>() {
				});
		List<TicketBean> ticketlist = res.getBody();
		ModelAndView mv = new ModelAndView("/viewticket");
		mv.addObject("ticketlist", ticketlist);
		return mv;
	}
}
