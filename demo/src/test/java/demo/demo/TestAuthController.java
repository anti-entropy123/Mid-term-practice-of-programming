package demo.demo;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import demo.demo.controller.AuthController;
import demo.demo.security.JwtAuthenticationRequest;

public class TestAuthController extends TestBase{
    
    @Autowired
    private AuthController controller; 

    @Test
    public void testLogin(){
        ResponseEntity<?> token = controller.logIn(new JwtAuthenticationRequest("123", "尤嘉宁"));
        System.out.println(token.getStatusCodeValue());
    }

    @Test
    public void  testRegiste(){
        controller.register(new JwtAuthenticationRequest("尤嘉宁", "123456"));
    }

}