package com.masai.controller;



import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.masai.exceptions.LoginException;
import com.masai.model.CurrentUserSession;
import com.masai.model.LoginDTO;
import com.masai.service.loginService.LoginService;

import springfox.documentation.swagger.readers.operation.ResponseHeaders;

@RestController
@CrossOrigin(origins = "*")
public class LoginController {

	@Autowired
	private LoginService customerLogin;
	

	
	@PostMapping("/login")
	public ResponseEntity<CurrentUserSession> logInCustomer(@RequestBody LoginDTO dto) throws LoginException {
		
		CurrentUserSession result = customerLogin.logIntoAccount(dto);
		
		HttpHeaders headers = new HttpHeaders();
		
		
		return new ResponseEntity<CurrentUserSession>(result,headers,HttpStatus.OK );
		
		
	}
	
	@GetMapping("/logout")
	public ResponseEntity<String> logOutCustomer(@RequestParam String key) throws LoginException {
		
		String result = customerLogin.logOutFromAccount(key);
		
		HttpHeaders headers = new HttpHeaders();
		
		
		return new ResponseEntity<String>(result,headers,HttpStatus.OK );
		
		
	}
	
	@PostMapping("/logout")
	public String logoutCustomer(@RequestParam(required = false) String key) throws LoginException {
		return customerLogin.logOutFromAccount(key);
		
	}
	
		
}
