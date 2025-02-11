package org.example.doandemo3.repository;

import org.example.doandemo3.model.NguoiDung;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface NguoiDungRepository extends JpaRepository<NguoiDung, String> {
    Optional<NguoiDung> findByEmail(String email);
    Optional<NguoiDung> findByIdNguoiDung(String id);
    Optional<NguoiDung> findByEmailOrIdNguoiDung(String email, String idNguoiDung);

}
