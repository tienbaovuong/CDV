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
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "HOC_VI")
@AttributeOverride(name = "id", column = @Column(name = "ID_HOC_VI", insertable = false, updatable = false))
@GenericGenerator(
        name = "SEQ_GEN",
        strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
        parameters = {@Parameter(name = "sequence_name", value = "SEQ_HOC_VI")})
public class HocVi extends BaseEntity{
    @ManyToOne
    @JoinColumn(name = "ID_CDV")
    private CongDoanVien congDoanVien;

    @Column(name = "Hoc_vi")
    private String hocVi;

    @Column(name = "Time")
    private LocalDateTime time;
}
