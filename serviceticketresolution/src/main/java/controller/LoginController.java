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
	public ModelAndView logincontroller(HttpServletRequest request) {
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
	public ModelAndView logincontrol(Credentials credentials, HttpSession session) {
		String url = Constants.url+"/rest/login";
		RestTemplate rt = new RestTemplate();
		session.setAttribute("user", credentials.getUsername());
		String role = rt.postForObject(url, credentials, String.class);
		if (role.equals("user")) {
			ModelAndView modelandview = new ModelAndView("/user");
			modelandview.addObject("username", credentials.getUsername());
			return modelandview;
		} else if (role.equals("service_engineer")) {
			ModelAndView modelandview1 = new ModelAndView("/engineer");
			modelandview1.addObject("username", credentials.getUsername());
			return modelandview1;
		} else if (role.equals("admin")) {

			ModelAndView modelandview2 = new ModelAndView("/admin");
			modelandview2.addObject("username", credentials.getUsername());

			return modelandview2;
		} else {
			ModelAndView modelandview3 = new ModelAndView("/index");
			modelandview3.addObject("error", "Invalid Credentials!!");
			return modelandview3;
		}
	}
}
