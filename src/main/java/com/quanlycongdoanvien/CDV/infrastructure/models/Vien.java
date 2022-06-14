package com.quanlycongdoanvien.CDV.infrastructure.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
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
@Table(name = "VIEN")
@AttributeOverride(name = "id", column = @Column(name = "ID_Vien", insertable = false, updatable = false))
@GenericGenerator(
        name = "SEQ_GEN",
        strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
        parameters = {@Parameter(name = "sequence_name", value = "SEQ_VIEN")})
@ToString(exclude = {"truong", "danhSachKhoa"})
public class Vien extends BaseEntity {
    @JsonManagedReference
    @OneToMany(mappedBy = "vien", fetch = FetchType.LAZY)
    private List<Khoa> danhSachKhoa;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "ID_Truong")
    private Truong truong;

    @Column(name = "Ten_vien")
    private String tenVien;

    @Column(name = "Ngan_hang")
    private String nganHang;

    @Column(name = "Tai_khoan")
    private String taiKhoan;

    @JsonManagedReference
    @OneToMany(mappedBy = "vien", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<PhiThuVien> phiThuVienList;
}
