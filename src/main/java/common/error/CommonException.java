package common.error;

import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.validation.Errors;

public class CommonException extends RuntimeException {
	
	private Map<String, Object> errorInfo = new HashMap<>();
	
	private String method;
	private int statusCode;
	private String requestURL;
	
	protected String field; // 에러 발생 form 필드
	
	public CommonException(String message) {
		super(message);
	}
	
	public CommonException(String message, String fields) {
		super(message);
		this.field = fields;
	}
	
	public CommonException(String message, HttpServletRequest request) {
		this(message, request, 400);
	}
	
	public CommonException(String message, HttpServletRequest request, int statusCode) {
		super(message);
		
		method = request.getMethod().toUpperCase();
		requestURL = request.getRequestURL().toString();
		this.statusCode = statusCode; 
	}
	
	public CommonException(String message, HttpServletRequest request, HttpStatus status) {
		this(message, request, status.value());
	}
	
	public CommonException(Errors errors, HttpServletRequest request, HttpStatus status) {
		this(getErrorMessages(errors), request, status);
	}
	
	protected static String getErrorMessages(Errors errors) {
		String message = errors.getAllErrors().stream().map(error -> {
			
			String msg = null;
			String[] codes = error.getCodes();
			ResourceBundle bundle = ResourceBundle.getBundle("messages.errors");
			try {
				msg = bundle.getString(codes[0]);
			} catch (Exception e) {
				try {
					msg = bundle.getString(codes[1]);
				} catch (Exception ex) {
					try {
						msg = error.getDefaultMessage();
					} catch (Exception ex2) {
						msg = codes[0];
					}
				}
			}
			
			return msg;
		}).collect(Collectors.joining(","));
		
		return message;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public String getRequestURL() {
		return requestURL;
	}

	public void setRequestURL(String requestURL) {
		this.requestURL = requestURL;
	}
	
	public Map<String, Object> gets() {
		errorInfo.put("statusCode", statusCode);
		errorInfo.put("requestURL", requestURL);
		errorInfo.put("method", method);
		
		return errorInfo;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}
}



