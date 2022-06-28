package com.quanlycongdoanvien.CDV.infrastructure.DTO;

import com.quanlycongdoanvien.CDV.infrastructure.models.Vien;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VienDTO {
    Vien vien;
    Long soCDV;

    public VienDTO(Vien vien, Long num) {
        this.soCDV = num;
        this.vien = vien;
    }
}
