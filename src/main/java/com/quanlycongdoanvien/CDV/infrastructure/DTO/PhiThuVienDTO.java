package com.quanlycongdoanvien.CDV.infrastructure.DTO;

import com.quanlycongdoanvien.CDV.infrastructure.models.PhiThuVien;

public class PhiThuVienDTO {
    public PhiThuVien phiThuVien;
    public String name;
    public Long soCDV;

    public PhiThuVienDTO(PhiThuVien phiThuVien) {
        this.phiThuVien = phiThuVien;
        this.name = phiThuVien.getVien().getTenVien();
        this.soCDV = 0L;
    }
}
