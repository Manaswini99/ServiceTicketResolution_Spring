package repository;

import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import pojo.DepartmentBean;
import pojo.ServiceEngineer;
import pojo.TicketBean;

@Repository
@Component
public interface UserRepo extends JpaRepository<TicketBean, Integer> {
	@Query("select s from DepartmentBean s")
	public List<DepartmentBean> getDeptList();

	@Modifying
	@Transactional
	@Query("UPDATE TicketBean c SET c.status = :status WHERE  c.service_engineer = :serviceengineer and c.status='ongoing'")
	int updateTicketStatus(@Param("serviceengineer") ServiceEngineer se, @Param("status") String status);

	@Modifying
	@Transactional
	@Query("UPDATE TicketBean c SET c.status ='completed' WHERE  c.ticketid = :ticketid and c.status='ongoing'")
	int complete(@Param("ticketid") int id);

	@Query(value = "select AVG(TIMESTAMPDIFF(DAY, start_date, completed_date)) from TicketBean where status ='completed' and priority='low'", nativeQuery = true)
	String getavgperlowpriority();

	@Query(value = "select AVG(TIMESTAMPDIFF(DAY, start_date, completed_date)) from TicketBean where status ='completed' and priority='high'", nativeQuery = true)
	String getavgperhighpriority();

	@Query(value = "select AVG(TIMESTAMPDIFF(DAY, start_date, completed_date)) from TicketBean where status ='completed' and priority='medium'", nativeQuery = true)
	String getavgpermediumpriority();

	@Modifying
	@Transactional
	@Query(value = "UPDATE TicketBean  SET status ='ongoing' WHERE serviceengineer =?1  and status='pending' order by requestedend_date", nativeQuery = true)
	int assignPending(String service_engineer);

	@Query(value = "select * from TicketBean  where status='ongoing' and serviceengineer=?1", nativeQuery = true)
	public Optional<TicketBean> findongoing(String serviceengineer);

	@Modifying
	@Transactional
	@Query(value = "update TicketBean  SET completed_date =curdate() WHERE  ticketid=?1", nativeQuery = true)
	public void setCompleteDate(int id);

	@Query(value = "select AVG(TIMESTAMPDIFF(DAY, start_date,curdate())) from TicketBean  where  ticketid=?1", nativeQuery = true)
	Long getTicketAge(Integer id);

	@Modifying
	@Transactional
	@Query(value = "update TicketBean  SET priority =?1 WHERE  ticketid=?2", nativeQuery = true)
	int updateTicketPriority(String newpriority, int ticketid);

}