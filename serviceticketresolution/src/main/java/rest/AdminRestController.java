package rest;

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
@RequestMapping("/adminController")
public class AdminRestController {
	@Autowired
	AdminService adminservice;
	
	/**
	 * 
	 * @param loginbean
	 * @return status
	 */
	@RequestMapping(value = "/addCredentials", method = RequestMethod.POST)
	public boolean addingSe(@RequestBody Credentials loginbean) {
		return adminservice.addSe(loginbean);
	}

	/**
	 * 
	 * @param engineerbean
	 * @return status
	 */
	@RequestMapping(value = "/addEngineer", method = RequestMethod.POST)
	public boolean addingEngineer(@RequestBody ServiceEngineer engineerbean) {
		return adminservice.addEngineer(engineerbean);
	}
	
	/**
	 * 
	 * @param loginbean
	 * @return status
	 */
	@RequestMapping(value = "/addUserCredentials", method = RequestMethod.POST)
	public boolean addingUser(@RequestBody Credentials loginbean) {
		return adminservice.addSe(loginbean);

	}
	
	/**
	 * 
	 * @param id
	 * @return status
	 */
	@RequestMapping(value = "/deletese", method = RequestMethod.POST)
	public boolean deleteServiceEngineer(@RequestBody String id) {
		return adminservice.deleteEngineer(id);
	}
}
