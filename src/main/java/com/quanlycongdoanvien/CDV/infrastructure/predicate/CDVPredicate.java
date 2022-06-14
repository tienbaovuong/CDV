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
            booleanBuilder.and(qCongDoanVien.soHieu.eq(cdv.getSoHieu()));
        }
        if (cdv.getHo() != null) {
            booleanBuilder.and(qCongDoanVien.ho.eq(cdv.getHo()));
        }
        if (cdv.getTen() != null) {
            booleanBuilder.and(qCongDoanVien.ten.eq(cdv.getTen()));
        }
        if (cdv.getGioiTinh() != null) {
            booleanBuilder.and(qCongDoanVien.gioiTinh.eq(cdv.getGioiTinh()));
        }
        if (cdv.getSdt() != null) {
            booleanBuilder.and(qCongDoanVien.sdt.eq(cdv.getSdt()));
        }
        if (cdv.getEmail() != null) {
            booleanBuilder.and(qCongDoanVien.email.eq(cdv.getEmail()));
        }
        if(cdv.getKhoa() != null) {
            if (cdv.getKhoa().getTenKhoa() != null) {
                booleanBuilder.and(qCongDoanVien.khoa.tenKhoa.eq(cdv.getKhoa().getTenKhoa()));
            }
            if(cdv.getKhoa().getVien() != null) {
                if (cdv.getKhoa().getVien().getTenVien() != null) {
                    booleanBuilder.and(qCongDoanVien.khoa.vien.tenVien.eq(cdv.getKhoa().getVien().getTenVien()));
                }
            }
        }

        return booleanBuilder.getValue();
    }
}
