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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "CONG_DOAN_VIEN")
@AttributeOverride(name = "id", column = @Column(name = "ID_CDV", insertable = false, updatable = false))
@GenericGenerator(
        name = "SEQ_GEN",
        strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
        parameters = {@Parameter(name = "sequence_name", value = "SEQ_CDV")})
public class CongDoanVien extends BaseEntity{

    @Column(name = "So_hieu")
    private String soHieu;

    @Column(name = "Ho")
    private String ho;

    @Column(name = "Ten")
    private String ten;

    @Column(name = "Gioi_Tinh")
    private String gioiTinh;

    @Column(name = "Ngay_sinh")
    private Date ngaySinh;

    @Column(name = "Huong_luong_tu")
    private Date huongLuongTu;

    @Column(name = "SDT")
    private String sdt;

    @Column(name = "Email")
    private String email;

    @Column(name = "CCCD")
    private String cccd;

    @ManyToOne
    @JoinColumn(name = "ID_Khoa")
    private Khoa khoa;

    @OneToMany(mappedBy = "congDoanVien")
    private List<PhiThuCDV> phiThuCDVList;
}
