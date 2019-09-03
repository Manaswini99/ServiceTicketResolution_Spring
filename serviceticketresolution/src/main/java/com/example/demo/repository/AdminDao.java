package com.example.demo.repository;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pojo.Credentials;
import pojo.ServiceEngineer;

@Service
public class AdminDao {
	@Autowired
	repo repo;

	@Autowired
	EngineerRepo engrepo;

	@Autowired
	UserRepo userepo;

	public boolean addSe(Credentials loginbean) {
		if (!repo.existsById(loginbean.getUsername())) {
			repo.save(loginbean);
			return true;
		} else {
			return false;
		}
	}

	public boolean addEngineer(ServiceEngineer engineerbean) {
		if (!engrepo.existsById(engineerbean.getServiceengineer())) {
			engrepo.save(engineerbean);
		}
		return true;
	}

	public boolean deleteEngineer(String id) {
		String username = id;
		Optional<ServiceEngineer> se = engrepo.findById(username);
		repo.deleteById(id);
		engrepo.delete(se.get());
		return true;

	}

}
