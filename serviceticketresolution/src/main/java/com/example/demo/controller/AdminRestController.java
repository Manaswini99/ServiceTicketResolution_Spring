package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.repository.AdminDao;

import pojo.Credentials;
import pojo.ServiceEngineer;

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
		System.out.println(loginbean);
		return admindao.addSe(loginbean);
	}

	/*
	 * THis method will add Engineer to ServiceEngineer
	 */
	@RequestMapping(value = "/addtose", method = RequestMethod.POST)
	public boolean addingEngineer(@RequestBody ServiceEngineer engineerbean) {
		System.out.println(engineerbean);
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
		System.out.println(id + "rest");
		return admindao.deleteEngineer(id);
	}
}
