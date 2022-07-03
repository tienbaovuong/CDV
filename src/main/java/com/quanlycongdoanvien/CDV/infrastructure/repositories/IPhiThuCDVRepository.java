package com.quanlycongdoanvien.CDV.infrastructure.repositories;

import com.quanlycongdoanvien.CDV.infrastructure.models.PhiThuCDV;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface IPhiThuCDVRepository extends JpaRepository<PhiThuCDV, Long>, QuerydslPredicateExecutor<PhiThuCDV> {
}
