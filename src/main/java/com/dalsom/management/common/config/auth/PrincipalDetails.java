package com.dalsom.management.common.config.auth;

import com.dalsom.management.admin.Admin;
import com.dalsom.management.admin.AdminRole;
import com.dalsom.management.admin.AdminStatus;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

public class PrincipalDetails implements UserDetails {

    private Admin admin;

    public PrincipalDetails(Admin admin) {
        this.admin = admin;
    }

    public AdminRole getRole() {
        return admin.getRole();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(() -> admin.getRole().name());

        return authorities;
    }

    @Override
    public String getPassword() {
        return admin.getPassword();
    }

    @Override
    public String getUsername() {
        return admin.getName();
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
        return admin.getStatus() == AdminStatus.APPROVED;
    }
}
