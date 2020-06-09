package demo.demo.controller;

import demo.demo.requestbody.JwtAuthenticationRequest;
import demo.demo.vo.JwtAuthenticationResponse;
import demo.demo.requestbody.JwtRegisteRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import demo.demo.entity.Member;
import demo.demo.service.AuthServiceImpl;


@RestController
public class AuthController {
    @Value("${jwt.tokenHead}")
    private String tokenHeader;

    @Autowired
    private AuthServiceImpl authService;

    @PostMapping(value = "${jwt.route.authentication.path}")
	public ResponseEntity<?> logIn(@RequestBody JwtAuthenticationRequest request) {
        final String token = authService.login(Integer.valueOf(request.getUsername()), request.getPassword());
        return ResponseEntity.ok(new JwtAuthenticationResponse(tokenHeader + " " + token));
    }
    
    @PostMapping(value = "${jwt.route.authentication.register}")
    public ResponseEntity<?> register(@RequestBody JwtRegisteRequest request){
        authService.register(new Member(
            0, 
            request.getUsername(), 
            request.getPassword(), 
            request.getTitle(),
            request.getSex()));
        return ResponseEntity.ok().build();
    }
}