package com.quanlycongdoanvien.CDV.infrastructure.predicate;

import com.quanlycongdoanvien.CDV.infrastructure.models.Khoa;
import com.quanlycongdoanvien.CDV.infrastructure.models.QKhoa;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;

public class KhoaPredicate {
    static public Predicate createPredicate(Khoa khoa) {
        QKhoa qKhoa = QKhoa.khoa;
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        booleanBuilder.and(qKhoa.exist.eq(true));
        if (qKhoa.tenKhoa != null) {
            booleanBuilder.and(qKhoa.tenKhoa.eq(qKhoa.tenKhoa));
        }
        if (qKhoa.vien.tenVien != null) {
            booleanBuilder.and(qKhoa.vien.tenVien.eq(qKhoa.vien.tenVien));
        }
        return booleanBuilder.getValue();
    }
}