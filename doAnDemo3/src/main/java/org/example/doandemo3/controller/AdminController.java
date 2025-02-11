package org.example.doandemo3.controller;

import org.example.doandemo3.model.NguoiDung;
import org.example.doandemo3.repository.NguoiDungRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
//@PreAuthorize("hasRole('ROLE_QUAN_TRI_VIEN')")
public class AdminController {

    @Autowired
    private NguoiDungRepository nguoiDungRepository;

    @GetMapping("/users")
    public ResponseEntity<List<NguoiDung>> getAllUsers() {
        return ResponseEntity.ok(nguoiDungRepository.findAll());
    }
}
