package com.quanlycongdoanvien.CDV.infrastructure.predicate;

import com.quanlycongdoanvien.CDV.infrastructure.models.QTaiKhoan;
import com.quanlycongdoanvien.CDV.infrastructure.models.TaiKhoan;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;

public class TaiKhoanPredicate {
    static public Predicate createPredicate(TaiKhoan taiKhoan){
        QTaiKhoan qTaiKhoan = QTaiKhoan.taiKhoan;
        BooleanBuilder booleanBuilder = new BooleanBuilder();

        if(qTaiKhoan.account != null){
            booleanBuilder.and(qTaiKhoan.account.eq(qTaiKhoan.account));
        }

        return booleanBuilder.getValue();
    }
}
