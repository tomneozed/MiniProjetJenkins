package main.exceptions;

public class NotEnoughMoneyException extends Exception {
	
	private int errCode;

	public NotEnoughMoneyException(int errCode, String message) {
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