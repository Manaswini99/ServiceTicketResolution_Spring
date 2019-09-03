package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pojo.DepartmentBean;
import pojo.ServiceEngineer;
import pojo.TicketBean;

@Service
public class UserDao {
	@Autowired
	UserRepo repo;

	@Autowired
	EngineerRepo engrepo;

	public List<DepartmentBean> getDeptList() {
		return repo.getDeptList();
	}

	public Boolean insertTicket(TicketBean ticket) {
		TicketBean ticketbean = engineerAssignment(ticket);
		repo.save(ticketbean);
		return true;
	}
	
/*
 * This method assign ticket raised by user to engineer of corresponding issue department 
 * and if no engineer is free it will check for priorities and if engineer is working on low priority and 
 * raised ticket is of high priority he will be assigned new and the old working will be kept as pending
 */
	public TicketBean engineerAssignment(TicketBean ticketbean) {
		List<ServiceEngineer> service_engineer = engrepo
				.checkDepaertmentEngineers(ticketbean.getDepartment().getDepartment_id(), "no");
		String engineer;
		int lowPriorityTicket;
		if (service_engineer.size() > 0) {
			engineer = service_engineer.get(0).getServiceengineer();
			ServiceEngineer se = new ServiceEngineer();
			se.setServiceengineer(engineer);
			ticketbean.setService_engineer(se);
			ticketbean.setStatus("ongoing");
			engrepo.updatecurrentpriority(engineer,ticketbean.getPriority() );
			return ticketbean;
		} else {
			String priority = ticketbean.getPriority();
			if (priority.equals("medium")) {
				List<ServiceEngineer> engineers = engrepo
						.checkDepaertmentEngineers(ticketbean.getDepartment().getDepartment_id(), "low");
				if (engineers.size() > 0) {
					engineer = engineers.get(0).getServiceengineer();
					ServiceEngineer se = new ServiceEngineer();
					se.setServiceengineer(engineer);
					ticketbean.setService_engineer(se);
					Optional<ServiceEngineer> en = engrepo.findById(engineer);
					ServiceEngineer seng = en.get();
					seng.setCurrentpriority_ticket(priority);
					engrepo.save(seng);
					repo.updateTicketStatus(se, "pending");
					ticketbean.setStatus("ongoing");
					return ticketbean;
				} else {
					List<ServiceEngineer> eng = engrepo.checkDeptEng(ticketbean.getDepartment().getDepartment_id());
					engineer = eng.get(0).getServiceengineer();
					ServiceEngineer se = new ServiceEngineer();
					se.setServiceengineer(engineer);
					ticketbean.setService_engineer(se);
					ticketbean.setStatus("pending");
					return ticketbean;
				}

			} else if (priority.equals("high")) {
				List<ServiceEngineer> engineers = engrepo
						.checkDepaertmentEngineers(ticketbean.getDepartment().getDepartment_id(), "low");
				if (engineers.size() > 0) {
					engineer = engineers.get(0).getServiceengineer();
					ServiceEngineer se = new ServiceEngineer();
					se.setServiceengineer(engineer);
					Optional<ServiceEngineer> en = engrepo.findById(engineer);
					ServiceEngineer seng = en.get();
					seng.setCurrentpriority_ticket(priority);
					engrepo.save(seng);
					repo.updateTicketStatus(se, "pending");
					ticketbean.setStatus("ongoing");
					return ticketbean;
				} else {
					List<ServiceEngineer> engineermed = engrepo
							.checkDepaertmentEngineers(ticketbean.getDepartment().getDepartment_id(), "medium");
					if (engineermed.size() > 0) {

						engineer = engineermed.get(0).getServiceengineer();
						ServiceEngineer se = new ServiceEngineer();
						se.setServiceengineer(engineer);
						Optional<ServiceEngineer> en = engrepo.findById(engineer);
						ServiceEngineer seng = en.get();
						seng.setCurrentpriority_ticket(priority);
						engrepo.save(seng);
						repo.updateTicketStatus(se, "pending");
						ticketbean.setStatus("ongoing");
						return ticketbean;
					}
				}
			} else if (priority.equals("low")) {
				List<ServiceEngineer> eng = engrepo.checkDeptEng(ticketbean.getDepartment().getDepartment_id());
				engineer = eng.get(0).getServiceengineer();
				ServiceEngineer se = new ServiceEngineer();
				se.setServiceengineer(engineer);
				ticketbean.setService_engineer(se);
				ticketbean.setStatus("pending");
				return ticketbean;
			}
		}
		return ticketbean;
	}

	public List<TicketBean> getTicketList() {
		return repo.findAll();

	}

}
