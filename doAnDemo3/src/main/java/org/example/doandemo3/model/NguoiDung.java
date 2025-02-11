package org.example.doandemo3.model;

import jakarta.persistence.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "NGUOIDUNG")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NguoiDung implements UserDetails {
    @Id
    private String idNguoiDung;

    @Column(nullable = false, length = 50)
    private String tenNguoiDung;

    @Column(nullable = false)
    private String matKhau;

    @Column(nullable = false, unique = true, length = 100)
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private VaiTro vaiTro = VaiTro.NGUOI_DUNG; // Mặc định là NGUOI_DUNG

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TrangThaiTaiKhoan trangThaiTaiKhoan = TrangThaiTaiKhoan.CHUA_XAC_THUC;

    @Column
    private String verificationToken;

    @Column(nullable = false, updatable = false)
    private LocalDateTime ngayTao;

    @PrePersist
    protected void onCreate() {
        ngayTao = LocalDateTime.now();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getPassword() {
        return "";
    }

    @Override
    public String getUsername() {
        return "";
    }

    public enum VaiTro {
        QUAN_TRI_VIEN, BIEN_TAP_VIEN, NGUOI_DUNG
    }

    public enum TrangThaiTaiKhoan {
        CHUA_XAC_THUC, DA_XAC_THUC, KHOA_TAI_KHOAN
    }
    public String getTokenFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }

}
