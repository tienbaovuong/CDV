package com.quanlycongdoanvien.CDV.infrastructure.DTO;

import com.quanlycongdoanvien.CDV.infrastructure.models.PhiThuCDV;

public class PhiThuCDVDTO {
    public PhiThuCDV phiThuCDV;
    public String name;
    public Long id;

    public PhiThuCDVDTO(PhiThuCDV phiThuCDV) {
        this.phiThuCDV = phiThuCDV;
        this.name = phiThuCDV.getCongDoanVien().getHo() + " " + phiThuCDV.getCongDoanVien().getTen();
        this.id = phiThuCDV.getCongDoanVien().getId();
    }
}
