package com.quanlycongdoanvien.CDV.controllers;

import com.quanlycongdoanvien.CDV.configurations.Webconfig;
import com.quanlycongdoanvien.CDV.infrastructure.models.CongDoanVien;
import com.quanlycongdoanvien.CDV.infrastructure.models.PhiThuCDV;
import com.quanlycongdoanvien.CDV.infrastructure.models.TaiKhoan;
import com.quanlycongdoanvien.CDV.infrastructure.services.CongDoanVienService;
import com.quanlycongdoanvien.CDV.infrastructure.services.KhoaService;
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

@RequestMapping("/api/congdoanvien")
public class CongDoanVienApiController {
    @Autowired
    CongDoanVienService congDoanVienService;
    @Autowired
    KhoaService khoaService;

    @GetMapping("")
    public CongDoanVien getCDV(@RequestParam Long id) {
        return congDoanVienService.findCDVById(id);
    }

    @PostMapping("")
    public List<CongDoanVien> getListCDV(@RequestParam int page, @RequestParam int size, @RequestBody CongDoanVien congDoanVien) {
        return congDoanVienService.filterCDVByPage(page, size, congDoanVien);
    }

    @PostMapping("/count")
    public Long countCDV(@RequestBody CongDoanVien congDoanVien) {
        return congDoanVienService.filterCDVCounter(congDoanVien);
    }

    @PostMapping("/addCDV")
    public String addCongDoanVien(@RequestParam String tenKhoa, @RequestBody CongDoanVien congDoanVien) {
        if (congDoanVienService.findTaiKhoan(congDoanVien.getEmail()) != null) {
            return "Account existed";
        }
        if (congDoanVien != null) {
            TaiKhoan taiKhoan = new TaiKhoan();
            taiKhoan.setAccount(congDoanVien.getEmail());
            taiKhoan.setPassword(congDoanVien.getCccd());
            taiKhoan.setCongDoanVien(congDoanVien);
            congDoanVien.setTaiKhoan(taiKhoan);
            congDoanVien.setKhoa(khoaService.findKhoaByName(tenKhoa));
            congDoanVienService.insertOrUpdate((congDoanVien));
            return "Success";
        }
        return "Missing info";
    }

    @PutMapping("/updateCDV")
    public void updateCongDoanVien(@RequestBody CongDoanVien congDoanVien) {
        congDoanVienService.insertOrUpdate(congDoanVien);
    }

    @DeleteMapping("/deleteCDV")
    public void deleteCongDoanVien(@RequestParam Long id) {
        congDoanVienService.delete(id);
    }

    @PostMapping("/login")
    public String login(@RequestParam String account, @RequestParam String password, @RequestParam String type) {
        TaiKhoan taiKhoan = congDoanVienService.findTaiKhoan(account);
        if (taiKhoan != null) {
            if (taiKhoan.getPassword().equals(password)) {
                if (type.equals("Truong")) {
                    if (taiKhoan.isQuanLyTruong()) return "Success";
                } else if (type.equals("Vien")) {
                    if (taiKhoan.isQuanLyVien()) return taiKhoan.getCongDoanVien().getKhoa().getVien().getId().toString();
                } else if (type.equals("Khoa")) {
                    if (taiKhoan.isQuanLyKhoa()) return taiKhoan.getCongDoanVien().getKhoa().getId().toString();
                } else {
                    return "Success";
                }
                return "Wrong type";
            } else {
                return "Wrong account or password";
            }
        } else {
            return "Wrong account or password";
        }
    }
    @GetMapping("/getaccount")
    public TaiKhoan getAccount(@RequestParam String account){
        return congDoanVienService.findTaiKhoan(account);
    }

    @PutMapping("/updateTaiKhoan")
    public void updateTaiKhoan(@RequestBody TaiKhoan taiKhoan) {
        congDoanVienService.update(taiKhoan);
    }

    @GetMapping("/phithu")
    public List<PhiThuCDV> getPhiThu(@RequestParam Long id) {
        return congDoanVienService.findPhiThu(id);
    }

    @PutMapping("/phithu")
    public void updatePhiThu(@RequestBody PhiThuCDV phiThuCDV) {
        congDoanVienService.insertOrUpdate(phiThuCDV);
    }
}
