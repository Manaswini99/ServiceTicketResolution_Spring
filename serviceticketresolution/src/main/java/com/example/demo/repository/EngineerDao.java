package com.example.demo.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pojo.ServiceEngineer;
import pojo.TicketBean;

@Service
public class EngineerDao {
	@Autowired
	UserRepo repo;

	@Autowired
	EngineerRepo engrepo;

	public List<TicketBean> getTicketList() {
		return repo.findAll();
	}
	
/*
 * This method will set completed date and assings a pending ticket if any present
 */
	public void completeTicket(int id) {
		repo.setCompleteDate(id);
		repo.complete(id);
		Optional<TicketBean> ticket = repo.findById(id);
		repo.assignPending(ticket.get().getService_engineer().getServiceengineer());
		Optional<ServiceEngineer> se = engrepo.findById(ticket.get().getService_engineer().getServiceengineer());
		int workedOn = se.get().getNo_of_tickets_worked() + 1;
		engrepo.updateWorkedOn(workedOn, ticket.get().getService_engineer().getServiceengineer());
		Optional<TicketBean> newticket = repo.findongoing(ticket.get().getService_engineer().getServiceengineer());
		if (!newticket.isEmpty()) {
			String priority = newticket.get().getPriority();
			String engineer = newticket.get().getService_engineer().getServiceengineer();
			engrepo.updatecurrentpriority(engineer, priority);
		} else {
			engrepo.updatecurrentpriority(ticket.get().getService_engineer().getServiceengineer(), "no");
		}
	}

	public List<String> getavgPriority() {
		List<String> avglistPriority = new ArrayList<String>();
		avglistPriority.add(repo.getavgperlowpriority());
		avglistPriority.add(repo.getavgpermediumpriority());
		avglistPriority.add(repo.getavgperhighpriority());

		return avglistPriority;
	}

	public List<ServiceEngineer> getEngineerList() {
		return engrepo.findAll();
	}

	public List<String> getavgperEngineer() {

		List<String> avgservice = new ArrayList<String>();
		List<ServiceEngineer> seList = engrepo.findAll();

		for (int i = 0; i < seList.size(); i++) {
			avgservice.add(seList.get(i).getServiceengineer());
			String a = engrepo.getAvgPerEngineer(seList.get(i).getServiceengineer());
			if (a == null) {
				avgservice.add("No Tickets Resolved");
			} else {
				avgservice.add(a);
			}
		}
		return avgservice;
	}

	public Long getTicketName(Integer ticketid) {
		return repo.getTicketAge(ticketid);
	}

	public int updateTicketPriority(String newpriority, int ticket) {
		return repo.updateTicketPriority(newpriority, ticket);
	}

}
