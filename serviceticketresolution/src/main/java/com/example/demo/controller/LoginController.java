package com.example.demo.controller;

import java.net.http.HttpRequest;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import jdk.nashorn.internal.ir.RuntimeNode.Request;
import pojo.Credentials;
import pojo.ServiceEngineer;

@Controller
@RequestMapping("/control")

public class LoginController {
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView logincontroller(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView("index");

		Credentials credentials = new Credentials();

		mv.addObject(credentials);
		return mv;
	}

	/*
	 * THis method will redirect users to their corresponding
	 * pages based on role
	 */
	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView logincontrol(Credentials credentials, HttpSession session) {
		String url = "http://localhost:8081/rest/insert";
		RestTemplate rt = new RestTemplate();
		session.setAttribute("user", credentials.getUsername());
		String role = rt.postForObject(url, credentials, String.class);
		if (role.equals("user")) {
			ModelAndView mv = new ModelAndView("/user");
			mv.addObject("username", credentials.getUsername());
			return mv;
		} else if (role.equals("service_engineer")) {
			ModelAndView mv = new ModelAndView("/engineer");
			mv.addObject("username", credentials.getUsername());
			return mv;
		} else if (role.equals("admin")) {

			ModelAndView mv = new ModelAndView("/admin");
			mv.addObject("username", credentials.getUsername());

			return mv;
		} else {
			ModelAndView mv = new ModelAndView("/index");
			mv.addObject("error", "Invalid Credentials!!");
			return mv;
		}
	}
}
