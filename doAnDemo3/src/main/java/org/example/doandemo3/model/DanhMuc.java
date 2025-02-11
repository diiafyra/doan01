package org.example.doandemo3.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "DANHMUC")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DanhMuc {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idDanhMuc;

    @Column(nullable = false, length = 100)
    private String tenDanhMuc;

    @Column(columnDefinition = "TEXT")
    private String moTa;
}
