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
            booleanBuilder.and(qCongDoanVien.soHieu.eq(qCongDoanVien.soHieu));
        }
        if (qCongDoanVien.ho != null) {
            booleanBuilder.and(qCongDoanVien.ho.eq(qCongDoanVien.ho));
        }
        if (qCongDoanVien.ten != null) {
            booleanBuilder.and(qCongDoanVien.ten.eq(qCongDoanVien.ten));
        }
        if (qCongDoanVien.gioiTinh != null) {
            booleanBuilder.and(qCongDoanVien.gioiTinh.eq(qCongDoanVien.gioiTinh));
        }
        if (qCongDoanVien.sdt != null) {
            booleanBuilder.and(qCongDoanVien.sdt.eq(qCongDoanVien.sdt));
        }
        if (qCongDoanVien.email != null) {
            booleanBuilder.and(qCongDoanVien.email.eq(qCongDoanVien.email));
        }
        if (qCongDoanVien.khoa.tenKhoa != null) {
            booleanBuilder.and(qCongDoanVien.khoa.tenKhoa.eq(qCongDoanVien.khoa.tenKhoa));
        }
        if (qCongDoanVien.khoa.vien.tenVien != null) {
            booleanBuilder.and(qCongDoanVien.khoa.vien.tenVien.eq(qCongDoanVien.khoa.vien.tenVien));
        }
        return booleanBuilder.getValue();
    }
}
