package autonomous.jdbc.springboot;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ImportResource;

/**
 * SpringBoot application main class. It uses JdbcTemplate class which
 * internally uses UCP for connection check-outs and check-ins.
 *
 */
// Specify the Spring XML config file name
@ImportResource({ "classpath*:autonomous/jdbc/springboot/PostleitzahlenAppConfig.xml" })
public class PostleitzahlenApplication {

	public static void main(String[] args) {
		SpringApplication.run(autonomous.jdbc.springboot.PostleitzahlenApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
		return args -> {
			final PostleitzahlenJDBCTemplate postleitzahlenJDBCTemplate = (PostleitzahlenJDBCTemplate) ctx.getBean("PostleitzahlenJDBCTemplate");
			System.out.println("Auflistung der Postleitzahlen: ");
			postleitzahlenJDBCTemplate.displayPostleitzahlenList();
		};
	}

}