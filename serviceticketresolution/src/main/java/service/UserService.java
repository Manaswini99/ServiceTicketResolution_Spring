package service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pojo.DepartmentBean;
import pojo.ServiceEngineer;
import pojo.TicketBean;
import repository.*;
@Service
public class UserService {
	@Autowired
	UserRepo userRepo;

	@Autowired
	EngineerRepo engineerRepo;
	
	//returns all departments
	public List<DepartmentBean> getDeptList() {
		return userRepo.getDeptList();
	}
	
	//ticket is added
	public Boolean insertTicket(TicketBean ticket) {
		TicketBean ticketbean = engineerAssignment(ticket);
		userRepo.save(ticketbean);
		return true;
	}
	
	//assigns engineer to ticket 
	public TicketBean engineerAssignment(TicketBean ticketbean) {
		List<ServiceEngineer> service_engineer = engineerRepo
				.checkDepaertmentEngineers(ticketbean.getDepartment().getDepartment_id(), "no");
		String engineer;
		if (service_engineer.size() > 0) {
			engineer = service_engineer.get(0).getServiceengineer();
			ServiceEngineer se = new ServiceEngineer();
			se.setServiceengineer(engineer);
			ticketbean.setService_engineer(se);
			ticketbean.setStatus("ongoing");
			engineerRepo.updatecurrentpriority(engineer,ticketbean.getPriority() );
			return ticketbean;
		} else {
			String priority = ticketbean.getPriority();
			if (priority.equals("medium")) {
				List<ServiceEngineer> engineers = engineerRepo
						.checkDepaertmentEngineers(ticketbean.getDepartment().getDepartment_id(), "low");
				if (engineers.size() > 0) {
					engineer = engineers.get(0).getServiceengineer();
					ServiceEngineer se = new ServiceEngineer();
					se.setServiceengineer(engineer);
					ticketbean.setService_engineer(se);
					Optional<ServiceEngineer> en = engineerRepo.findById(engineer);
					ServiceEngineer seng = en.get();
					seng.setCurrentpriority_ticket(priority);
					engineerRepo.save(seng);
					userRepo.updateTicketStatus(se, "pending");
					ticketbean.setStatus("ongoing");
					return ticketbean;
				} else {
					List<ServiceEngineer> eng = engineerRepo.checkDeptEng(ticketbean.getDepartment().getDepartment_id());
					engineer = eng.get(0).getServiceengineer();
					ServiceEngineer se = new ServiceEngineer();
					se.setServiceengineer(engineer);
					ticketbean.setService_engineer(se);
					ticketbean.setStatus("pending");
					return ticketbean;
				}

			} else if (priority.equals("high")) {
				List<ServiceEngineer> engineers = engineerRepo
						.checkDepaertmentEngineers(ticketbean.getDepartment().getDepartment_id(), "low");
				if (engineers.size() > 0) {
					engineer = engineers.get(0).getServiceengineer();
					ServiceEngineer se = new ServiceEngineer();
					se.setServiceengineer(engineer);
					Optional<ServiceEngineer> en = engineerRepo.findById(engineer);
					ServiceEngineer seng = en.get();
					seng.setCurrentpriority_ticket(priority);
					engineerRepo.save(seng);
					userRepo.updateTicketStatus(se, "pending");
					ticketbean.setStatus("ongoing");
					return ticketbean;
				} else {
					List<ServiceEngineer> engineermed = engineerRepo
							.checkDepaertmentEngineers(ticketbean.getDepartment().getDepartment_id(), "medium");
					if (engineermed.size() > 0) {

						engineer = engineermed.get(0).getServiceengineer();
						ServiceEngineer se = new ServiceEngineer();
						se.setServiceengineer(engineer);
						Optional<ServiceEngineer> en = engineerRepo.findById(engineer);
						ServiceEngineer seng = en.get();
						seng.setCurrentpriority_ticket(priority);
						engineerRepo.save(seng);
						userRepo.updateTicketStatus(se, "pending");
						ticketbean.setStatus("ongoing");
						return ticketbean;
					}
				}
			} else if (priority.equals("low")) {
				List<ServiceEngineer> eng = engineerRepo.checkDeptEng(ticketbean.getDepartment().getDepartment_id());
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
	
	//returns list of tickets
	public List<TicketBean> getTicketList() {
		return userRepo.findAll();

	}

}
