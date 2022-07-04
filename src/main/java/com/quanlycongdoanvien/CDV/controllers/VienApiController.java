package com.quanlycongdoanvien.CDV.controllers;

import com.quanlycongdoanvien.CDV.configurations.Webconfig;
import com.quanlycongdoanvien.CDV.infrastructure.DTO.Number;
import com.quanlycongdoanvien.CDV.infrastructure.DTO.PhiThuVienDTO;
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
    public Number addVien(@RequestBody Vien vien) {
        if (vien != null) {
            Truong truong = truongService.findTruong();
            if (truong == null) {
                System.out.println("null");
                return new Number(1L);
            }
            vien.setTruong(truong);
            vienService.insertOrUpdate(vien);
        }
        return new Number(1L);
    }

    @PutMapping("/updateVien")
    public Number updateVien(@RequestBody Vien vien) {
        vienService.insertOrUpdate(vien);
        return new Number(1L);
    }

    @DeleteMapping("/deleteVien")
    public Number deleteVien(@RequestParam Long id) {
        vienService.delete(id);
        return new Number(1L);
    }

    @GetMapping("/phithu/month")
    public List<PhiThuVien> getPhiThu(@RequestParam Long idVien, String year, String month) {
        return vienService.findPhiThuByMonth(idVien, year, month);
    }

    @GetMapping("/phithu/truong")
    public List<PhiThuVienDTO> getPhiThuVien(@RequestParam String year, String month) {
        List<PhiThuVien> phiThuViens = vienService.findPhiThuByTruong(year, month);
        List<PhiThuVienDTO> phiThuVienDTOS = new ArrayList<>();
        for (PhiThuVien phiThuVien : phiThuViens) {
            PhiThuVienDTO phiThuVienDTO = new PhiThuVienDTO(phiThuVien);
            phiThuVienDTO.soCDV = vienService.countCDVInVien(phiThuVien.getVien().getId());
            phiThuVienDTOS.add(phiThuVienDTO);
        }
        return phiThuVienDTOS;
    }

    @PutMapping("/phithu")
    public Number updatePhiThu(@RequestParam Long id, @RequestBody PhiThuVien phiThuVien) {
        Vien vien = vienService.findVienById(id);
        if (vien != null) {
            phiThuVien.setVien(vien);
            vienService.insertOrUpdate(phiThuVien);
        }
        return new Number(1L);
    }
}
