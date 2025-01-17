package com.cyfrifpro.config;

import java.util.Collection;
import java.util.List;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import com.cyfrifpro.model.Manager;
import com.cyfrifpro.model.Admin;
import com.cyfrifpro.model.Client;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserInfoConfig implements UserDetails {

    private static final long serialVersionUID = 1L;

    private String email;
    private String password;
    private List<GrantedAuthority> authorities;

    // Constructor to initialize UserInfoConfig from Manager or Client
    public UserInfoConfig(Manager manager) {
        this.email = manager.getEmail();
        this.password = manager.getPassword();
        this.authorities = List.of(new SimpleGrantedAuthority("ROLE_" + manager.getRole()));
    }

    public UserInfoConfig(Client client) {
        this.email = client.getEmail();
        this.password = client.getPassword();
        this.authorities = List.of(new SimpleGrantedAuthority("ROLE_" + client.getRole()));
    }
    
    public UserInfoConfig(Admin admin) {
        this.email = admin.getEmail();
        this.password = admin.getPassword();
        this.authorities = List.of(new SimpleGrantedAuthority("ROLE_" + admin.getRole()));
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // You can customize this based on your business logic
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // Customize if necessary
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // Customize if necessary
    }

    @Override
    public boolean isEnabled() {
        return true; // Customize if you want to manage account enabled/disabled
    }
}
