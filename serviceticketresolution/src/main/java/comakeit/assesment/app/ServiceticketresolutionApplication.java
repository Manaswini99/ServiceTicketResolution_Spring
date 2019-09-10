package comakeit.assesment.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
@ComponentScan(basePackages = {"comakeit.assesment.app","controller","service","rest"})
@EntityScan(basePackages = {"pojo"})
@EnableJpaRepositories(basePackages = {"repository","service"})
@SpringBootApplication
public class ServiceticketresolutionApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceticketresolutionApplication.class, args);
	}

}
