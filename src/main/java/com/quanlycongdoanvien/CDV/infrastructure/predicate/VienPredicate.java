package com.quanlycongdoanvien.CDV.infrastructure.predicate;

import com.quanlycongdoanvien.CDV.infrastructure.models.QVien;
import com.quanlycongdoanvien.CDV.infrastructure.models.Vien;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;

public class VienPredicate {
    static public Predicate createPredicate(Vien vien) {
        QVien qVien = QVien.vien;
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        booleanBuilder.and(qVien.exist.eq(true));
        if (vien.getTenVien() != null) {
            booleanBuilder.and(qVien.tenVien.eq(vien.getTenVien()));
        }
        return booleanBuilder.getValue();
    }
}
