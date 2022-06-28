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

        if (cdv.getSoHieu() != null) {
            booleanBuilder.and(qCongDoanVien.soHieu.startsWithIgnoreCase(cdv.getSoHieu()));
        }
        if (cdv.getHo() != null) {
            booleanBuilder.and(qCongDoanVien.ho.startsWithIgnoreCase(cdv.getHo()));
        }
        if (cdv.getTen() != null) {
            booleanBuilder.and(qCongDoanVien.ten.startsWithIgnoreCase(cdv.getTen()));
        }
        if (cdv.getGioiTinh() != null) {
            booleanBuilder.and(qCongDoanVien.gioiTinh.eq(cdv.getGioiTinh()));
        }
        if (cdv.getSdt() != null) {
            booleanBuilder.and(qCongDoanVien.sdt.startsWithIgnoreCase(cdv.getSdt()));
        }
        if (cdv.getEmail() != null) {
            booleanBuilder.and(qCongDoanVien.email.startsWithIgnoreCase(cdv.getEmail()));
        }
        if (cdv.getKhoa() != null) {
            if (cdv.getKhoa().getTenKhoa() != null) {
                booleanBuilder.and(qCongDoanVien.khoa.tenKhoa.startsWithIgnoreCase(cdv.getKhoa().getTenKhoa()));
            }
            if (cdv.getKhoa().getVien() != null) {
                if (cdv.getKhoa().getVien().getTenVien() != null) {
                    booleanBuilder.and(qCongDoanVien.khoa.vien.tenVien.startsWithIgnoreCase(cdv.getKhoa().getVien().getTenVien()));
                }
            }
        }

        return booleanBuilder.getValue();
    }
}
