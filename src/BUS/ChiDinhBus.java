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
    
    public int themChiTietDichVu(ChiTietChiDinh ct,int manv) {
		return dao.themChiTietDichVu(ct,manv);
    	
    	
    }
    
    public void lienKetChiTietDichVuVaoDangKy(int idDangKy, int idChiTietDV) {
    	dao.lienKetChiTietDichVuVaoDangKy(idDangKy, idChiTietDV);
    }
    
    public List<ChiTietChiDinh> getChiTietByMaPhieu(int maPhieuChiDinh) {
		return dao.getChiTietByMaPhieu(maPhieuChiDinh);
    	
    }
    
    
    public List<ChiTietChiDinh> getChiTietXNByMaPhieu(int maPhieuChiDinh) {
		return dao.getChiTietXNByMaPhieu(maPhieuChiDinh);
    	
    	
    }
    
    public int capNhatTongTienPhieu(int maPhieuChiDinh) {
    	return dao.capNhatTongTienPhieu(maPhieuChiDinh);
    }

    
    public String getTenDichVuThucTe(int id) {
		return dao.getTenDichVuThucTe( id);
    	
    	
    }
    public void capNhatTongTienPhieuChiDinh(int maPCD) {
    	
    	dao.capNhatTongTienPhieuChiDinh(maPCD);
    }
    
    public boolean xoaChiTietDichVu(int idChiTiet) {
        return dao.xoaChiTietDichVu(idChiTiet);
    }
    
    public int getMaPhieuChiDinhByMaPhieuKham(int maPhieuKham) {
        return dao.getMaPhieuChiDinhByMaPhieuKham(maPhieuKham);
    }
    
    public List<DanhSachChoCLS> getDanhSachChoCLS(int mack) {
        return dao.getDanhSachChoCLS(mack);
    }
    public boolean luuKetQuaCDHAMoi(int idChiTiet, String moTa, String ketLuan, String deNghi, 
            int maBacSi, String anh1, String anh2) {
				return dao.luuKetQuaCDHAMoi(idChiTiet, moTa, ketLuan, deNghi, maBacSi, anh1, anh2);
    	
    }
    
    public boolean BSluuKetQuaCDHAMoi(int idChiTiet, String moTa, String ketLuan, String deNghi, 
            int maBacSi, String anh1, String anh2) {
				return dao.BSluuKetQuaCDHAMoi(idChiTiet, moTa, ketLuan, deNghi, maBacSi, anh1, anh2);
    	
    }
    
    
    public KetQuaCDHA layKetQuaTheoIdChiTiet(int idChiTiet) {
		return dao.layKetQuaTheoIdChiTiet(idChiTiet);
    	
    }
    public KetQuaCDHA layKetQuaTheoId(int idChiTiet) {
		return dao.layKetQuaTheoId(idChiTiet);
    	
    }
    
    public DanhSachChoCLS getThongTinChiTietById(int idChiTiet) {
		return dao.getThongTinChiTietById(idChiTiet);
    	
    }
    
    
    public List<ChiTietChiDinh> getKQCDHAChiTietByMaPhieu(int maPhieuChiDinh) {
		return dao.getKQCDHAChiTietByMaPhieu(maPhieuChiDinh);
    	
    	
    }
    
    public boolean capNhatKetQuaDaCo(int idChiTiet, String moTa, String ketLuan, String deNghi, 
            int maBacSi, String anh1, String anh2) {
				return dao.capNhatKetQuaDaCo(idChiTiet, moTa, ketLuan, deNghi, maBacSi, anh1, anh2);
    	
    }
    
    public boolean BScapNhatKetQuaDaCo(int idChiTiet, String moTa, String ketLuan, String deNghi, 
            int maBacSi, String anh1, String anh2) {
				return dao.BScapNhatKetQuaDaCo(idChiTiet, moTa, ketLuan, deNghi, maBacSi, anh1, anh2);
    	
    }
    
    
}