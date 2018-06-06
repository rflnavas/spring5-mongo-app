package guru.springframework.exception;

public class NoModelFoundException extends RuntimeException {
	
	private static final long serialVersionUID = 81049558091929576L;

	public NoModelFoundException(String msg) {
		super(msg);
	}
}
