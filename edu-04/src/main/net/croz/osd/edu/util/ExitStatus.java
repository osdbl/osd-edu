package net.croz.osd.edu.util;

public enum ExitStatus {
	USER_QUIT(0), 
	INVALID_CONFIG (1); 

	public Integer status;
	
	ExitStatus (Integer status) {
		this.status = status;
	}
}
