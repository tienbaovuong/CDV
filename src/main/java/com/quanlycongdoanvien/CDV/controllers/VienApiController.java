package com.quanlycongdoanvien.CDV.controllers;

import com.quanlycongdoanvien.CDV.configurations.Webconfig;
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

import java.util.List;

@CrossOrigin(origins = Webconfig.crossOrigin)
@RestController()

@RequestMapping("/api/vien")
public class VienApiController {
    @Autowired
    VienService vienService;
    TruongService truongService;

    @GetMapping("")
    public Vien getVien(@RequestParam Long id) {
        return vienService.findVienById(id);
    }

    @PostMapping("")
    public List<Vien> getListVien(@RequestParam int page, @RequestParam int size, @RequestBody Vien vien) {
        return vienService.filterVienByPage(page, size, vien);
    }

    @PostMapping("/count")
    public Long countVien(@RequestBody Vien vien) {
        return vienService.filterVienCount(vien);
    }

    @PostMapping("/addVien")
    public void addVien(@RequestBody Vien vien) {
        if (vien != null) {
            Truong truong = truongService.findTruong();
            if(truong == null) System.out.println("null");
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
    public void updatePhiThu(@RequestBody PhiThuVien phiThuVien) {
        vienService.insertOrUpdate(phiThuVien);
    }
}
