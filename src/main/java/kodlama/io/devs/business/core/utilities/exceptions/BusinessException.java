package kodlama.io.devs.business.core.utilities.exceptions;

public class BusinessException extends RuntimeException{

	String message;
	
	public BusinessException(String message) {
		super(message);
	}
}
