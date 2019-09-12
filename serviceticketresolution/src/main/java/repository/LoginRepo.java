package repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pojo.Credentials;

@Repository
public interface LoginRepo extends JpaRepository<Credentials, String> {
}
