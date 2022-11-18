package common.Util;

public class JsonData <T>{

	private boolean result;
	
	private String message;
	
	private T data;

	public boolean isResult() {
		return result;
	}

	public void setResult(boolean result) {
		this.result = result;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "JsonData [result=" + result + ", message=" + message + ", data=" + data + "]";
	}
	
	
	
	
}
