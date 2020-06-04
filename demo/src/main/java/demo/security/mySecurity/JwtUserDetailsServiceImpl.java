package demo.security.mySecurity;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import demo.security.mySecurity.JwtUser;
import demo.demo.dao.MemberDao;
import demo.demo.entity.Member;

@Service
public class JwtUserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private MemberDao memberDao;

    private List<GrantedAuthority> mapToGrantedAuthorities(String title){
        List<String> temp = new ArrayList<String>();
        switch(title){
            case "普通员工":
                temp.add("ROLE_普通员工");
                break;
            case "行政部员工":
                temp.add("ROLE_行政部员工");
                break;
            case "财务部员工":
                temp.add("ROLE_财务部员工");
                break;
            case "项目经理":
                temp.add("ROLE_普通员工");
                temp.add("ROLE_项目经理");
                break;    
            case "副总经理":
                temp.add("ROLE_普通员工");
                temp.add("ROLE_项目经理");
                temp.add("ROLE_副总经理");
                break;    
            case "总经理":
                temp.add("ROLE_普通员工");
                temp.add("ROLE_项目经理");
                temp.add("ROLE_副总经理");
                temp.add("ROLE_总经理");
                break;
        }
        return temp.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member user = memberDao.qureyUser(Integer.valueOf(username));

        if(user == null){
            throw new UsernameNotFoundException(String.format("No user found with username '%s'.", username));
        }else{
            return new JwtUser(
                user.getId(), 
                user.getName(), 
                user.getPassword(), 
                "unkownEmail", 
                mapToGrantedAuthorities(user.getTitle()), 
                "2000/06/21");
        }
    }
}