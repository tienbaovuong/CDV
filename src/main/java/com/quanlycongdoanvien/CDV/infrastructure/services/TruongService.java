package com.quanlycongdoanvien.CDV.infrastructure.services;

import com.quanlycongdoanvien.CDV.infrastructure.models.CongDoanVien;
import com.quanlycongdoanvien.CDV.infrastructure.models.Khoa;
import com.quanlycongdoanvien.CDV.infrastructure.models.PhiThuCDV;
import com.quanlycongdoanvien.CDV.infrastructure.models.PhiThuKhoa;
import com.quanlycongdoanvien.CDV.infrastructure.models.PhiThuTruong;
import com.quanlycongdoanvien.CDV.infrastructure.models.PhiThuVien;
import com.quanlycongdoanvien.CDV.infrastructure.models.Truong;
import com.quanlycongdoanvien.CDV.infrastructure.models.Vien;
import com.quanlycongdoanvien.CDV.infrastructure.predicate.PhiThuTruongPredicate;
import com.quanlycongdoanvien.CDV.infrastructure.repositories.IPhiThuCDVRepository;
import com.quanlycongdoanvien.CDV.infrastructure.repositories.IPhiThuKhoaRepository;
import com.quanlycongdoanvien.CDV.infrastructure.repositories.IPhiThuTruongRepository;
import com.quanlycongdoanvien.CDV.infrastructure.repositories.IPhiThuVienRepository;
import com.quanlycongdoanvien.CDV.infrastructure.repositories.ITruongRepository;
import com.querydsl.core.types.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class TruongService {
    @Autowired
    ITruongRepository iTruongRepository;
    @Autowired
    IPhiThuTruongRepository iPhiThuTruongRepository;
    @Autowired
    IPhiThuVienRepository iPhiThuVienRepository;
    @Autowired
    IPhiThuKhoaRepository iPhiThuKhoaRepository;
    @Autowired
    IPhiThuCDVRepository iPhiThuCDVRepository;

    public Truong findTruong() {
        return iTruongRepository.findById(1L).orElse(null);
    }

    public Truong insertOrUpdate(Truong truong) {
        return iTruongRepository.save(truong);
    }

    public List<PhiThuTruong> findPhiThu(Long i) {
        if (iTruongRepository.findById(i).orElse(null) == null) return null;
        return iTruongRepository.findById(i).orElse(null).getPhiThuTruongList();
    }

    public List<PhiThuTruong> findPhiThuByMonth(String year, String month) {
        Predicate predicate = PhiThuTruongPredicate.createPredicate(year, month);
        return (List<PhiThuTruong>) iPhiThuTruongRepository.findAll(predicate);
    }

    public PhiThuTruong insertOrUpdate(PhiThuTruong phiThuTruong) {
        return iPhiThuTruongRepository.save(phiThuTruong);
    }

    public void createPhiThu(){
        Date date = new Date();
        Truong truong = findTruong();
        PhiThuTruong phiThuTruong = new PhiThuTruong();
        phiThuTruong.setThoiDiem(date);
        phiThuTruong.setDaNop(false);
        phiThuTruong.setTruong(truong);
        Long tongPhiThuTruong = 0L;

        for(Vien vien: truong.getDanhSachVien()){
            PhiThuVien phiThuVien = new PhiThuVien();
            phiThuVien.setVien(vien);
            phiThuVien.setThoiDiem(date);
            phiThuVien.setDaNop(false);
            Long tongPhiThuVien = 0L;

            for(Khoa khoa: vien.getDanhSachKhoa()){
                PhiThuKhoa phiThuKhoa = new PhiThuKhoa();
                phiThuKhoa.setKhoa(khoa);
                phiThuKhoa.setThoiDiem(date);
                phiThuKhoa.setDaNop(false);
                Long tongPhiThuKhoa = 0L;

                for(CongDoanVien congDoanVien: khoa.getDanhSachCDV()){
                    PhiThuCDV phiThuCDV = new PhiThuCDV();
                    phiThuCDV.setCongDoanVien(congDoanVien);
                    phiThuCDV.setThoiDiem(date);
                    phiThuCDV.setDaNop(false);
                    phiThuCDV.setBacLuong(congDoanVien.getBacLuongList().get(congDoanVien.getBacLuongList().size()-1).getBacLuong());
                    phiThuCDV.setHeSoChucVu(congDoanVien.getChucVuList().get(congDoanVien.getChucVuList().size()-1).getHeSoChucVu());
                    phiThuCDV.setLuongNop((long) (1490000L *(phiThuCDV.getBacLuong()+ phiThuCDV.getHeSoChucVu())/100));
                    iPhiThuCDVRepository.save(phiThuCDV);
                    tongPhiThuKhoa += phiThuCDV.getLuongNop();
                }

                phiThuKhoa.setTongThu(tongPhiThuKhoa);
                phiThuKhoa.setGiuLai(tongPhiThuKhoa*7/20);
                Long tmp = phiThuKhoa.getTongThu() - phiThuKhoa.getGiuLai();
                tongPhiThuVien += tmp;
                phiThuKhoa.setNopVien(tmp/13*3);
                phiThuKhoa.setNopTruong(tmp-phiThuKhoa.getNopVien());
                iPhiThuKhoaRepository.save(phiThuKhoa);
            }

            phiThuVien.setTongThu(tongPhiThuVien);
            phiThuVien.setGiuLai(tongPhiThuVien/13*3);
            phiThuVien.setNopTruong(tongPhiThuVien - phiThuVien.getGiuLai());
            iPhiThuVienRepository.save(phiThuVien);
            tongPhiThuTruong += phiThuVien.getNopTruong();
        }

        phiThuTruong.setTongThu(tongPhiThuTruong);
        iPhiThuTruongRepository.save(phiThuTruong);
    }
    public void createPhiThuForChosenMonth(int month){
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DATE, 1);
        calendar.set(Calendar.MONTH, month-1);
        Date date = calendar.getTime();
        Truong truong = findTruong();
        PhiThuTruong phiThuTruong = new PhiThuTruong();
        phiThuTruong.setThoiDiem(date);
        phiThuTruong.setDaNop(false);
        phiThuTruong.setTruong(truong);
        Long tongPhiThuTruong = 0L;

        for(Vien vien: truong.getDanhSachVien()){
            PhiThuVien phiThuVien = new PhiThuVien();
            phiThuVien.setVien(vien);
            phiThuVien.setThoiDiem(date);
            phiThuVien.setDaNop(false);
            Long tongPhiThuVien = 0L;

            for(Khoa khoa: vien.getDanhSachKhoa()){
                PhiThuKhoa phiThuKhoa = new PhiThuKhoa();
                phiThuKhoa.setKhoa(khoa);
                phiThuKhoa.setThoiDiem(date);
                phiThuKhoa.setDaNop(false);
                Long tongPhiThuKhoa = 0L;

                for(CongDoanVien congDoanVien: khoa.getDanhSachCDV()){
                    PhiThuCDV phiThuCDV = new PhiThuCDV();
                    phiThuCDV.setCongDoanVien(congDoanVien);
                    phiThuCDV.setThoiDiem(date);
                    phiThuCDV.setDaNop(false);
                    phiThuCDV.setBacLuong(congDoanVien.getBacLuongList().get(congDoanVien.getBacLuongList().size()-1).getBacLuong());
                    phiThuCDV.setHeSoChucVu(congDoanVien.getChucVuList().get(congDoanVien.getChucVuList().size()-1).getHeSoChucVu());
                    phiThuCDV.setLuongNop((long) (1490000L *(phiThuCDV.getBacLuong()+ phiThuCDV.getHeSoChucVu())/100));
                    iPhiThuCDVRepository.save(phiThuCDV);
                    tongPhiThuKhoa += phiThuCDV.getLuongNop();
                }

                phiThuKhoa.setTongThu(tongPhiThuKhoa);
                phiThuKhoa.setGiuLai(tongPhiThuKhoa*7/20);
                Long tmp = phiThuKhoa.getTongThu() - phiThuKhoa.getGiuLai();
                tongPhiThuVien += tmp;
                phiThuKhoa.setNopVien(tmp/13*3);
                phiThuKhoa.setNopTruong(tmp-phiThuKhoa.getNopVien());
                iPhiThuKhoaRepository.save(phiThuKhoa);
            }

            phiThuVien.setTongThu(tongPhiThuVien);
            phiThuVien.setGiuLai(tongPhiThuVien/13*3);
            phiThuVien.setNopTruong(tongPhiThuVien - phiThuVien.getGiuLai());
            iPhiThuVienRepository.save(phiThuVien);
            tongPhiThuTruong += phiThuVien.getNopTruong();
        }

        phiThuTruong.setTongThu(tongPhiThuTruong);
        iPhiThuTruongRepository.save(phiThuTruong);
    }
}
