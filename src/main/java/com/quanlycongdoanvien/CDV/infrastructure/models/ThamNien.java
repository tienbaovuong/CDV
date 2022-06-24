package com.quanlycongdoanvien.CDV.infrastructure.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
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
@Table(name = "THAM_NIEN")
@AttributeOverride(name = "id", column = @Column(name = "ID_THAM_NIEN", insertable = false, updatable = false))
@GenericGenerator(
        name = "SEQ_GEN",
        strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
        parameters = {@Parameter(name = "sequence_name", value = "SEQ_THAM_NIEN")})
@ToString(exclude = {"congDoanVien"})
public class ThamNien extends BaseEntity {
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "ID_CDV")
    private CongDoanVien congDoanVien;

    @Column(name = "Tham_nien")
    private String thamNien;

    @Column(name = "Time")
    private LocalDateTime time;
}
