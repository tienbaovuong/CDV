package com.quanlycongdoanvien.CDV.controllers;

import com.quanlycongdoanvien.CDV.configurations.Webconfig;
import com.quanlycongdoanvien.CDV.infrastructure.DTO.Number;
import com.quanlycongdoanvien.CDV.infrastructure.DTO.Text;
import com.quanlycongdoanvien.CDV.infrastructure.models.BacLuong;
import com.quanlycongdoanvien.CDV.infrastructure.models.ChucVu;
import com.quanlycongdoanvien.CDV.infrastructure.models.CongDoanVien;
import com.quanlycongdoanvien.CDV.infrastructure.models.HocHam;
import com.quanlycongdoanvien.CDV.infrastructure.models.HocVi;
import com.quanlycongdoanvien.CDV.infrastructure.models.PhiThuCDV;
import com.quanlycongdoanvien.CDV.infrastructure.models.TaiKhoan;
import com.quanlycongdoanvien.CDV.infrastructure.models.ThamNien;
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
    public Number countCDV(@RequestBody CongDoanVien congDoanVien) {
        return new Number(congDoanVienService.filterCDVCounter(congDoanVien));
    }

    @PostMapping("/addCDV")
    public Text addCongDoanVien(@RequestParam Long idKhoa, @RequestBody CongDoanVien congDoanVien) {
        if (congDoanVienService.findTaiKhoan(congDoanVien.getEmail()) != null) {
            return new Text("Account existed");
        }
        if (congDoanVien != null) {
            TaiKhoan taiKhoan = new TaiKhoan();
            taiKhoan.setAccount(congDoanVien.getEmail());
            taiKhoan.setPassword(congDoanVien.getCccd());
            taiKhoan.setCongDoanVien(congDoanVien);
            congDoanVien.setTaiKhoan(taiKhoan);
            if (khoaService.findKhoaById(idKhoa) == null) {
                return new Text("Cant find khoa");
            } else {
                congDoanVien.setKhoa(khoaService.findKhoaById(idKhoa));
                for (BacLuong bacLuong : congDoanVien.getBacLuongList()) {
                    bacLuong.setCongDoanVien(congDoanVien);
                }
                for (ChucVu chucVu : congDoanVien.getChucVuList()) {
                    chucVu.setCongDoanVien(congDoanVien);
                }
                for (HocHam hocHam : congDoanVien.getHocHamList()) {
                    hocHam.setCongDoanVien(congDoanVien);
                }
                for (HocVi hocVi : congDoanVien.getHocViList()) {
                    hocVi.setCongDoanVien(congDoanVien);
                }
                for (ThamNien thamNien : congDoanVien.getThamNienList()) {
                    thamNien.setCongDoanVien(congDoanVien);
                }
                congDoanVienService.insertOrUpdate((congDoanVien));
                return new Text("Success");
            }
        }
        return new Text("Missing info");
    }

    @PutMapping("/updateCDV")
    public void updateCongDoanVien(@RequestBody CongDoanVien congDoanVien) {
        for (BacLuong bacLuong : congDoanVien.getBacLuongList()) {
            bacLuong.setCongDoanVien(congDoanVien);
        }
        for (ChucVu chucVu : congDoanVien.getChucVuList()) {
            chucVu.setCongDoanVien(congDoanVien);
        }
        for (HocHam hocHam : congDoanVien.getHocHamList()) {
            hocHam.setCongDoanVien(congDoanVien);
        }
        for (HocVi hocVi : congDoanVien.getHocViList()) {
            hocVi.setCongDoanVien(congDoanVien);
        }
        for (ThamNien thamNien : congDoanVien.getThamNienList()) {
            thamNien.setCongDoanVien(congDoanVien);
        }
        congDoanVienService.insertOrUpdate(congDoanVien);
    }

    @DeleteMapping("/deleteCDV")
    public void deleteCongDoanVien(@RequestParam Long id) {
        congDoanVienService.delete(id);
    }

    @PostMapping("/login")
    public Number login(@RequestParam String account, @RequestParam String password, @RequestParam String type) {
        TaiKhoan taiKhoan = congDoanVienService.findTaiKhoan(account);
        if (taiKhoan != null) {
            if (taiKhoan.getPassword().equals(password)) {
                if (type.equals("Truong")) {
                    if (taiKhoan.isQuanLyTruong()) return new Number(taiKhoan.getCongDoanVien().getId());
                } else if (type.equals("Vien")) {
                    if (taiKhoan.isQuanLyVien())
                        return new Number(taiKhoan.getCongDoanVien().getId());
                } else if (type.equals("Khoa")) {
                    if (taiKhoan.isQuanLyKhoa()) return new Number(taiKhoan.getCongDoanVien().getId());
                } else {
                    return new Number(taiKhoan.getCongDoanVien().getId());
                }
                //wrong type
                return new Number(-1L);
            } else {
                //wrong account or password
                return new Number(0L);
            }
        } else {
            //wrong account or password
            return new Number(0L);
        }
    }

    @GetMapping("/getaccount")
    public TaiKhoan getAccount(@RequestParam Long id) {
        return congDoanVienService.findCDVById(id).getTaiKhoan();
    }

    @PutMapping("/updateTaiKhoan")
    public void updateTaiKhoan(@RequestParam Long id, @RequestBody TaiKhoan taiKhoan) {
        CongDoanVien cdv = congDoanVienService.findCDVById(id);
        if (cdv != null) {
            taiKhoan.setCongDoanVien(cdv);
            congDoanVienService.update(taiKhoan);
        }
    }

    @GetMapping("/phithu")
    public List<PhiThuCDV> getPhiThu(@RequestParam Long id) {
        return congDoanVienService.findPhiThu(id);
    }

    @PutMapping("/phithu")
    public void updatePhiThu(@RequestParam Long id, @RequestBody PhiThuCDV phiThuCDV) {
        CongDoanVien cdv = congDoanVienService.findCDVById(id);
        if (cdv != null) {
            phiThuCDV.setCongDoanVien(cdv);
            congDoanVienService.insertOrUpdate(phiThuCDV);
        }
    }
}
