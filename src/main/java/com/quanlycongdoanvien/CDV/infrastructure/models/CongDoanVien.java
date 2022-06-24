package com.quanlycongdoanvien.CDV.infrastructure.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.Parameter;

import javax.persistence.AttributeOverride;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
@DynamicUpdate
@Table(name = "CONG_DOAN_VIEN")
@AttributeOverride(name = "id", column = @Column(name = "ID_CDV", insertable = false, updatable = false))
@GenericGenerator(
        name = "SEQ_GEN",
        strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
        parameters = {@Parameter(name = "sequence_name", value = "SEQ_CDV")})
public class CongDoanVien extends BaseEntity {

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

    @JsonManagedReference
    @ManyToOne
    @JoinColumn(name = "ID_Khoa")
    private Khoa khoa;

    @JsonBackReference
    @OneToMany(mappedBy = "congDoanVien", cascade = CascadeType.ALL)
    private List<PhiThuCDV> phiThuCDVList;

    @JsonManagedReference
    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "congDoanVien", cascade = CascadeType.ALL)
    private List<BacLuong> bacLuongList;

    @JsonManagedReference
    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "congDoanVien", cascade = CascadeType.ALL)
    private List<ThamNien> thamNienList;

    @JsonManagedReference
    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "congDoanVien", cascade = CascadeType.ALL)
    private List<HocHam> hocHamList;

    @JsonManagedReference
    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "congDoanVien", cascade = CascadeType.ALL)
    private List<HocVi> hocViList;

    @JsonManagedReference
    @LazyCollection(LazyCollectionOption.FALSE)
    @OneToMany(mappedBy = "congDoanVien", cascade = CascadeType.ALL)
    private List<ChucVu> chucVuList;

    @JsonBackReference
    @OneToOne(mappedBy = "congDoanVien", cascade = CascadeType.ALL)
    private TaiKhoan taiKhoan;
}
