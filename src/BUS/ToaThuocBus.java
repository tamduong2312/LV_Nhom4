package BUS;

import DAO.ToaThuocDao;
import MODEL.CTToaThuoc;
import MODEL.DonThuocCho;
import MODEL.ToaThuoc;
import java.util.List;

public class ToaThuocBus {

    private ToaThuocDao dao = new ToaThuocDao();


    public int taoToaThuoc(int maPhieuKham, String ghiChu) {
        return dao.taoToaMoi(maPhieuKham, ghiChu);
    }

 
    public ToaThuoc getToaThuocByPhieuKham(int maPhieuKham) {
        return dao.getByPhieuKham(maPhieuKham);
    }


    public List<CTToaThuoc> getListChiTiet(int maToa) {
        return dao.getChiTietByMaToa(maToa);
    }


    public boolean themThuocVaoToa(CTToaThuoc ct) {
        return dao.themChiTiet(ct);
    }


    public boolean xoaThuoc(int id) {
        return dao.xoaChiTiet(id);
    }
    
    public boolean capNhatChiTiet(CTToaThuoc ct) {
		return dao.capNhatChiTiet(ct);
    	
    	
    }
    
    public List<CTToaThuoc> getChiTietByMaPhieuKham(int maPhieuKham) {
		return dao.getChiTietByMaPhieuKham(maPhieuKham);
    	
    }
    
    public List<ToaThuoc> getListToaThuocByBenhNhan(int maBenhNhan) {
		return dao.getListToaThuocByBenhNhan(maBenhNhan);
    	
    }
    
    public boolean updateToaThuocByPhieuKham(String tongtien, int mapk) {
		return dao.updateToaThuocByPhieuKham(tongtien, mapk);
    	
    }
    
    public List<DonThuocCho> getAllLichSuCapPhat() {
		return dao.getAllLichSuCapPhat();
    	
    }
    public List<DonThuocCho> timKiemLichSuTheoTuKhoa(String keyword) {
		return dao.timKiemLichSuTheoTuKhoa(keyword);
    	
    }
    
    public List<DonThuocCho> timKiemLichSuTheoNgay(String tuNgay, String denNgay) {
		return dao.timKiemLichSuTheoNgay(tuNgay, denNgay);
    	
    }
    
    public void capNhatGhiChuToaThuoc(int maPK, String noiDung) {
    	 dao.appendGhiChuHoanTien(maPK, noiDung);
    }
    public boolean updateTrangThaiToaThuoc(int maPK, String trangThaiMoi) {
    	return dao.updateTrangThaiToaThuoc(maPK, trangThaiMoi);
    }
    
    public ToaThuoc getTrangThaiVaGhiChuByMaPK(int maPK) {
		return dao.getTrangThaiVaGhiChuByMaPK(maPK);
    	
    }
}