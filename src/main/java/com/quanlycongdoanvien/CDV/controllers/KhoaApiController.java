package com.quanlycongdoanvien.CDV.controllers;

import com.quanlycongdoanvien.CDV.configurations.Webconfig;
import com.quanlycongdoanvien.CDV.infrastructure.models.Khoa;
import com.quanlycongdoanvien.CDV.infrastructure.models.PhiThuKhoa;
import com.quanlycongdoanvien.CDV.infrastructure.services.KhoaService;
import com.quanlycongdoanvien.CDV.infrastructure.services.VienService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = Webconfig.crossOrigin)
@RestController()

@RequestMapping("/api/khoa")
public class KhoaApiController {
    @Autowired
    KhoaService khoaService;
    VienService vienService;

    @GetMapping("")
    public Khoa getKhoa(@RequestParam Long id) {
        return khoaService.findKhoaById(id);
    }

    @PostMapping("")
    public List<Khoa> getListKhoa(@RequestParam int page, @RequestParam int size, @RequestBody Khoa khoa) {
        return khoaService.filterKhoaByPage(page, size, khoa);
    }

    @PostMapping("/count")
    public Long countKhoa(@RequestBody Khoa khoa) {
        return khoaService.filterKhoaCount(khoa);
    }

    @PostMapping("/addKhoa")
    public void addKhoa(@RequestParam String tenVien, @RequestBody Khoa khoa) {
        if (khoa != null) {
            khoa.setVien(vienService.findVienByName(tenVien));
            khoaService.insertOrUpdate(khoa);
        }
    }

    @PutMapping("/updateKhoa")
    public void updateKhoa(@RequestBody Khoa khoa) {
        khoaService.insertOrUpdate(khoa);
    }

    @DeleteMapping("/deleteKhoa")
    public void deleteKhoa(@RequestParam Long id) {
        khoaService.delete(id);
    }

    @GetMapping("/phithu")
    public List<PhiThuKhoa> getPhiThu(@RequestParam Long id) {
        return khoaService.findPhiThu(id);
    }

    @PutMapping("/phithu")
    public void updatePhiThu(@RequestBody PhiThuKhoa phiThuKhoa) {
        khoaService.insertOrUpdate(phiThuKhoa);
    }
}
