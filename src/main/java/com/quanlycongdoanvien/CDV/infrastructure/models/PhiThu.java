package com.quanlycongdoanvien.CDV.infrastructure.models;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.MappedSuperclass;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@MappedSuperclass
public abstract class PhiThu extends BaseEntity{
    @Column(name = "Time")
    protected Date thoiDiem;

    @Column(name = "Da_nop")
    protected boolean daNop;

    @Column(name = "Thoi_gian_nop")
    protected LocalDateTime thoiGianNop;
}
