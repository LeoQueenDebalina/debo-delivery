package com.ecommerce.app.debodelivery.service;

import com.ecommerce.app.debodelivery.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class CustomUserDetailsService implements UserDetails {
    private String mobileNumber;
    private String password;
    private List<GrantedAuthority> authorities;

    public CustomUserDetailsService(User user) {
        this.mobileNumber = user.getMobileNumber();
        this.password = user.getPassword();
        this.authorities = Arrays.asList(new SimpleGrantedAuthority(user.getType().toString()));
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.mobileNumber;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
