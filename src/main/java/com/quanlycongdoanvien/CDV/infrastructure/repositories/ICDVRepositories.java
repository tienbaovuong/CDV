package com.quanlycongdoanvien.CDV.infrastructure.repositories;

import com.quanlycongdoanvien.CDV.infrastructure.models.CongDoanVien;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface ICDVRepositories extends JpaRepository<CongDoanVien,Long> {
}
