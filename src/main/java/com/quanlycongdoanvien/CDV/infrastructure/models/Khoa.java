package com.quanlycongdoanvien.CDV.infrastructure.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.AttributeOverride;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Getter
@Setter
@Entity
@DynamicUpdate
@Table(name = "KHOA")
@AttributeOverride(name = "id", column = @Column(name = "ID_Khoa", insertable = false, updatable = false))
@GenericGenerator(
        name = "SEQ_GEN",
        strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
        parameters = {@Parameter(name = "sequence_name", value = "SEQ_KHOA")})
public class Khoa extends BaseEntity {
    @JsonIgnore
    @OneToMany(mappedBy = "khoa", fetch = FetchType.LAZY)
    private List<CongDoanVien> danhSachCDV;

    @ManyToOne
    @JoinColumn(name = "ID_Vien")
    private Vien vien;

    @Column(name = "Ten_khoa")
    private String tenKhoa;

    @Column(name = "Ngan_hang")
    private String nganHang;

    @Column(name = "Tai_khoan")
    private String taiKhoan;

    @JsonIgnore
    @OneToMany(mappedBy = "khoa", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<PhiThuKhoa> phiThuKhoaList;
}
