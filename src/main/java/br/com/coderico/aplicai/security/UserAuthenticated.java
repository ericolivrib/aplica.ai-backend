package br.com.coderico.aplicai.security;

import br.com.coderico.aplicai.entity.Role;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@AllArgsConstructor
public class UserAuthenticated implements UserDetails {

    private String username;
    private String password;
    private GrantedAuthority authority;

    public UserAuthenticated(String username, String password, Role authority) {
        this.username = username;
        this.password = password;
        this.authority = new SimpleGrantedAuthority(authority.getLabel());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(authority);
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }
}
