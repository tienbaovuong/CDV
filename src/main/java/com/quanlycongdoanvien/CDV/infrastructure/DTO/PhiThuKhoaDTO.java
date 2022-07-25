package com.quanlycongdoanvien.CDV.infrastructure.DTO;

import com.quanlycongdoanvien.CDV.infrastructure.models.PhiThuKhoa;

public class PhiThuKhoaDTO {
    public PhiThuKhoa phiThuKhoa;
    public String name;
    public Long soCDV;
    public Long id;

    public PhiThuKhoaDTO(PhiThuKhoa phiThuKhoa) {
        this.phiThuKhoa = phiThuKhoa;
        this.name = phiThuKhoa.getKhoa().getTenKhoa();
        this.id = phiThuKhoa.getKhoa().getId();
        this.soCDV = 0L;
    }
}
