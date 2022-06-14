package com.quanlycongdoanvien.CDV.infrastructure.models;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "PHI_THU_CDV")
@AttributeOverride(name = "id", column = @Column(name = "ID_Phi_thu_cdv", insertable = false, updatable = false))
@GenericGenerator(
        name = "SEQ_GEN",
        strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
        parameters = {@Parameter(name = "sequence_name", value = "SEQ_PHI_THU_CDV")})
public class PhiThuCDV  extends PhiThu{
    @ManyToOne
    @JoinColumn(name = "ID_CDV")
    private CongDoanVien congDoanVien;

    @Column(name = "Luong_nop")
    private Long luongNop;

    @Column(name = "Bac_luong")
    private float bacLuong;

    @Column(name = "He_so_chuc_vu")
    private float heSoChucVu;
}
