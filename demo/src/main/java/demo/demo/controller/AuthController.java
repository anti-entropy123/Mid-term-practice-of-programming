package demo.demo.controller;

import demo.demo.security.JwtAuthenticationResponse;
import demo.demo.service.AuthServiceImpl;

import com.nimbusds.oauth2.sdk.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.hutool.crypto.digest.BCrypt;
import demo.demo.dao.MemberDao;
import demo.demo.entity.Member;
import demo.demo.security.JwtAuthenticationRequest;


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
        authService.register(new Member(0, username, rawPassword, ""));
        return ResponseEntity.ok().build();
    }
}