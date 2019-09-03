package com.example.demo.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import pojo.ServiceEngineer;

@Repository
public interface EngineerRepo extends JpaRepository<ServiceEngineer, String> {
	@Query("select s from ServiceEngineer s where s.department=(select d from DepartmentBean d where d.department_id=?1) and s.currentpriority_ticket=?2 order by no_of_tickets_worked")
	public List<ServiceEngineer> checkDepaertmentEngineers(int id, String currentpriority);

	@Query("select s from ServiceEngineer s where s.department=(select d from DepartmentBean d where d.department_id=?1)")
	public List<ServiceEngineer> checkDeptEng(int department_id);

	@Modifying
	@Transactional
	@Query("UPDATE ServiceEngineer c SET c.currentpriority_ticket = :currentpriority_ticket WHERE c.serviceengineer = :serviceengineer")
	int updatecurrentpriority(@Param("serviceengineer") String serviceengineer,
			@Param("currentpriority_ticket") String currentpriority_ticket);

	@Modifying
	@Transactional
	@Query("UPDATE ServiceEngineer c SET c.no_of_tickets_worked =?1 WHERE c.serviceengineer =?2")
	public void updateWorkedOn(int workedOn, String se);

	@Query(value = "select (AVG(TIMESTAMPDIFF(day, start_date, completed_date))) from TicketBean  where serviceengineer=?1 and status='completed'", nativeQuery = true)
	String getAvgPerEngineer(String engineer);
}
