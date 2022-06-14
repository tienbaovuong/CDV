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
@Table(name = "PHI_THU_VIEN")
@AttributeOverride(name = "id", column = @Column(name = "ID_Phi_thu_vien", insertable = false, updatable = false))
@GenericGenerator(
        name = "SEQ_GEN",
        strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
        parameters = {@Parameter(name = "sequence_name", value = "SEQ_PHI_THU_VIEN")})
public class PhiThuVien extends PhiThu{
    @ManyToOne
    @JoinColumn(name = "ID_Vien")
    private Vien vien;

    @Column(name = "Tong_thu")
    private Long tongThu;

    @Column(name = "Nop_truong")
    private Long nopTruong;

    @Column(name = "Giu_lai")
    private Long giuLai;
}
