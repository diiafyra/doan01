package org.example.doandemo3.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "CANHBAO")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CanhBao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idCanhBao;

    @Column(nullable = false, length = 100)
    private String loaiTanCong;

    @Column(nullable = false, length = 45)
    private String diaChiNguon;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private MucDo mucDo;

    @Column(nullable = false, updatable = false)
    private LocalDateTime thoiGianPhatHien = LocalDateTime.now();

    public enum MucDo {
        THAP, TRUNG_BINH, CAO, QUAN_TRONG
    }
}
