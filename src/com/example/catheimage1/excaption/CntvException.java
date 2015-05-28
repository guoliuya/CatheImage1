package com.example.catheimage1.excaption;

/**
* @ClassName: CntvException
* @Description: TODO 异常
* @author Cheng Yong
* @date  2013-4-11
*
*/
public class CntvException extends Exception {
	
	private static final long serialVersionUID = 7633894647653066893L;
	
	private int statusCode = -1;
	
	private String tag = this.getClass().getName();

    public CntvException(String msg) {
        super(msg);
    }

    public CntvException(Exception cause) {
        super(cause);
    }

    public CntvException(String msg, int statusCode) {
        super(msg);
        this.statusCode = statusCode;
    }

    public CntvException(String msg, Exception cause) {
        super(msg, cause);
    }

    public CntvException(String msg, Exception cause, int statusCode) {
        super(msg, cause);
        this.statusCode = statusCode;
    }

    public int getStatusCode() {
        return this.statusCode;
    }
}
