package autonomous.jdbc.springboot;

/**
 * Simple DAO class implementation for Postleitzahlen table.
 *
 */
public class PostleitzahlDAO {

	private int plz;
	private String ort;
	private String zusatz;

	public int getPlz() {
		return plz;
	}

	public void setPlz(int plz) {
		this.plz = plz;
	}

	public String getOrt() {
		return ort;
	}

	public void setOrt(String ort) {
		this.ort = ort;
	}

	public String getZusatz() {
		return zusatz;
	}

	public void setZusatz(String zusatz) {
		this.zusatz = zusatz;
	}

}