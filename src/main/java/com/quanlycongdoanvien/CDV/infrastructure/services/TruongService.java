package com.quanlycongdoanvien.CDV.infrastructure.services;

import com.quanlycongdoanvien.CDV.infrastructure.models.PhiThuTruong;
import com.quanlycongdoanvien.CDV.infrastructure.models.Truong;
import com.quanlycongdoanvien.CDV.infrastructure.predicate.PhiThuTruongPredicate;
import com.quanlycongdoanvien.CDV.infrastructure.repositories.IPhiThuTruongRepository;
import com.quanlycongdoanvien.CDV.infrastructure.repositories.ITruongRepository;
import com.querydsl.core.types.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TruongService {
    @Autowired
    ITruongRepository iTruongRepository;
    @Autowired
    IPhiThuTruongRepository iPhiThuTruongRepository;

    public Truong findTruong() {
        return iTruongRepository.findById(1L).orElse(null);
    }

    public Truong insertOrUpdate(Truong truong) {
        return iTruongRepository.save(truong);
    }

    public List<PhiThuTruong> findPhiThu(Long i) {
        if (iTruongRepository.findById(i).orElse(null) == null) return null;
        return iTruongRepository.findById(i).orElse(null).getPhiThuTruongList();
    }

    public List<PhiThuTruong> findPhiThuByMonth(String year, String month) {
        Predicate predicate = PhiThuTruongPredicate.createPredicate(year, month);
        return (List<PhiThuTruong>) iPhiThuTruongRepository.findAll(predicate);
    }

    public PhiThuTruong insertOrUpdate(PhiThuTruong phiThuTruong) {
        return iPhiThuTruongRepository.save(phiThuTruong);
    }
}
