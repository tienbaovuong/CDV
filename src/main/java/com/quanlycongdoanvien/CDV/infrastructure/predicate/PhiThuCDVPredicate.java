package com.quanlycongdoanvien.CDV.infrastructure.predicate;

import com.quanlycongdoanvien.CDV.infrastructure.models.QPhiThuCDV;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;

public class PhiThuCDVPredicate {
    static public Predicate createPredicate(Long idCDV, String year) {
        QPhiThuCDV qPhiThuCDV = QPhiThuCDV.phiThuCDV;
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        booleanBuilder.and(qPhiThuCDV.exist.eq(true));
        if (idCDV != null) {
            booleanBuilder.and(qPhiThuCDV.congDoanVien.id.eq(idCDV));
        }
        if (!year.equalsIgnoreCase("")) {
            booleanBuilder.and(qPhiThuCDV.thoiDiem.year().eq(Integer.valueOf(year)));
        }
        return booleanBuilder.getValue();
    }

    static public Predicate createPredicateMonth(Long idKhoa, String year, String month) {
        QPhiThuCDV qPhiThuCDV = QPhiThuCDV.phiThuCDV;
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        booleanBuilder.and(qPhiThuCDV.exist.eq(true));
        if (idKhoa != null) {
            booleanBuilder.and(qPhiThuCDV.congDoanVien.khoa.id.eq(idKhoa));
        }
        if (!year.equalsIgnoreCase("")) {
            booleanBuilder.and(qPhiThuCDV.thoiDiem.year().eq(Integer.valueOf(year)));
        }
        if (!month.equalsIgnoreCase("")) {
            booleanBuilder.and(qPhiThuCDV.thoiDiem.month().eq(Integer.valueOf(month)));
        }
        return booleanBuilder.getValue();
    }
}
