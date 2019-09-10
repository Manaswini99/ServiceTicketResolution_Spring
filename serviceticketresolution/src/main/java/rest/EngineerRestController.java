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
	EngineerDao engineerdao;

	@RequestMapping(value = "/viewengtickets", method = RequestMethod.GET)
	public List<TicketBean> getTicketList()
	{
		return engineerdao.getTicketList();
	}
	@RequestMapping(value="/completeticket",method = RequestMethod.POST)
	public void completeTicket(@RequestBody int id) {
		engineerdao.completeTicket(id);
	}
	
	@RequestMapping(value = "/avgperpriority", method = RequestMethod.GET)
	public List<String> getAvgPerPriority()
	{
		return engineerdao.getavgPriority();
	}
	
	@RequestMapping(value = "/avgperengineer", method = RequestMethod.GET)
	public List<String> getAvgPerEngineer()
	{
		return engineerdao.getavgperEngineer();
	}
	
	
	@RequestMapping(value = "/ticketage/{id}", method = RequestMethod.GET)
	public Long getTicketAge(@PathVariable("id")Integer ticketid)
	{
		
		return engineerdao.getTicketName(ticketid);
	}
	
	@RequestMapping(value = "/updatePriority/{ticket}/{newpriority}", method = RequestMethod.POST)
	public boolean updatePriority(@PathVariable("ticket") int  ticket,@PathVariable("newpriority") String newpriority)
	{
		
		 engineerdao.updateTicketPriority(newpriority,ticket);
		 return true;
	}
	
}
