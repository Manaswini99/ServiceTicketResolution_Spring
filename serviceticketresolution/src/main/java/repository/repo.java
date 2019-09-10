package repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.cdi.JpaRepositoryExtension;
import org.springframework.stereotype.Repository;

import pojo.Credentials;

@Repository
public interface repo extends JpaRepository<Credentials, String> {
}
