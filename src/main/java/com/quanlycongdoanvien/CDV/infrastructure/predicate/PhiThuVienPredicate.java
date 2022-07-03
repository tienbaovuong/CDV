package com.quanlycongdoanvien.CDV.infrastructure.predicate;

import com.quanlycongdoanvien.CDV.infrastructure.models.QPhiThuVien;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;

public class PhiThuVienPredicate {
    static public Predicate createPredicate(Long idVien, String year, String month) {
        QPhiThuVien qPhiThuVien = QPhiThuVien.phiThuVien;
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        booleanBuilder.and(qPhiThuVien.exist.eq(true));
        if (idVien != null) {
            booleanBuilder.and(qPhiThuVien.id.eq(idVien));
        }
        if (!year.equalsIgnoreCase("")) {
            booleanBuilder.and(qPhiThuVien.thoiDiem.year().eq(Integer.valueOf(year)));
        }
        if (!month.equalsIgnoreCase("")) {
            booleanBuilder.and(qPhiThuVien.thoiDiem.month().eq(Integer.valueOf(month)));
        }
        return booleanBuilder.getValue();

    }

    static public Predicate createPredicateMonth(String year, String month) {
        QPhiThuVien qPhiThuVien = QPhiThuVien.phiThuVien;
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        booleanBuilder.and(qPhiThuVien.exist.eq(true));
        if (!year.equalsIgnoreCase("")) {
            booleanBuilder.and(qPhiThuVien.thoiDiem.year().eq(Integer.valueOf(year)));
        }
        if (!month.equalsIgnoreCase("")) {
            booleanBuilder.and(qPhiThuVien.thoiDiem.month().eq(Integer.valueOf(month)));
        }
        return booleanBuilder.getValue();
    }
}
