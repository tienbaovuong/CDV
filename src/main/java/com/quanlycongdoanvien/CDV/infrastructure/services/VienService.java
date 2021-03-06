package com.quanlycongdoanvien.CDV.infrastructure.services;

import com.quanlycongdoanvien.CDV.infrastructure.models.CongDoanVien;
import com.quanlycongdoanvien.CDV.infrastructure.models.Khoa;
import com.quanlycongdoanvien.CDV.infrastructure.models.PhiThuVien;
import com.quanlycongdoanvien.CDV.infrastructure.models.Vien;
import com.quanlycongdoanvien.CDV.infrastructure.predicate.PhiThuVienPredicate;
import com.quanlycongdoanvien.CDV.infrastructure.predicate.VienPredicate;
import com.quanlycongdoanvien.CDV.infrastructure.repositories.IPhiThuVienRepository;
import com.quanlycongdoanvien.CDV.infrastructure.repositories.IVienRepository;
import com.querydsl.core.types.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VienService {
    @Autowired
    IVienRepository iVienRepository;
    @Autowired
    IPhiThuVienRepository iPhiThuVienRepository;

    public Long findNumberOfVien() {
        return iVienRepository.count();
    }

    public Vien findVienById(Long i) {
        return iVienRepository.findById(i).orElse(null);
    }

    public Vien findVienByName(String name) {
        Vien vien = new Vien();
        vien.setTenVien(name);
        Predicate predicate = VienPredicate.createPredicate(vien);
        return iVienRepository.findOne(predicate).orElse(null);
    }

    public Long countCDVInVien(Long i) {
        Vien vien = iVienRepository.findById(i).orElse(null);
        Long count = 0L;
        if (vien != null) {
            for (Khoa khoa : vien.getDanhSachKhoa()) {
                for (CongDoanVien congDoanVien : khoa.getDanhSachCDV()) {
                    count++;
                }
            }
        }
        return count;
    }

    public List<Vien> filterVienByPage(int page, int size, Vien vien) {
        Predicate predicate = VienPredicate.createPredicate(vien);
        return iVienRepository.findAll(predicate, PageRequest.of(page - 1, size, Sort.Direction.ASC, "id")).toList();
    }

    public Long filterVienCount(Vien vien) {
        Predicate predicate = VienPredicate.createPredicate(vien);
        return iVienRepository.count(predicate);
    }

    public Vien insertOrUpdate(Vien vien) {
        return iVienRepository.save(vien);
    }

    public void delete(Long id) {
        Vien vien = iVienRepository.findById(id).orElse(null);
        if (vien != null) {
            vien.setExist(false);
            iVienRepository.save(vien);
        }
    }

    public List<PhiThuVien> findPhiThu(Long i) {
        if (iVienRepository.findById(i).orElse(null) == null) return null;
        return iVienRepository.findById(i).orElse(null).getPhiThuVienList();
    }

    public List<PhiThuVien> findPhiThuByMonth(Long i, String year, String month) {
        Predicate predicate = PhiThuVienPredicate.createPredicate(i, year, month);
        return (List<PhiThuVien>) iPhiThuVienRepository.findAll(predicate);
    }

    public List<PhiThuVien> findPhiThuByTruong(String year, String month) {
        Predicate predicate = PhiThuVienPredicate.createPredicateMonth(year, month);
        return (List<PhiThuVien>) iPhiThuVienRepository.findAll(predicate);
    }

    public PhiThuVien insertOrUpdate(PhiThuVien phiThuVien) {
        return iPhiThuVienRepository.save(phiThuVien);
    }
}
