package rest;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pojo.Credentials;
import pojo.ServiceEngineer;
import service.*;

@RestController
@RequestMapping("/admincontroller")
public class AdminRestController {
	@Autowired
	AdminDao admindao;
	
	/*
	 * This method adds Engineer to Credentials 
	 */
	@RequestMapping(value = "/addcred", method = RequestMethod.POST)
	public boolean addingSe(@RequestBody Credentials loginbean) {
		
		return admindao.addSe(loginbean);
	}

	/*
	 * THis method will add Engineer to ServiceEngineer
	 */
	@RequestMapping(value = "/addtose", method = RequestMethod.POST)
	public boolean addingEngineer(@RequestBody ServiceEngineer engineerbean) {
		
		return admindao.addEngineer(engineerbean);

	}

	/*
	 * This method will add User
	 */
	@RequestMapping(value = "/addusercred", method = RequestMethod.POST)
	public boolean addingUser(@RequestBody Credentials loginbean) {
		return admindao.addSe(loginbean);

	}

	@RequestMapping(value = "/deletese", method = RequestMethod.POST)
	public boolean deletese(@RequestBody String id) {
		return admindao.deleteEngineer(id);
	}
}
