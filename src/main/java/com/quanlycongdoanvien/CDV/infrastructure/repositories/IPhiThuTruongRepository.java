package com.quanlycongdoanvien.CDV.infrastructure.repositories;

import com.quanlycongdoanvien.CDV.infrastructure.models.PhiThuTruong;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface IPhiThuTruongRepository extends JpaRepository<PhiThuTruong, Long>, QuerydslPredicateExecutor<PhiThuTruong> {
}
