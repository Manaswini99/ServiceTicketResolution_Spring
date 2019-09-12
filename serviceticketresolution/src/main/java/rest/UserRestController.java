package rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import pojo.DepartmentBean;
import pojo.TicketBean;
import service.*;
/**
 *
 * @author manaswini
 *
 */
@RestController
@RequestMapping("/userController")
public class UserRestController {
	
	@Autowired
	UserService userservice;
	
	/**
	 * 
	 * @return List<DepartmentBean
	 */
	@RequestMapping(value = "/viewdeptlist", method = RequestMethod.GET)
	public List<DepartmentBean> getdeptlist(){
		return 	userservice.getDeptList();
	}
	
	/**
	 * 
	 * @param ticket
	 * @return status of ticket
	 */
	@RequestMapping(value = "/addticket", method = RequestMethod.POST)
	public Boolean addTicket(@RequestBody TicketBean ticket){
		return 	userservice.insertTicket(ticket);
	}
	
	/**
	 * 
	 * @return List<TicketBean>
	 */
	@RequestMapping(value = "/viewticketlist", method = RequestMethod.GET)
	public List<TicketBean> getticketlist(){
		return 	userservice.getTicketList();
	}
}
