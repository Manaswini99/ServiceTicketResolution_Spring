package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.repository.EngineerDao;
import com.example.demo.repository.LoginDao;
import com.example.demo.repository.repo;

import pojo.Credentials;
import pojo.ServiceEngineer;

@RestController
@RequestMapping("/rest")
public class LoginRestController {

	@Autowired
	LoginDao ld;

	@Autowired
	EngineerDao engdao;

	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public String insert(@RequestBody Credentials loginbean) {
		return ld.check(loginbean);
	}

	@RequestMapping(value = "/getengineers", method = RequestMethod.GET)
	public List<ServiceEngineer> getEngineers() {
		return engdao.getEngineerList();
	}

	@RequestMapping(value = "/getUsers", method = RequestMethod.GET)
	public List<Credentials> getUsers() {
		return ld.getUsers();
	}
}
