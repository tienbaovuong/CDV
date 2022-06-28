package com.quanlycongdoanvien.CDV.controllers;

import com.quanlycongdoanvien.CDV.configurations.Webconfig;
import com.quanlycongdoanvien.CDV.infrastructure.DTO.Number;
import com.quanlycongdoanvien.CDV.infrastructure.DTO.VienDTO;
import com.quanlycongdoanvien.CDV.infrastructure.models.PhiThuVien;
import com.quanlycongdoanvien.CDV.infrastructure.models.Truong;
import com.quanlycongdoanvien.CDV.infrastructure.models.Vien;
import com.quanlycongdoanvien.CDV.infrastructure.services.TruongService;
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

@RequestMapping("/api/vien")
public class VienApiController {
    @Autowired
    VienService vienService;
    @Autowired
    TruongService truongService;

    @GetMapping("")
    public VienDTO getVien(@RequestParam Long id) {
        return new VienDTO(vienService.findVienById(id), vienService.countCDVInVien(id));
    }

    @PostMapping("")
    public List<VienDTO> getListVien(@RequestParam int page, @RequestParam int size, @RequestBody Vien vien) {
        List<Vien> viens = vienService.filterVienByPage(page, size, vien);
        List<VienDTO> vienDTOS = new ArrayList<>();
        for (Vien vien1 : viens) {
            VienDTO vienDTO = new VienDTO(vien1, vienService.countCDVInVien(vien1.getId()));
            vienDTOS.add(vienDTO);
        }
        return vienDTOS;
    }

    @PostMapping("/count")
    public Number countVien(@RequestBody Vien vien) {
        return new Number(vienService.filterVienCount(vien));
    }

    @PostMapping("/addVien")
    public void addVien(@RequestBody Vien vien) {
        if (vien != null) {
            Truong truong = truongService.findTruong();
            if (truong == null) {
                System.out.println("null");
                return;
            }
            vien.setTruong(truong);
            vienService.insertOrUpdate(vien);
        }
    }

    @PutMapping("/updateVien")
    public void updateVien(@RequestBody Vien vien) {
        vienService.insertOrUpdate(vien);
    }

    @DeleteMapping("/deleteVien")
    public void deleteVien(@RequestParam Long id) {
        vienService.delete(id);
    }

    @GetMapping("/phithu")
    public List<PhiThuVien> getPhiThu(@RequestParam Long id) {
        return vienService.findPhiThu(id);
    }

    @PutMapping("/phithu")
    public void updatePhiThu(@RequestParam Long id, @RequestBody PhiThuVien phiThuVien) {
        Vien vien = vienService.findVienById(id);
        if (vien != null) {
            phiThuVien.setVien(vien);
            vienService.insertOrUpdate(phiThuVien);
        }
    }
}
