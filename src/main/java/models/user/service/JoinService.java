package models.user.service;

import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;

import models.user.JoinRequest;

@Service
public class JoinService {

	
	public void join(JoinRequest joinRequest, Errors errors) {
		if(errors.hasErrors()) {
			return;
		}
		
		String memId = joinRequest.getMemId();
		
		
	}
	
}
