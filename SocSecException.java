
public class SocSecException extends Exception{ //an exception for if a social security is invalid
	public SocSecException(String error) {
		super("Invalid social security number, " + error);
	}
}
