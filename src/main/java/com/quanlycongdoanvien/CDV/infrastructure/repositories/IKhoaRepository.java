package com.quanlycongdoanvien.CDV.infrastructure.repositories;

import com.quanlycongdoanvien.CDV.infrastructure.models.Khoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface IKhoaRepository extends JpaRepository<Khoa, Long>, QuerydslPredicateExecutor<Khoa> {
}
