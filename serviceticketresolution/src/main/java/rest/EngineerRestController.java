package rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import service.*;
import java.util.List;

import pojo.TicketBean;


@RestController
@RequestMapping("/engineercontroller")
public class EngineerRestController {
	@Autowired
	EngineerService engineerservice;

	@RequestMapping(value = "/viewengtickets", method = RequestMethod.GET)
	public List<TicketBean> getTicketList()
	{
		return engineerservice.getTicketList();
	}
	@RequestMapping(value="/completeticket",method = RequestMethod.POST)
	public void completeTicket(@RequestBody int id) {
		engineerservice.completeTicket(id);
	}
	
	@RequestMapping(value = "/avgperpriority", method = RequestMethod.GET)
	public List<String> getAvgPerPriority()
	{
		return engineerservice.getavgPriority();
	}
	
	@RequestMapping(value = "/avgperengineer", method = RequestMethod.GET)
	public List<String> getAvgPerEngineer()
	{
		return engineerservice.getavgperEngineer();
	}
	
	
	@RequestMapping(value = "/ticketage/{id}", method = RequestMethod.GET)
	public Long getTicketAge(@PathVariable("id")Integer ticketid)
	{
		return engineerservice.getTicketName(ticketid);
	}
	
	@RequestMapping(value = "/updatePriority/{ticket}/{newpriority}", method = RequestMethod.PUT)
	public boolean updatePriority(@PathVariable("ticket") int  ticket,@PathVariable("newpriority") String newpriority)
	{
		
		engineerservice.updateTicketPriority(newpriority,ticket);
		 return true;
	}
	
}
