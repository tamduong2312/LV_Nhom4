package BUS;

import java.util.List;

import DAO.KhoThuocDao;
import MODEL.ChiTietPhieuNhap;
import MODEL.KhoThuoc;
import MODEL.PhieuNhapThuoc;

public class KhoThuocBus {

    private KhoThuocDao khoThuocDao = new KhoThuocDao();

    public List<KhoThuoc> getAllKhoThuoc() {
        return khoThuocDao.getAllKhoThuoc();
    }

    public List<PhieuNhapThuoc> getAllPhieuNhapThuoc() {
        return khoThuocDao.getAllPhieuNhap();
    }


    public List<ChiTietPhieuNhap> getChiTietPhieuNhap(int maPhieu) {
        return khoThuocDao.getChiTietByMaPhieu(maPhieu);
    }
    public PhieuNhapThuoc getPhieuNhapById(int maPhieu) {
		return khoThuocDao.getPhieuNhapById(maPhieu);
    	
    	
    }

    public int taoPhieuNhap(PhieuNhapThuoc pn) {
        return khoThuocDao.insertPhieuNhap(pn);
    }


    public boolean themChiTiet(ChiTietPhieuNhap ct) {
        return khoThuocDao.insertChiTietPhieuNhap(ct);
    }


    public int xoactphieunhap(int id) {
        return khoThuocDao.xoactphieunhap(id); 
    }


    public boolean hoanTatNhapKho(int maPhieu) {
      
        List<ChiTietPhieuNhap> listChiTiet = khoThuocDao.getChiTietByMaPhieu(maPhieu);
        
        if (listChiTiet.isEmpty()) {
            return false;
        }

        double tongTien = 0;


        for (ChiTietPhieuNhap ct : listChiTiet) {
            tongTien += ct.getThanhTien();


            boolean tonTai = khoThuocDao.checkThuocTonTai(ct.getMaThuoc());

            if (tonTai) {
 
                khoThuocDao.updateSoLuongTon(ct.getMaThuoc(), ct.getSoLuongNhap());
            } else {
    
                khoThuocDao.insertKhoMoi(ct.getMaThuoc(), ct.getSoLuongNhap());
            }
        }

    
        return khoThuocDao.hoanTatPhieuNhap(maPhieu, tongTien);
    }
    
    public List<KhoThuoc> timKiemKhoThuoc(String keyword) {
        return khoThuocDao.timKiemKhoThuoc(keyword);
    }
    

    
 
    
    public int getSoLuongTon(int maThuoc) {
		return khoThuocDao.getSoLuongTon(maThuoc);

    }
    public String getHsd(int maThuoc) {
    	return  khoThuocDao.getHsd(maThuoc);
    }
    
    
    public boolean updateTruSoLuongTon(String tenThuoc, int soLuongTru) {
		return khoThuocDao.updateTruSoLuongTon(tenThuoc, soLuongTru);
    	
    	
    }
}