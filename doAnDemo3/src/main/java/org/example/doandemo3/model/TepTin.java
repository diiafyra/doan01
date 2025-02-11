package org.example.doandemo3.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "TEPTIN")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TepTin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idTepTin;

    @Column(nullable = false, length = 255)
    private String tenTepTin;

    @Column(nullable = false, length = 255)
    private String duongDan;

    @ManyToOne
    @JoinColumn(name = "id_loaiteptin")
    private LoaiTepTin loaiTepTin;

    @Column(nullable = false, updatable = false)
    private LocalDateTime ngayTaiLen = LocalDateTime.now();

    @ManyToOne
    @JoinColumn(name = "nguoi_tai_len")
    private NguoiDung nguoiTaiLen;
}
