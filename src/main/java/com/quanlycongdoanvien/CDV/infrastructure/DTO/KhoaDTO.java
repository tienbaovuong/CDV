package com.quanlycongdoanvien.CDV.infrastructure.DTO;

import com.quanlycongdoanvien.CDV.infrastructure.models.Khoa;

public class KhoaDTO {
    Khoa khoa;
    Long soCDV;

    public KhoaDTO(Khoa khoa, Long num) {
        this.khoa = khoa;
        this.soCDV = num;
    }
}
