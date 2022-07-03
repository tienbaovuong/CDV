package com.quanlycongdoanvien.CDV.infrastructure.repositories;

import com.quanlycongdoanvien.CDV.infrastructure.models.PhiThuKhoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface IPhiThuKhoaRepository extends JpaRepository<PhiThuKhoa, Long>, QuerydslPredicateExecutor<PhiThuKhoa> {
}
