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

import demo.demo.dao.ApplicationDao;
import demo.demo.dao.MemberDao;
import demo.demo.entity.HolidayBalance;
import demo.demo.entity.Member;
import demo.utils.ConfigData;
import demo.utils.JwtTokenUtil;
import demo.security.mySecurity.JwtUser;

@Service
public class AuthServiceImpl implements AuthService{
    private AuthenticationManager authenticationManager;
    private UserDetailsService userDetailsService;
    private JwtTokenUtil jwtTokenUtil;
    private MemberDao memberDao;
    private ApplicationDao applicationDao;
    private ConfigData configData;

    @Value("{Jwt.tokenHead}")
    private String tokenHead;

    @Autowired
    public AuthServiceImpl(
            AuthenticationManager authenticationManager,
            UserDetailsService userDetailsService,
            JwtTokenUtil jwtTokenUtil,
            MemberDao memberDao,
            ApplicationDao applicationDao,
            ConfigData configData){
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.jwtTokenUtil = jwtTokenUtil;
        this.memberDao = memberDao;
        this.applicationDao = applicationDao;
        this.configData = configData;
    }
    
    @Override
    public String login(int userId, String password) {
        UsernamePasswordAuthenticationToken upToken = new UsernamePasswordAuthenticationToken(userId, password);
        final Authentication authentication = authenticationManager.authenticate(upToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        final UserDetails userDetails = userDetailsService.loadUserByUsername(String.valueOf(userId));
        final String token = jwtTokenUtil.generateToken((JwtUser)userDetails);
        return token;
    }


    @Override
    public void register(Member member){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        final String rawPassword = member.getPassword();
        member.setPassword(encoder.encode(rawPassword));
        
        int userId = memberDao.insertMember(member);
        // 设置年假
        applicationDao.insertHolidayBalance(new HolidayBalance(userId, configData.getAnnualLeave(), "annualLeave"));
        // 设置产假
        applicationDao.insertHolidayBalance(new HolidayBalance(
                                                userId,
                                                member.getSex().equals("male")? 0: configData.getMaternityLeave(),
                                                "maternityLeave"));
        // 设置探亲假
        applicationDao.insertHolidayBalance(new HolidayBalance(
            userId, configData.getHomeLeave(), "homeLeave"
        ));
        // 设置病假
        applicationDao.insertHolidayBalance(new HolidayBalance(
            userId, configData.getSickLeave(), "sickLeave"
        ));
        // 设置事假
        applicationDao.insertHolidayBalance(new HolidayBalance(
            userId, configData.getAbsenceLeave(), "absenceLeave"
        ));
    }

    @Override
    public String refresh(String oldToken) {
        final String token = oldToken.substring(tokenHead.length());
        // String username = jwtTokenUtil.getUsernameFromToken(token);
        
        return jwtTokenUtil.refreshToken(token);
    }
}