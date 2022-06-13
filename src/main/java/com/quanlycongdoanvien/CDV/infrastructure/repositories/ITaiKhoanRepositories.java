package com.quanlycongdoanvien.CDV.infrastructure.repositories;

import com.quanlycongdoanvien.CDV.infrastructure.models.TaiKhoan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface ITaiKhoanRepositories extends JpaRepository<TaiKhoan,Long> {
}
