package com.quanlycongdoanvien.CDV.infrastructure.repositories;

import com.quanlycongdoanvien.CDV.infrastructure.models.Vien;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface IVienRepository extends JpaRepository<Vien, Long>, QuerydslPredicateExecutor<Vien> {
}
