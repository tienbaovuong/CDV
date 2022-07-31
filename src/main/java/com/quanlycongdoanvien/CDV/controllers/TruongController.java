package com.quanlycongdoanvien.CDV.controllers;

import com.quanlycongdoanvien.CDV.configurations.Webconfig;
import com.quanlycongdoanvien.CDV.infrastructure.models.PhiThuTruong;
import com.quanlycongdoanvien.CDV.infrastructure.models.Truong;
import com.quanlycongdoanvien.CDV.infrastructure.services.TruongService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.quanlycongdoanvien.CDV.infrastructure.DTO.Number;
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
    public Number updateTruong(@RequestBody Truong truong) {
        truongService.insertOrUpdate(truong);
        return new Number(1L);
    }

    @GetMapping("/phithu")
    public List<PhiThuTruong> getPhiThu() {
        return truongService.findPhiThu(1L);
    }

    @GetMapping("/phithu/month")
    public List<PhiThuTruong> getPhiThu(@RequestParam String year, String month) {
        return truongService.findPhiThuByMonth(year, month);
    }

    @PutMapping("/phithu")
    public Number updatePhiThu(@RequestParam Long id, @RequestBody PhiThuTruong phiThuTruong) {
        Truong truong = truongService.findTruong();
        phiThuTruong.setTruong(truong);
        truongService.insertOrUpdate(phiThuTruong);
        return new Number(1L);
    }
    @GetMapping("/phithu/monthlyupdate")
    public void monthlyPhiThu(){
        truongService.createPhiThu();
    }
    @PutMapping("/phithu/monthlyupdate")
    public void monthlyChoosePhiThu(@RequestParam int month){
        truongService.createPhiThuForChosenMonth(month);
    }
}
