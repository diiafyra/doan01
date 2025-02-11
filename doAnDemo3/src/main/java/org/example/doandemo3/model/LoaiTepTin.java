package org.example.doandemo3.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "LOAITEPTIN")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LoaiTepTin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idLoaiTepTin;

    @Column(nullable = false, unique = true, length = 50)
    private String tenLoaiTepTin;
}
