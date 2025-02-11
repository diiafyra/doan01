package org.example.doandemo3.dto;

import lombok.Data;

@Data
public class RegisterRequest {
    private String username;
    private String tenNguoiDung;
    private String matKhau;
    private String email;
}
