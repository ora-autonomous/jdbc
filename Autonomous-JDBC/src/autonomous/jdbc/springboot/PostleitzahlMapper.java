package autonomous.jdbc.springboot;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

/**
 * Simple Row mapper implementation class for Postleitzahlen table.
 *
 */
public class PostleitzahlMapper implements RowMapper<PostleitzahlDAO> {

	@Override
	public PostleitzahlDAO mapRow(ResultSet rs, int rowNo) throws SQLException {
		PostleitzahlDAO postleitzahl = new PostleitzahlDAO();
		postleitzahl.setPlz(rs.getInt("plz"));
		postleitzahl.setOrt(rs.getString("ort"));
		postleitzahl.setZusatz(rs.getString("zusatz"));

		return postleitzahl;
	}

}