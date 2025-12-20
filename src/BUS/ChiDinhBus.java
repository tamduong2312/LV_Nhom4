package BUS;
import DAO.ChiDinhDao;
import MODEL.PhieuChiDinh;
import MODEL.ChiTietChiDinh;
import MODEL.DanhSachChoCLS;
import MODEL.KetQuaCDHA;

import java.util.List;

public class ChiDinhBus {
    private ChiDinhDao dao = new ChiDinhDao();

    public int taoPhieuChiDinh(int maPhieuKham, int maNguoiChiDinh) {
		return dao.taoPhieuChiDinh(maPhieuKham, maNguoiChiDinh);
    	
    }
    
    public boolean themChiTietDichVu(ChiTietChiDinh ct) {
		return dao.themChiTietDichVu(ct);
    	
    	
    }
    
    public List<ChiTietChiDinh> getChiTietByMaPhieu(int maPhieuChiDinh) {
		return dao.getChiTietByMaPhieu(maPhieuChiDinh);
    	
    }
    
    public int capNhatTongTienPhieu(int maPhieuChiDinh) {
    	return dao.capNhatTongTienPhieu(maPhieuChiDinh);
    }

    public boolean xoaChiTietDichVu(int idChiTiet) {
        return dao.xoaChiTietDichVu(idChiTiet);
    }
    
    public int getMaPhieuChiDinhByMaPhieuKham(int maPhieuKham) {
        return dao.getMaPhieuChiDinhByMaPhieuKham(maPhieuKham);
    }
    
    public List<DanhSachChoCLS> getDanhSachChoCLS() {
        return dao.getDanhSachChoCLS();
    }
    public boolean luuKetQuaCDHAMoi(int idChiTiet, String moTa, String ketLuan, String deNghi, 
            int maBacSi, String anh1, String anh2) {
				return dao.luuKetQuaCDHAMoi(idChiTiet, moTa, ketLuan, deNghi, maBacSi, anh1, anh2);
    	
    }
    
    public KetQuaCDHA layKetQuaTheoIdChiTiet(int idChiTiet) {
		return dao.layKetQuaTheoIdChiTiet(idChiTiet);
    	
    }
    public KetQuaCDHA layKetQuaTheoId(int idChiTiet) {
		return dao.layKetQuaTheoId(idChiTiet);
    	
    }
    
    public DanhSachChoCLS getThongTinChiTietById(int idChiTiet) {
		return dao.getThongTinTuMaKetQua(idChiTiet);
    	
    }
    
    public boolean capNhatKetQuaDaCo(int idChiTiet, String moTa, String ketLuan, String deNghi, 
            int maBacSi, String anh1, String anh2) {
				return dao.capNhatKetQuaDaCo(idChiTiet, moTa, ketLuan, deNghi, maBacSi, anh1, anh2);
    	
    }
}