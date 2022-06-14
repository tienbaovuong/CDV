package com.quanlycongdoanvien.CDV.infrastructure.services;

import com.quanlycongdoanvien.CDV.infrastructure.models.PhiThuTruong;
import com.quanlycongdoanvien.CDV.infrastructure.models.Truong;
import com.quanlycongdoanvien.CDV.infrastructure.repositories.IPhiThuTruongRepository;
import com.quanlycongdoanvien.CDV.infrastructure.repositories.ITruongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TruongService {
    @Autowired
    ITruongRepository iTruongRepository;
    IPhiThuTruongRepository iPhiThuTruongRepository;

    public Truong findTruong() {
        return iTruongRepository.findById(Long.valueOf(1)).orElse(null);
    }

    public Truong insertOrUpdate(Truong truong) {
        return iTruongRepository.save(truong);
    }

    public List<PhiThuTruong> findPhiThu(Long i) {
        if(iTruongRepository.findById(i).orElse(null) == null) return null;
        return iTruongRepository.findById(i).orElse(null).getPhiThuTruongList();
    }

    public PhiThuTruong insertOrUpdate(PhiThuTruong phiThuTruong) {
        return iPhiThuTruongRepository.save(phiThuTruong);
    }
}
