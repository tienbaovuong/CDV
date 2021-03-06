package com.quanlycongdoanvien.CDV.infrastructure.services;

import com.quanlycongdoanvien.CDV.infrastructure.models.CongDoanVien;
import com.quanlycongdoanvien.CDV.infrastructure.models.Khoa;
import com.quanlycongdoanvien.CDV.infrastructure.models.PhiThuKhoa;
import com.quanlycongdoanvien.CDV.infrastructure.predicate.KhoaPredicate;
import com.quanlycongdoanvien.CDV.infrastructure.predicate.PhiThuKhoaPredicate;
import com.quanlycongdoanvien.CDV.infrastructure.repositories.IKhoaRepository;
import com.quanlycongdoanvien.CDV.infrastructure.repositories.IPhiThuKhoaRepository;
import com.querydsl.core.types.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KhoaService {
    @Autowired
    IKhoaRepository iKhoaRepository;
    @Autowired
    IPhiThuKhoaRepository iPhiThuKhoaRepository;

    public Long findNumberOfKhoa() {
        return iKhoaRepository.count();
    }

    public Khoa findKhoaById(Long i) {
        return iKhoaRepository.findById(i).orElse(null);
    }

    public Khoa findKhoaByName(String name) {
        Khoa khoa = new Khoa();
        khoa.setTenKhoa(name);
        Predicate predicate = KhoaPredicate.createPredicate(khoa);
        return iKhoaRepository.findOne(predicate).orElse(null);
    }

    public Long countCDVInKhoa(Long i) {
        Khoa khoa = iKhoaRepository.findById(i).orElse(null);
        Long count = 0L;
        if (khoa != null) {
            for (CongDoanVien congDoanVien : khoa.getDanhSachCDV()) {
                count++;
            }
        }
        return count;
    }

    public List<Khoa> filterKhoaByPage(int page, int size, Khoa khoa) {
        Predicate predicate = KhoaPredicate.createPredicate(khoa);
        return iKhoaRepository.findAll(predicate, PageRequest.of(page - 1, size, Sort.Direction.ASC, "id")).toList();
    }

    public Long filterKhoaCount(Khoa khoa) {
        Predicate predicate = KhoaPredicate.createPredicate(khoa);
        return iKhoaRepository.count(predicate);
    }

    public Khoa insertOrUpdate(Khoa khoa) {
        return iKhoaRepository.save(khoa);
    }

    public void delete(Long id) {
        Khoa khoa = iKhoaRepository.findById(id).orElse(null);
        if (khoa != null) {
            khoa.setExist(false);
            iKhoaRepository.save(khoa);
        }
    }

    public List<PhiThuKhoa> findPhiThu(Long i) {
        if (iKhoaRepository.findById(i).orElse(null) == null) return null;
        return iKhoaRepository.findById(i).orElse(null).getPhiThuKhoaList();
    }

    public List<PhiThuKhoa> findPhiThuByMonth(Long i, String year, String month) {
        Predicate predicate = PhiThuKhoaPredicate.createPredicate(i, year, month);
        return (List<PhiThuKhoa>) iPhiThuKhoaRepository.findAll(predicate);
    }

    public List<PhiThuKhoa> findPhiThuByVien(Long i, String year, String month) {
        Predicate predicate = PhiThuKhoaPredicate.createPredicateMonth(i, year, month);
        return (List<PhiThuKhoa>) iPhiThuKhoaRepository.findAll(predicate);
    }

    public PhiThuKhoa insertOrUpdate(PhiThuKhoa phiThuKhoa) {
        return iPhiThuKhoaRepository.save(phiThuKhoa);
    }
}
