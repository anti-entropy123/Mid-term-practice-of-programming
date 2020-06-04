package demo.demo.controller;

import demo.security.mySecurity.JwtAuthenticationRequest;
import demo.security.mySecurity.JwtAuthenticationResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import demo.demo.entity.Member;
import demo.demo.service.AuthServiceImpl;


@RestController
public class AuthController {
    @Value("${jwt.header}")
    private String tokenHeader;

    @Autowired
    private AuthServiceImpl authService;

    @PostMapping(value = "${jwt.route.authentication.path}")
	public ResponseEntity<?> logIn(@RequestBody JwtAuthenticationRequest request) {
        final String token = authService.login(Integer.valueOf(request.getUsername()), request.getPassword());
        return ResponseEntity.ok(new JwtAuthenticationResponse(token));
    }
    
    @PostMapping(value = "${jwt.route.authentication.register}")
    public ResponseEntity<?> register(@RequestBody JwtAuthenticationRequest request){
        final String username = request.getUsername();
        final String rawPassword = request.getPassword();
        authService.register(new Member(0, username, rawPassword, "普通员工"));
        return ResponseEntity.ok().build();
    }
}