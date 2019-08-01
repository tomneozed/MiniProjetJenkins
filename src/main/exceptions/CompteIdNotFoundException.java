package main.exceptions;

public class CompteIdNotFoundException extends Exception {
	
	private int errCode;

	public CompteIdNotFoundException(int errCode, String message) {
		super(message);
		this.errCode = errCode;
	}

	public int getErrCode() {
		return errCode;
	}

	public void setErrCode(int errCode) {
		this.errCode = errCode;
	}
}