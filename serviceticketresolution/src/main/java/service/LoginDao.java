package service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import net.bytebuddy.dynamic.DynamicType.Builder.FieldDefinition.Optional;
import pojo.Credentials;
import pojo.ServiceEngineer;
import repository.*;
@Service
public class LoginDao {
	@Autowired
	repo repo;
/*
 * This method will get username and password to check if user is valid or not
 */
	public String check(Credentials loginbean) {
		if (repo.existsById(loginbean.getUsername())) {
			java.util.Optional<Credentials> user = repo.findById(loginbean.getUsername());
			if (user.get().getPassword().equals(loginbean.getPassword())) {
				return user.get().getRoles().getRoleName();
			} else {
				return "wrong password";
			}

		} else {
			return "notfound";
		}
	}

	public List<Credentials> getUsers() {

		return repo.findAll();
	}

}