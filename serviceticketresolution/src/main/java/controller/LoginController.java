package controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import comakeit.assesment.app.Constants;
import pojo.Credentials;


@Controller
@RequestMapping("/control")
public class LoginController {
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView loginController(HttpServletRequest request) {
		ModelAndView modelandview = new ModelAndView("index");
		Credentials credentials = new Credentials();
		modelandview.addObject(credentials);
		return modelandview;
	}

	/*
	 * THis method will redirect users to their corresponding
	 * pages based on role
	 */
	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView loginControl(Credentials credentials, HttpSession session) {
		String url = Constants.url+"/rest/login";
		RestTemplate rt = new RestTemplate();
		session.setAttribute("user", credentials.getUsername());
		String role = rt.postForObject(url, credentials, String.class);
		if (role.equals("user")) {
			ModelAndView modelandview = new ModelAndView("/user");
			modelandview.addObject("username", credentials.getUsername());
			return modelandview;
		} else if (role.equals("service_engineer")) {
			ModelAndView modelandviewEng = new ModelAndView("/engineer");
			modelandviewEng.addObject("username", credentials.getUsername());
			return modelandviewEng;
		} else if (role.equals("admin")) {

			ModelAndView modelandviewAdmin = new ModelAndView("/admin");
			modelandviewAdmin.addObject("username", credentials.getUsername());

			return modelandviewAdmin;
		} else {
			ModelAndView modelandviewInvalid = new ModelAndView("/index");
			modelandviewInvalid.addObject("error", "Invalid Credentials!!");
			return modelandviewInvalid;
		}
	}
}
