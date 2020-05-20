package demo.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import cn.org.faster.framework.auth.AuthService;
import demo.demo.dao.MemberDao;
import demo.demo.entity.Member;
import demo.demo.security.JwtTokenUtil;
import demo.demo.security.JwtUser;

@Service
public class AuthServiceImpl {
    private AuthenticationManager authenticationManager;
    private UserDetailsService userDetailsService;
    private JwtTokenUtil jwtTokenUtil;
    private MemberDao memberDao;

    @Value("{Jwt.tokenHead}")
    private String tokenHead;


    @Autowired
    public AuthServiceImpl(
            AuthenticationManager authenticationManager,
            UserDetailsService userDetailsService,
            JwtTokenUtil jwtTokenUtil,
            MemberDao memberDao){
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.jwtTokenUtil = jwtTokenUtil;
        this.memberDao = memberDao;
    }
    
    public String login(int userId, String password) {
        UsernamePasswordAuthenticationToken upToken = new UsernamePasswordAuthenticationToken(userId, password);
        final Authentication authentication = authenticationManager.authenticate(upToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        final UserDetails userDetails = userDetailsService.loadUserByUsername(String.valueOf(userId));
        final String token = jwtTokenUtil.generateToken(userDetails);
        return token;
    }


    public void register(Member member){
        final int userId = member.getId();
        if(memberDao.qureyUser(userId) != null){
            return;
        }
        else{
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            final String rawPassword = member.getPassword();
            member.setPassword(encoder.encode(rawPassword));
            member.setTitle("普通员工");
            memberDao.insertMember(member);
        }
    }

    public String refresh(String oldToken) {
        final String token = oldToken.substring(tokenHead.length());
        // String username = jwtTokenUtil.getUsernameFromToken(token);
        
        return jwtTokenUtil.refreshToken(token);
    }
}