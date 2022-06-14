package com.quanlycongdoanvien.CDV.controllers;

import com.quanlycongdoanvien.CDV.configurations.Webconfig;
import com.quanlycongdoanvien.CDV.infrastructure.models.PhiThuTruong;
import com.quanlycongdoanvien.CDV.infrastructure.models.Truong;
import com.quanlycongdoanvien.CDV.infrastructure.services.TruongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = Webconfig.crossOrigin)
@RestController()

@RequestMapping("/api/truong")
public class TruongController {
    @Autowired
    TruongService truongService;

    @GetMapping("")
    public Truong getTruong() {
        return truongService.findTruong();
    }

    @PutMapping("")
    public void updateTruong(@RequestBody Truong truong) {
        truongService.insertOrUpdate(truong);
    }

    @GetMapping("/phithu")
    public List<PhiThuTruong> getPhiThu(@RequestParam Long id) {
        return truongService.findPhiThu(id);
    }

    @PutMapping("/phithu")
    public void updatePhiThu(@RequestBody PhiThuTruong phiThuTruong) {
        truongService.insertOrUpdate(phiThuTruong);
    }
}
