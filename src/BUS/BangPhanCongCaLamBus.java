package BUS;

import java.sql.Time;
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
    
 public boolean suaPhanCong(BangPhanCongCaLam pc) {
		return q.suaPhanCong(pc);
    	
    }
    
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

		    return q.getPhanCongBSTheoThu("Bác sĩ", "", thu);
		}
	 
	    public int layGioiHanBNHomNay(String tenChuyenKhoa) {
			return q.tinhToanGioiHanBNTheoLichTruc(tenChuyenKhoa);
	    	
	    }
	    
	    public String layTenBacSiTrucHomNay(String tenCK) {

	        String[] dsThu = {"Chủ Nhật", "Thứ 2", "Thứ 3", "Thứ 4", "Thứ 5", "Thứ 6", "Thứ 7"};
	        java.util.Calendar cal = java.util.Calendar.getInstance();
	        String thuHienTai = dsThu[cal.get(java.util.Calendar.DAY_OF_WEEK) - 1];


	        if (tenCK == null || tenCK.isEmpty()) {
	            return "Chưa chọn chuyên khoa";
	        }


	        return q.layTenBacSiTrucTheoKhoaVaThu(tenCK, thuHienTai);
	    }
	    public String kiemTraPhongDaCoNguoi(String phong,String cv, String thu, int idExclude) {
			return q.kiemTraPhongDaCoNguoi(phong, thu,cv, idExclude);
	    	
	    }
}
