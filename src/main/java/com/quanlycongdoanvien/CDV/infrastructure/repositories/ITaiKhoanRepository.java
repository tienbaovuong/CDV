package com.quanlycongdoanvien.CDV.infrastructure.repositories;

import com.quanlycongdoanvien.CDV.infrastructure.models.TaiKhoan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ITaiKhoanRepository extends JpaRepository<TaiKhoan, Long> {
}
