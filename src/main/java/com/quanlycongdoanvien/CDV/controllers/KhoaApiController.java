package com.quanlycongdoanvien.CDV.controllers;

import com.quanlycongdoanvien.CDV.configurations.Webconfig;
import com.quanlycongdoanvien.CDV.infrastructure.DTO.KhoaDTO;
import com.quanlycongdoanvien.CDV.infrastructure.DTO.Number;
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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@CrossOrigin(origins = Webconfig.crossOrigin)
@RestController()

@RequestMapping("/api/khoa")
public class KhoaApiController {
    @Autowired
    KhoaService khoaService;
    @Autowired
    VienService vienService;

    @GetMapping("")
    public KhoaDTO getKhoa(@RequestParam Long id) {
        return new KhoaDTO(khoaService.findKhoaById(id), khoaService.countCDVInKhoa(id));
    }

    @GetMapping("/count")
    public Number countCDVInKhoa(@RequestParam Long id) {
        return new Number(khoaService.countCDVInKhoa(id));
    }

    @PostMapping("")
    public List<KhoaDTO> getListKhoa(@RequestParam int page, @RequestParam int size, @RequestBody Khoa khoa) {
        List<Khoa> khoas = khoaService.filterKhoaByPage(page, size, khoa);
        List<KhoaDTO> khoaDTOS = new ArrayList<>();
        for (Khoa khoa1 : khoas) {
            KhoaDTO khoaDTO = new KhoaDTO(khoa1, khoaService.countCDVInKhoa(khoa1.getId()));
            khoaDTOS.add(khoaDTO);
        }
        return khoaDTOS;
    }

    @PostMapping("/count")
    public Number countKhoa(@RequestBody Khoa khoa) {
        return new Number(khoaService.filterKhoaCount(khoa));
    }

    @PostMapping("/addKhoa")
    public void addKhoa(@RequestParam Long idVien, @RequestBody Khoa khoa) {
        if (khoa != null) {
            if (vienService.findVienById(idVien) != null) {
                khoa.setVien(vienService.findVienById(idVien));
                khoaService.insertOrUpdate(khoa);
            }
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
    public void updatePhiThu(@RequestParam Long id, @RequestBody PhiThuKhoa phiThuKhoa) {
        Khoa khoa = khoaService.findKhoaById(id);
        if (khoa != null) {
            phiThuKhoa.setKhoa(khoa);
            khoaService.insertOrUpdate(phiThuKhoa);
        }
    }
}
