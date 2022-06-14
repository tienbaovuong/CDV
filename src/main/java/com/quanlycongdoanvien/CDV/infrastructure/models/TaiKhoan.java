package com.quanlycongdoanvien.CDV.infrastructure.models;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "TAI_KHOAN")
@AttributeOverride(name = "id", column = @Column(name = "ID_TAI_KHOAN", insertable = false, updatable = false))
@GenericGenerator(
        name = "SEQ_GEN",
        strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
        parameters = {@Parameter(name = "sequence_name", value = "SEQ_PHI_THU_CDV")})
public class TaiKhoan extends BaseEntity{
    @OneToOne
    @JoinColumn(name = "ID_CDV")
    private CongDoanVien congDoanVien;

    @Column(name = "Account")
    private String account;

    @Column(name = "Password")
    private String password;

    @Column(name = "La_quan_ly_khoa")
    private boolean isQuanLyKhoa;

    @Column(name = "La_quan_ly_vien")
    private boolean isQuanLyVien;

    @Column(name = "La_quan_ly_truong")
    private boolean isQuanLyTruong;
}
