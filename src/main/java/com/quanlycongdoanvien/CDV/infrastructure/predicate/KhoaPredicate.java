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

        if (khoa.getTenKhoa() != null) {
            booleanBuilder.and(qKhoa.tenKhoa.startsWithIgnoreCase(khoa.getTenKhoa()));
        }
        if (khoa.getVien() != null) {
            if (khoa.getVien().getTenVien() != null) {
                booleanBuilder.and(qKhoa.vien.tenVien.startsWithIgnoreCase(khoa.getVien().getTenVien()));
            }
        }
        return booleanBuilder.getValue();
    }
}
