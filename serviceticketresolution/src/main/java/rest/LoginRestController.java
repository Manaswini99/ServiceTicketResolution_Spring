package rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import pojo.Credentials;
import pojo.ServiceEngineer;
import service.*;

/**
 * 
 * @author manaswini
 *
 */
@RestController
@RequestMapping("/rest")
public class LoginRestController {

	@Autowired
	LoginService loginservice;

	@Autowired
	EngineerService engineerservice;
	
	/**
	 * 
	 * @param loginbean
	 * @return status of insertion
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String insert(@RequestBody Credentials loginbean) {
		return loginservice.check(loginbean);
	}
	
	/**
	 * 
	 * @return List<ServiceEngineer>
	 */
	@RequestMapping(value = "/getengineers", method = RequestMethod.GET)
	public List<ServiceEngineer> getEngineers() {
		return engineerservice.getEngineerList();
	}
	
	/**
	 * 
	 * @return List<Credentials>
	 */
	@RequestMapping(value = "/getUsers", method = RequestMethod.GET)
	public List<Credentials> getUsers() {
		return loginservice.getUsers();
	}
}
