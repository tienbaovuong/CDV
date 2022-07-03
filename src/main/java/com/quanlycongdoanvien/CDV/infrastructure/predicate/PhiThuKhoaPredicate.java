package com.quanlycongdoanvien.CDV.infrastructure.predicate;

import com.quanlycongdoanvien.CDV.infrastructure.models.QPhiThuKhoa;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;

public class PhiThuKhoaPredicate {
    static public Predicate createPredicate(Long idKhoa, String year, String month) {
        QPhiThuKhoa qPhiThuKhoa = QPhiThuKhoa.phiThuKhoa;
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        booleanBuilder.and(qPhiThuKhoa.exist.eq(true));
        if (idKhoa != null) {
            booleanBuilder.and(qPhiThuKhoa.id.eq(idKhoa));
        }
        if (!year.equalsIgnoreCase("")) {
            booleanBuilder.and(qPhiThuKhoa.thoiDiem.year().eq(Integer.valueOf(year)));
        }
        if (!month.equalsIgnoreCase("")) {
            booleanBuilder.and(qPhiThuKhoa.thoiDiem.month().eq(Integer.valueOf(month)));
        }
        return booleanBuilder.getValue();

    }

    static public Predicate createPredicateMonth(Long idVien, String year, String month) {
        QPhiThuKhoa qPhiThuKhoa = QPhiThuKhoa.phiThuKhoa;
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        booleanBuilder.and(qPhiThuKhoa.exist.eq(true));
        if (idVien != null) {
            booleanBuilder.and(qPhiThuKhoa.khoa.vien.id.eq(idVien));
        }
        if (!year.equalsIgnoreCase("")) {
            booleanBuilder.and(qPhiThuKhoa.thoiDiem.year().eq(Integer.valueOf(year)));
        }
        if (!month.equalsIgnoreCase("")) {
            booleanBuilder.and(qPhiThuKhoa.thoiDiem.month().eq(Integer.valueOf(month)));
        }
        return booleanBuilder.getValue();

    }
}
