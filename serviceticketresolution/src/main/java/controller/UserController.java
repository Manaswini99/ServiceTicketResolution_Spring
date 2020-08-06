package controller;

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

import comakeit.assesment.app.Constants;
import pojo.DepartmentBean;
import pojo.TicketBean;

@Controller
public class UserController {

	//redirects to raiseticket.jsp
	@RequestMapping("/raiseticket")
	public ModelAndView raiseTicket() {
		String url = Constants.url+"/userController/viewdeptlist";
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<List<DepartmentBean>> res = restTemplate.exchange(url, HttpMethod.GET, null,
				new ParameterizedTypeReference<List<DepartmentBean>>() {
				});
		List<DepartmentBean> deptlist = res.getBody();
		ModelAndView modelandview = new ModelAndView("/raiseticket");
		modelandview.addObject("deptlist", deptlist);
		return modelandview;
	}

	/*
	 * This method will get details of issue so that
	 * it can be assigned to corresponding department Engineer
	 */
	@RequestMapping("/ticket")
	public ModelAndView addTicket(TicketBean ticket, HttpSession session, HttpServletRequest request) {
		ticket.setRequestedend_date(LocalDate.parse(request.getParameter("end_date")));
		DepartmentBean dep = new DepartmentBean();
		dep.setDepartment_id(Integer.parseInt(request.getParameter("department_id")));
		ticket.setDepartment(dep);
		ticket.setCustomer((String) session.getAttribute("user"));
		ticket.setStart_date(LocalDate.now());
		ticket.setStatus("pending");
		String url = Constants.url+"/userController/addticket";
		RestTemplate resttemplate = new RestTemplate();
		String insertionStatus = resttemplate.postForObject(url, ticket, String.class);
		ModelAndView modelandview = new ModelAndView("/user");
		return modelandview;
	}

	/*
	 * This method allows user to view solved and unsolved tickets raised by him
	 */
	@RequestMapping("/userview")
	public ModelAndView viewTickets() {
		String url = Constants.url+"/userController/viewticketlist";
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<List<TicketBean>> res = restTemplate.exchange(url, HttpMethod.GET, null,
				new ParameterizedTypeReference<List<TicketBean>>() {
				});
		List<TicketBean> ticketlist = res.getBody();
		ModelAndView modelandview = new ModelAndView("/viewticket");
		modelandview.addObject("ticketlist", ticketlist);
		return modelandview;
	}
}
