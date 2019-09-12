package service;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import pojo.Credentials;
import pojo.ServiceEngineer;
import repository.*;

@Service
@Component
@EnableAutoConfiguration
public class AdminService {
	
	@Autowired
	LoginRepo loginRepo;

	@Autowired
	EngineerRepo engineerRepo;
	
	//adds ServiceEngineer
	public boolean addSe(Credentials loginbean) {
		if (!loginRepo.existsById(loginbean.getUsername())) {
			loginRepo.save(loginbean);
			return true;
		} else {
			return false;
		}
	}
	

	public boolean addEngineer(ServiceEngineer engineerbean) {
		if (!engineerRepo.existsById(engineerbean.getServiceengineer())) {
			engineerRepo.save(engineerbean);
		}
		return true;
	}

	public boolean deleteEngineer(String id) {
		String username = id;
		Optional<ServiceEngineer> se = engineerRepo.findById(username);
		loginRepo.deleteById(id);
		engineerRepo.delete(se.get());
		return true;
	}

}
