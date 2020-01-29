package autonomous.jdbc.springboot;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

/**
 * Simple Java class which uses Spring's JdbcTemplate class to implement
 * business logic.
 * 
 */
public class PostleitzahlenJDBCTemplate {
	private JdbcTemplate jdbcTemplate;

	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);

	}

	public void displayPostleitzahlenList() {
		final String sql = "SELECT plz, ort, zusatz FROM postleitzahlen where ort like 'Mülheim%'";
		List<PostleitzahlDAO> postleitzahlen = jdbcTemplate.query(sql, new PostleitzahlMapper());

		System.out.println(
				String.format("%20s %20s %20s \n", "PLZ", "ORT", "ZUSATZ"));
		

		for (PostleitzahlDAO postleitzahl : postleitzahlen) {
			System.out.println(String.format("%20d %20s %20s", postleitzahl.getPlz(), postleitzahl.getOrt(),
					postleitzahl.getZusatz()));
		}
	}
}