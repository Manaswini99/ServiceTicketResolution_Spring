package controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import comakeit.assesment.app.Constants;
import pojo.TicketBean;


@Controller
public class EngineerController {
	@Autowired
	Environment environment;

	//returns list of engineers
	@RequestMapping("/ViewEngineerTickets")
	public ModelAndView viewEngineerTickets() {
		String url = Constants.url+"/engineercontroller/viewengtickets";
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<List<TicketBean>> response = restTemplate.exchange(url, HttpMethod.GET, null,
				new ParameterizedTypeReference<List<TicketBean>>() {
				});
		List<TicketBean> engtickets = response.getBody();
		ModelAndView modelandview = new ModelAndView("/engineertickets");
		modelandview.addObject("engtickets", engtickets);
		return modelandview;
	}

	//This method allows engineer to view ticket and complete ongoing
	@RequestMapping("/completeticket")
	public String completeTicket(@RequestParam("id") int id) {
		String url = Constants.url+"/engineercontroller/completeticket";
		RestTemplate resttemplate = new RestTemplate();
		String insertionStatus = resttemplate.postForObject(url, id, String.class);
		return "redirect:/ViewEngineerTickets";
	}
	
	//gets average time taken per priority
	@RequestMapping("/perseverity")
	public ModelAndView averagePerPriority() {
		String url = Constants.url+"/engineercontroller/avgperpriority";
		RestTemplate rtemp = new RestTemplate();
		ResponseEntity<List<String>> res = rtemp.exchange(url, HttpMethod.GET, null,
				new ParameterizedTypeReference<List<String>>() {
				});
		List<String> avg = res.getBody();
		ModelAndView modelandview = new ModelAndView("/avgperpriority");
		modelandview.addObject("avg", avg);
		return modelandview;
	}

	/*
	 * This method gives statistics of average time taken by all engineers
	 */
	@RequestMapping("/perengineer")
	public ModelAndView averagePerEngineer() {
		String url = Constants.url+"/engineercontroller/avgperengineer";
		RestTemplate resttemp = new RestTemplate();
		ResponseEntity<List<String>> res = resttemp.exchange(url, HttpMethod.GET, null,
				new ParameterizedTypeReference<List<String>>() {
				});
		List<String> avgperEngineer = res.getBody();

		ModelAndView modelandview = new ModelAndView("AveragePerEngineer");
		modelandview.addObject("avgperEngineer", avgperEngineer);
		return modelandview;
	}

	// Engineer can view age of particular ticket
	@RequestMapping("/ticketname")
	public ModelAndView ticketAge(@RequestParam("ticketname") Integer ticketid) {
		String url = Constants.url+"/engineercontroller/ticketage/" + ticketid;
		RestTemplate restTemplate = new RestTemplate();
		Long age = restTemplate.getForObject(url, Long.class);
		ModelAndView modelandview = new ModelAndView("/ticketage");
		modelandview.addObject("age",
				"<fieldset><legend>Ticket-Age</legend><center>The ticket Age is " + age + "</center></fieldset>");
		return modelandview;
	}

	
	// This method allows Engineer to change priority of ticket if he wants to
	@RequestMapping("/changepriority")
	public String changePriority(@RequestParam("ticket") int ticket, @RequestParam("newpriority") String newpriority) {
		String url = Constants.url+"/engineercontroller/updatePriority/" + ticket + "/" + newpriority;
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.put(url, null, Boolean.class);
		return "redirect:/ViewEngineerTickets";

	}
}
