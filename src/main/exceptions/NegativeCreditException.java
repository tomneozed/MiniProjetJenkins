package main.exceptions;

public class NegativeCreditException extends Exception {
	
	private int errCode;

	public NegativeCreditException(int errCode, String message) {
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