package com.quanlycongdoanvien.CDV.infrastructure.services;

import com.quanlycongdoanvien.CDV.infrastructure.models.CongDoanVien;
import com.quanlycongdoanvien.CDV.infrastructure.models.PhiThuCDV;
import com.quanlycongdoanvien.CDV.infrastructure.models.TaiKhoan;
import com.quanlycongdoanvien.CDV.infrastructure.predicate.CDVPredicate;
import com.quanlycongdoanvien.CDV.infrastructure.predicate.PhiThuCDVPredicate;
import com.quanlycongdoanvien.CDV.infrastructure.predicate.TaiKhoanPredicate;
import com.quanlycongdoanvien.CDV.infrastructure.repositories.IBacLuongRepository;
import com.quanlycongdoanvien.CDV.infrastructure.repositories.ICDVRepository;
import com.quanlycongdoanvien.CDV.infrastructure.repositories.IChucVuRepository;
import com.quanlycongdoanvien.CDV.infrastructure.repositories.IHocHamRepository;
import com.quanlycongdoanvien.CDV.infrastructure.repositories.IHocViRepository;
import com.quanlycongdoanvien.CDV.infrastructure.repositories.IPhiThuCDVRepository;
import com.quanlycongdoanvien.CDV.infrastructure.repositories.ITaiKhoanRepository;
import com.quanlycongdoanvien.CDV.infrastructure.repositories.IThamNienRepository;
import com.querydsl.core.types.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CongDoanVienService {
    @Autowired
    private ICDVRepository icdvRepository;
    @Autowired
    private IPhiThuCDVRepository iPhiThuCDVRepository;
    private IBacLuongRepository iBacLuongRepository;
    private IThamNienRepository iThamNienRepository;
    private IHocHamRepository iHocHamRepository;
    private IHocViRepository iHocViRepository;
    private IChucVuRepository iChucVuRepository;
    @Autowired
    private ITaiKhoanRepository iTaiKhoanRepository;

    public CongDoanVienService() {
    }

    public Long findNumberOfCDV() {
        return icdvRepository.count();
    }

    public CongDoanVien findCDVById(Long i) {
        return icdvRepository.findById(i).orElse(null);
    }

    public List<CongDoanVien> filterCDVByPage(int page, int size, CongDoanVien cdv) {
        Predicate predicate = CDVPredicate.createPredicate(cdv);
        return icdvRepository.findAll(predicate, PageRequest.of(page - 1, size, Sort.Direction.ASC, "id")).toList();
    }

    public Long filterCDVCounter(CongDoanVien cdv) {
        Predicate predicate = CDVPredicate.createPredicate(cdv);
        return icdvRepository.count(predicate);
    }

    public CongDoanVien insertOrUpdate(CongDoanVien cdv) {
        return icdvRepository.save(cdv);
    }

    public void delete(Long id) {
        CongDoanVien cdv = icdvRepository.findById(id).orElse(null);
        if (cdv != null) {
            cdv.setExist(false);
            icdvRepository.save(cdv);
        }
    }

    //phi thu related
    public List<PhiThuCDV> findPhiThu(Long i) {
        if (icdvRepository.findById(i).orElse(null) != null)
            return icdvRepository.findById(i).orElse(null).getPhiThuCDVList();
        else return null;
    }

    public List<PhiThuCDV> findPhiThuByYear(Long i, String year) {
        Predicate predicate = PhiThuCDVPredicate.createPredicate(i, year);
        return iPhiThuCDVRepository.findAll(predicate, PageRequest.of(0, 12, Sort.Direction.ASC, "thoiDiem")).toList();
    }

    public List<PhiThuCDV> findPhiThuByMonth(Long i, String year, String month) {
        Predicate predicate = PhiThuCDVPredicate.createPredicateMonth(i, year, month);
        return (List<PhiThuCDV>) iPhiThuCDVRepository.findAll(predicate);
    }

    public PhiThuCDV insertOrUpdate(PhiThuCDV phiThuCDV) {
        return iPhiThuCDVRepository.save(phiThuCDV);
    }

    //tai khoan related
    public TaiKhoan findTaiKhoan(long i) {
        if (icdvRepository.findById(i).orElse(null) == null) return null;
        return icdvRepository.findById(i).orElse(null).getTaiKhoan();
    }

    public TaiKhoan findTaiKhoan(String account) {
        TaiKhoan taiKhoan = new TaiKhoan();
        taiKhoan.setAccount(account);
        Predicate predicate = TaiKhoanPredicate.createPredicate(taiKhoan);
        if (predicate == null) return null;
        return iTaiKhoanRepository.findOne(predicate).orElse(null);
    }

    public TaiKhoan update(TaiKhoan taiKhoan) {
        return iTaiKhoanRepository.save(taiKhoan);
    }
}
