package com.quanlycongdoanvien.CDV.infrastructure.models;

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
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@DynamicUpdate
@Table(name = "TAI_KHOAN")
@AttributeOverride(name = "id", column = @Column(name = "ID_TAI_KHOAN", insertable = false, updatable = false))
@GenericGenerator(
        name = "SEQ_GEN",
        strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
        parameters = {@Parameter(name = "sequence_name", value = "SEQ_PHI_THU_CDV")})
@ToString(exclude = {"congDoanVien"})
public class TaiKhoan extends BaseEntity {
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ID_CDV")
    private CongDoanVien congDoanVien;

    @Column(name = "Account")
    private String account;

    @Column(name = "Password")
    private String password;

    @Column(name = "La_quan_ly_khoa")
    private boolean isQuanLyKhoa = false;

    @Column(name = "La_quan_ly_vien")
    private boolean isQuanLyVien = false;

    @Column(name = "La_quan_ly_truong")
    private boolean isQuanLyTruong = false;

}
