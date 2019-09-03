package com.example.demo.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import pojo.TicketBean;
@Controller
public class EngineerController {
	
/*
 * This method returns list of Engineers
 */
@RequestMapping("/ViewEngineerTickets")
public ModelAndView viewEngineerTickets() {
	String url = "http://localhost:8081/engineercontroller/viewengtickets";
	RestTemplate restTemplate = new RestTemplate();
	ResponseEntity<List<TicketBean>> response = restTemplate.exchange(url, HttpMethod.GET, null,
			new ParameterizedTypeReference<List<TicketBean>>() {
			});
			List<TicketBean> engtickets= response.getBody();
			ModelAndView modelandview = new ModelAndView("/engineertickets");
			modelandview.addObject("engtickets",engtickets);
			return modelandview;
}

/*
 * This method allows engineer to view tickets and complete ongoing ticket 
 */
@RequestMapping("/completeticket")
public String completeTicket(@RequestParam("id") int id) {
	String url = "http://localhost:8081/engineercontroller/completeticket";
	RestTemplate resttemplate = new RestTemplate();
	String insertionStatus = resttemplate.postForObject(url,id, String.class);
	return "redirect:/ViewEngineerTickets";
}
/*
 * This method gives statistics of average time taken by engineers
 *  per priority 
 */

@RequestMapping("/perseverity")
public ModelAndView averagePerPriority() {
	String url = "http://localhost:8081/engineercontroller/avgperpriority";
	RestTemplate rtemp = new RestTemplate();
	ResponseEntity<List<String>> res = rtemp.exchange(url, HttpMethod.GET, null,
			new ParameterizedTypeReference<List<String>>() {
			});
			List<String> avg= res.getBody();
			ModelAndView modelandview = new ModelAndView("/avgperpriority");
			modelandview.addObject("avg",avg);
			return modelandview;
}

/*
 * This method gives statictics of average time taken by all engineers 
 */
@RequestMapping("/perengineer")
public ModelAndView averagePerEngineer() {
	String url = "http://localhost:8081/engineercontroller/avgperengineer";
	RestTemplate resttemp = new RestTemplate();
	ResponseEntity<List<String>> res = resttemp.exchange(url, HttpMethod.GET, null,
			new ParameterizedTypeReference<List<String>>() {
			});
			List<String> avgperEngineer= res.getBody();
			System.out.println(avgperEngineer+"back");
			ModelAndView modelandview = new ModelAndView("AveragePerEngineer");
			modelandview.addObject("avgperEngineer",avgperEngineer);
			return modelandview;
}
/*
 * Engineer can view age of particular ticket 
 */
@RequestMapping("/ticketname")
public ModelAndView ticketAge(@RequestParam("ticketname") Integer ticketid) {
	
	String url = "http://localhost:8081/engineercontroller/ticketage/"+ticketid;
	RestTemplate temp = new RestTemplate();
	Long age = temp.getForObject(url, Long.class);
	ModelAndView modelandview = new ModelAndView("/ticketage");
	modelandview.addObject("age","<fieldset><legend>Ticket-Age</legend><center>The ticket Age is "+age+"</center></fieldset>");
	return modelandview;
}

/*
 * This method allows Engineer to change priority of ticket if he wants to
 */
@RequestMapping("/changepriority")
public String changePriority(@RequestParam("ticket") int ticket,@RequestParam("newpriority") String newpriority) {
	String url = "http://localhost:8081/engineercontroller/updatePriority/"+ticket+"/"+newpriority;
	RestTemplate resttemplate = new RestTemplate();
	boolean insertionStatus = resttemplate.postForObject(url,null, Boolean.class);
	return "redirect:/ViewEngineerTickets";
	
}
}
