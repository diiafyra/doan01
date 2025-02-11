package org.example.doandemo3.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "LOG")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Log {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idLog;

    @ManyToOne
    @JoinColumn(name = "id_nguoidung")
    private NguoiDung nguoiDung;

    @Column(nullable = false, length = 255)
    private String hanhDong;

    @Column(nullable = false, length = 45)
    private String diaChiIp;

    @Column(nullable = false, updatable = false)
    private LocalDateTime thoiGian = LocalDateTime.now();
}
