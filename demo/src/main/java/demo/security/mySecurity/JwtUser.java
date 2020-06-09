package demo.security.mySecurity;

import java.util.Collection;

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class JwtUser implements UserDetails {

    private final int id;
    private final String username;
    private final String password;
    private final String email;
    private final Collection<? extends GrantedAuthority> anthorities;
    private final String lastPasswordResetDate;

    public JwtUser(
            int id,
            String username,
            String password,
            String email,
            Collection<? extends GrantedAuthority> authorities,
            String lastPasswordResetDate ){
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.anthorities = authorities;
        this.lastPasswordResetDate = lastPasswordResetDate;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.anthorities;
    }

    @JsonIgnore
    public int getId(){
        return this.id;
    }

    @Override
    @JsonIgnore
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    // 账户是否未过期
    @Override
    @JsonIgnore
    public boolean isAccountNonExpired() {
        // * may need modify
        return true;
    }

    // 账户是否未锁定
    @Override
    @JsonIgnore
    public boolean isAccountNonLocked() {
        // * may need modify
        return true;
    }

    // 密码是否未过期
    @Override
    @JsonIgnore
    public boolean isCredentialsNonExpired() {
        // * may need modify
        return true;
    }

    // 账户是否激活
    @Override
    @JsonIgnore
    public boolean isEnabled() {
        // * may need modify
        return true;
    }

    @JsonIgnore
    public String getLastPasswordResetDate() {
        return this.lastPasswordResetDate;
    }

    @JsonIgnore
    public String getEmail(){
        return this.email;
    }
}