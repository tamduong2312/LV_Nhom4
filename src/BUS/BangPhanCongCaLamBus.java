package BUS;

import java.util.List;

import DAO.BangPhanCongLamViecDao;
import MODEL.BangPhanCongCaLam;
import MODEL.NhanVien;

public class BangPhanCongCaLamBus {
	BangPhanCongLamViecDao q = new BangPhanCongLamViecDao();
    public List<NhanVien> getNVChuaCoCalam(String chucvu,String chuyenkhoa) {
		return q.getNVChuaCoCalam(chucvu,chuyenkhoa);
	
    }
    
    
    public List<BangPhanCongCaLam> getNVDaCoCalam(String chucvu,String chuyenkhoa) {
		return q.getNVDaCoCalam(chucvu,chuyenkhoa);
	
    }
    
    public List<NhanVien> getNVKhadungTheoThu(String chucVu, String chuyenKhoa, String thu) {
		return q.getNVKhadungTheoThu(chucVu, chuyenKhoa, thu);
    	
    	
    }
    
    public List<BangPhanCongCaLam> getPhanCongTheoThu(String chucVu, String chuyenKhoa, String thu) {
		return q.getPhanCongTheoThu(chucVu, chuyenKhoa, thu);
    	
    }
    
    public List<BangPhanCongCaLam> getAllBangPhanCong() {
		return q.getAllBangPhanCong();
    	
    	
    }
    
    public boolean themPhanCong(BangPhanCongCaLam pc) {
		return q.themPhanCong(pc);
    	
    	
    }
    
    public boolean xoaPhanCong(int idPhanCong) {
		return q.xoaPhanCong(idPhanCong);
    	
    	
    }
    
//    public boolean suaPhanCong(BangPhanCongCaLam pc) {
//		return q.suaPhanCong(pc);
//    	
//    }
    
    public boolean xoaPhanCongTheoNV(int maNV, String thu) {
		return q.xoaPhanCongTheoNV(maNV, thu);
    	
    }
	public List<BangPhanCongCaLam> getCaLamCuaNhanVien(int maNV) {
		// TODO Auto-generated method stub
		return q.getCaLamCuaNhanVien(maNV);
	}
	
	 public String getTenNhanVien(int maNV) {
		return q.getTenNhanVien(maNV);
		 
		 
	 }
	 
	 public List<BangPhanCongCaLam> getLichBacSiTheoThu(String thu) {
		    // Truyền vào "Bác sĩ" để DAO tìm tất cả "Bác sĩ..." (chuyên khoa, tổng quát, v.v.)
		    // Truyền "" vào chuyên khoa để DAO lấy tất cả chuyên khoa
		    return q.getPhanCongBSTheoThu("Bác sĩ", "", thu);
		}
}
