package service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pojo.Credentials;
import repository.*;

@Service
public class LoginService {
	@Autowired
	LoginRepo loginRepo;

	/**
	 * @param loginbean
	 * @return String status
	 */
	public String check(Credentials loginbean) {
		if (loginRepo.existsById(loginbean.getUsername())) {
			java.util.Optional<Credentials> user = loginRepo.findById(loginbean.getUsername());
			if (user.get().getPassword().equals(loginbean.getPassword())) {
				return user.get().getRoles().getRoleName();
			} else {
				return "wrong password";
			}

		} else {
			return "notfound";
		}
	}

	/**
	 * @return List<Credentials>
	 */
	public List<Credentials> getUsers() {
		return loginRepo.findAll();
	}

}