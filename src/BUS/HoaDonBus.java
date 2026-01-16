package BUS;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import DAO.HoaDonDao;
import MODEL.CTHoaDon;
import MODEL.HoaDon;

public class HoaDonBus {
    private HoaDonDao dao = new HoaDonDao();

    public int taoHoaDon(HoaDon hd) {
        return dao.taoHoaDon(hd);
    }

    public boolean themChiTietHoaDon(CTHoaDon ct) {
        return dao.themChiTietHoaDon(ct);
    }
    

    public boolean capNhatTrangThai(int maHD, String trangThai, double tongTien) {
        return dao.capNhatTrangThai(maHD, trangThai,tongTien);
    }
    
    public List<HoaDon> getAllHoaDon() {
		return dao.getAllHoaDon();
    	
    }
    
    public List<CTHoaDon> getChiTietByMaHD(int maHoaDon) {
		return dao.getChiTietByMaHD(maHoaDon);
    	
    }
    
    public List<HoaDon> timKiemTheoText(String keyword) {
		return dao.timKiemTheoText(keyword);
    	
    	
    }
    
    public List<HoaDon> timKiemTheoNgay(String tuNgay, String denNgay) {
		return dao.timKiemTheoNgay(tuNgay, denNgay);
		
		
    	
    }
    
    public boolean thanhToanTatCaPhieuCuaBNTrongNgay(int maBenhNhan) {
		return dao.thanhToanTatCaPhieuCuaBNTrongNgay(maBenhNhan);
    	
    }
    
    public int getMaHoaDonByMaPhieuKham(int maPK) {
		return dao.getMaHoaDonByMaPhieuKham(maPK);
    	
    }
    
    public void xoaChiTietHoaDon(int maHD, int idGoc, String loai) {

        dao.xoaChiTiet(maHD, idGoc, loai);
    }

    public void capNhatTongTien(int maHD, double tongTien) {

        dao.updateTongTien(maHD, tongTien);
    }
    
    public boolean capNhatGhiChuTheoMaPK(int maPK, String ghiChu) {
    return 	dao.yeuCauHoanTien(maPK, ghiChu);
    }
    
    public String getGhiChuByMaPK(int maPK) {
    	return dao.getGhiChuByMaPK(maPK);
    }
    public boolean updateTrangThaiByMaHD(int maHD) {
    	
    	return dao.updateTrangThaiByMaHD(maHD);
    }
}