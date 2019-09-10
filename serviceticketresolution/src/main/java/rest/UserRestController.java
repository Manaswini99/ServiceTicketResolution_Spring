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

@RestController
@RequestMapping("/usercontroller")
public class UserRestController {
	
	@Autowired
	UserDao userdao;

	@RequestMapping(value = "/viewdeptlist", method = RequestMethod.GET)
	public List<DepartmentBean> getdeptlist(){
		return 	userdao.getDeptList();
	}
	
	@RequestMapping(value = "/addticket", method = RequestMethod.POST)
public Boolean addTicket(@RequestBody TicketBean ticket){
		System.out.println("rest"+ticket);
		return 	userdao.insertTicket(ticket);
	}
	
	@RequestMapping(value = "/viewticketlist", method = RequestMethod.GET)
	public List<TicketBean> getticketlist(){
		return 	userdao.getTicketList();
	}
}
