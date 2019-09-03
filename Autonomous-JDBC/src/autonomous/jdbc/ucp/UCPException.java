package autonomous.jdbc.ucp;

public class UCPException extends Exception {

	private static final long serialVersionUID = -22944389587051109L;

	public UCPException(String errorMessage) {
        super(errorMessage);
    }
}
