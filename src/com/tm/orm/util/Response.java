package com.tm.orm.util;

public class Response {

	private String res;
	private boolean success;
	private String exception;

	public Response() {
		this.success = false;
		this.res = "";
		this.exception = "";
	}

	public Response(boolean success, String res, String exception) {
		this.success = success;
		this.res = res;
		this.exception = exception;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public void setResponse(String res) {
		this.res = res;
	}

	public void setException(String exception) {
		this.exception = exception;
	}

	public boolean isSuccess() {
		return this.success;
	}

	public String getResponse() {
		return this.res;
	}

	public String getException() {
		return this.exception;
	}

	@Override
	public String toString() {

		StringBuffer stringBuffer = new StringBuffer();
		stringBuffer.append("Success   : " + success + "\n\r");
		stringBuffer.append("Response  : " + res + "\n\r");
		stringBuffer.append("Exception : " + exception + "\n\r");

		return stringBuffer.toString();
	}

	@Override
	public boolean equals(Object object) {
		if(!(object instanceof com.tm.orm.util.Response)) return false;
		Response res = (Response)object;
		return this.toString().equals(res.toString());
	}
}