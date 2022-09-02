package com.dalsom.management.common.config.auth;

import com.dalsom.management.admin.Admin;
import com.dalsom.management.admin.AdminRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class PrincipalDetailsService implements UserDetailsService {

    private final AdminRepository adminRepository;

    public PrincipalDetailsService(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String loginId) throws UsernameNotFoundException {
        Admin admin = adminRepository.findByLoginId(loginId).orElseThrow(() -> new UsernameNotFoundException("아이디가 없습니다"));
        return new PrincipalDetails(admin);
    }
}
