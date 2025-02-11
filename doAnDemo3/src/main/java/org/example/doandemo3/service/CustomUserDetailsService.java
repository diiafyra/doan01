package org.example.doandemo3.service;

import org.example.doandemo3.model.NguoiDung;
import org.example.doandemo3.repository.NguoiDungRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.Collections;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final NguoiDungRepository nguoiDungRepository;

    @Autowired
    public CustomUserDetailsService(NguoiDungRepository nguoiDungRepository) {
        this.nguoiDungRepository = nguoiDungRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("🔍 Đang load user: " + username);

        NguoiDung nguoiDung = nguoiDungRepository.findByIdNguoiDung(username)
                .orElseThrow(() -> new UsernameNotFoundException("User không tồn tại!"));

        System.out.println("✅ Tìm thấy user: " + nguoiDung.getIdNguoiDung());

        return new org.springframework.security.core.userdetails.User(
                nguoiDung.getIdNguoiDung(),
                nguoiDung.getMatKhau(),
                Collections.singletonList(new SimpleGrantedAuthority(nguoiDung.getVaiTro().toString()))
                );
    }
}
