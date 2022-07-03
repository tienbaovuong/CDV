package com.quanlycongdoanvien.CDV.infrastructure.predicate;

import com.quanlycongdoanvien.CDV.infrastructure.models.QPhiThuTruong;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;

public class PhiThuTruongPredicate {
    static public Predicate createPredicate(String year, String month) {
        QPhiThuTruong qPhiThuTruong = QPhiThuTruong.phiThuTruong;
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        booleanBuilder.and(qPhiThuTruong.exist.eq(true));
        if (!year.equalsIgnoreCase("")) {
            booleanBuilder.and(qPhiThuTruong.thoiDiem.year().eq(Integer.valueOf(year)));
        }
        if (!month.equalsIgnoreCase("")) {
            booleanBuilder.and(qPhiThuTruong.thoiDiem.month().eq(Integer.valueOf(month)));
        }
        return booleanBuilder.getValue();
    }
}
