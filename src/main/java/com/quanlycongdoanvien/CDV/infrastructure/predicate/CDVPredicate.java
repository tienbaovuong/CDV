package com.quanlycongdoanvien.CDV.infrastructure.predicate;

import com.quanlycongdoanvien.CDV.infrastructure.models.CongDoanVien;
import com.quanlycongdoanvien.CDV.infrastructure.models.QCongDoanVien;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;

public class CDVPredicate {
    static public Predicate createPredicate(CongDoanVien cdv) {
        QCongDoanVien qCongDoanVien = QCongDoanVien.congDoanVien;
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        booleanBuilder.and(qCongDoanVien.exist.eq(true));
        if (qCongDoanVien.soHieu != null) {
            booleanBuilder.and(qCongDoanVien.soHieu.eq(cdv.getSoHieu()));
        }
        if (qCongDoanVien.ho != null) {
            booleanBuilder.and(qCongDoanVien.ho.eq(cdv.getHo()));
        }
        if (qCongDoanVien.ten != null) {
            booleanBuilder.and(qCongDoanVien.ten.eq(cdv.getTen()));
        }
        if (qCongDoanVien.gioiTinh != null) {
            booleanBuilder.and(qCongDoanVien.gioiTinh.eq(cdv.getGioiTinh()));
        }
        if (qCongDoanVien.sdt != null) {
            booleanBuilder.and(qCongDoanVien.sdt.eq(cdv.getSdt()));
        }
        if (qCongDoanVien.email != null) {
            booleanBuilder.and(qCongDoanVien.email.eq(cdv.getEmail()));
        }
        if (qCongDoanVien.khoa.tenKhoa != null) {
            booleanBuilder.and(qCongDoanVien.khoa.tenKhoa.eq(cdv.getKhoa().getTenKhoa()));
        }
        if (qCongDoanVien.khoa.vien.tenVien != null) {
            booleanBuilder.and(qCongDoanVien.khoa.vien.tenVien.eq(cdv.getKhoa().getVien().getTenVien()));
        }
        return booleanBuilder.getValue();
    }
}
