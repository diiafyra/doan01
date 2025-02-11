package org.example.doandemo3.service;

import lombok.RequiredArgsConstructor;
import org.example.doandemo3.dto.LoginRequest;
import org.example.doandemo3.dto.LoginResponse;
import org.example.doandemo3.dto.RegisterRequest;
import org.example.doandemo3.dto.RegisterResponse;
import org.example.doandemo3.model.NguoiDung;
import org.example.doandemo3.model.NguoiDung.TrangThaiTaiKhoan;
import org.example.doandemo3.repository.NguoiDungRepository;
import org.example.doandemo3.security.JwtTokenProvider;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final NguoiDungRepository nguoiDungRepository;
    private final PasswordEncoder passwordEncoder;
    private final EmailService emailService;
    private final JwtTokenProvider jwtTokenProvider;
    private final AuthenticationManager authenticationManager;


    @Transactional
    public RegisterResponse registerUser(RegisterRequest request) {
        if (nguoiDungRepository.findByEmail(request.getEmail()).isPresent()) {
            return new RegisterResponse("Email đã tồn tại!");
        }
        if (nguoiDungRepository.findByIdNguoiDung(request.getUsername()).isPresent()) {
            return new RegisterResponse("Tên người dùng đã tồn tại!");
        }

        String verificationToken = UUID.randomUUID().toString();
        NguoiDung nguoiDung = NguoiDung.builder()
                .idNguoiDung(request.getUsername())
                .tenNguoiDung(request.getTenNguoiDung())
                .matKhau(passwordEncoder.encode(request.getMatKhau()))
                .email(request.getEmail())
                .vaiTro(NguoiDung.VaiTro.NGUOI_DUNG)
                .trangThaiTaiKhoan(TrangThaiTaiKhoan.CHUA_XAC_THUC)
                .verificationToken(verificationToken)
                .build();

        nguoiDungRepository.save(nguoiDung);

        emailService.sendVerificationEmail(request.getEmail(), verificationToken);

        return new RegisterResponse("Đăng ký thành công! Vui lòng kiểm tra email để xác thực tài khoản.");
    }

    @Transactional
    public RegisterResponse verifyAccount(String token) {
        NguoiDung nguoiDung = nguoiDungRepository.findAll()
                .stream()
                .filter(u -> token.equals(u.getVerificationToken()))
                .findFirst()
                .orElse(null);

        if (nguoiDung == null) {
            return new RegisterResponse("Token không hợp lệ hoặc tài khoản đã xác thực.");
        }

        nguoiDung.setTrangThaiTaiKhoan(TrangThaiTaiKhoan.DA_XAC_THUC);
        nguoiDung.setVerificationToken(null);
        nguoiDungRepository.save(nguoiDung);

        return new RegisterResponse("Xác thực tài khoản thành công!");
    }

    public LoginResponse login(LoginRequest request) {
        Optional<NguoiDung> userOptional = nguoiDungRepository.findByEmailOrIdNguoiDung(request.getUsernameOrEmail(), request.getUsernameOrEmail());

        if (userOptional.isEmpty()) {
            throw new RuntimeException("Tài khoản không tồn tại!");
        }

        NguoiDung user = userOptional.get();

        if (user.getTrangThaiTaiKhoan() == TrangThaiTaiKhoan.CHUA_XAC_THUC) {
            throw new RuntimeException("Tài khoản chưa xác thực! Vui lòng kiểm tra email để xác nhận.");
        }
        if (user.getTrangThaiTaiKhoan() == TrangThaiTaiKhoan.KHOA_TAI_KHOAN) {
            throw new RuntimeException("Tài khoản bị khóa. Vui lòng liên hệ quản trị viên.");
        }

        if (!passwordEncoder.matches(request.getPassword(), user.getMatKhau())) {
            throw new RuntimeException("Sai mật khẩu! Vui lòng thử lại.");
        }

        System.out.println("Raw Password: " + request.getPassword());
        System.out.println("Encoded Password from DB: " + user.getMatKhau());
        System.out.println("Match result: " + passwordEncoder.matches(request.getPassword(), user.getMatKhau()));


        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(user.getIdNguoiDung(), request.getPassword()));

            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = jwtTokenProvider.generateToken(authentication);

            return new LoginResponse("Đăng nhập thành công!", jwt, jwtTokenProvider.getRoleFromToken(jwt));

        } catch (Exception e) {
            throw new RuntimeException("Xác thực thất bại! Vui lòng thử lại.");
        }
    }

}
