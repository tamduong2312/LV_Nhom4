package BUS;

import java.sql.Date;
import java.util.List;

import DAO.ChuyenKhoaDao;
import DAO.PhieuKhamDao;
import MODEL.NhanVien;
import MODEL.PhieuKham;

public class PhieuKhamBus {
	private PhieuKhamDao ck = new PhieuKhamDao ();
	
	   public int TaoPhieuKham(int maBenhNhan, int maNhanVien, String maChuyenKhoa) {
	       return ck.taoPhieuKham(maBenhNhan, maNhanVien, maChuyenKhoa);
	    }
	   
	    public int TroLyTQTaoPhieuKham(int maBenhNhan, int maNhanVien, String maChuyenKhoa) {
			return ck.TroLyTQTaoPhieuKham(maBenhNhan, maNhanVien, maChuyenKhoa);
	    	
	    	
	    }
	    
	    public int updateTrangThai(int maPK) {
			return ck.updateTrangThai(maPK);
	    	
	    }
	    
	    public int getMaPhieuKhamByBenhNhan(int maBenhNhan) {
			return ck.getMaPhieuKhamByBenhNhan(maBenhNhan);
	    	
	    }
	    
	    public int updateTrangThaiBSKHAMBENH(int maPK) {
			return ck.updateTrangThaiBSKHAMBENH(maPK);
	    	
	    }
	    
	    public int updateTrangThaiChiDinhCLS(int maPK) {
			return ck.updateTrangThaiChiDinhCLS(maPK);
	    	
	    	
	    }
	    
	    public int updateTrangThaiKQChiDinhCLS(int maPK) {
			return ck.updateTrangThaiKQChiDinhCLS(maPK);
	    	
	    }
	    
	    public int updateTrangThaiHoantatKhamBenh(int maPK) {
			return ck.updateTrangThaiHoantatKhamBenh(maPK);
	    	
	    }
	    public int updateTrangThaiHUYKHAM(int maPK) {
			return ck.updateTrangThaiHUYKHAM(maPK);
	    	
	    	
	    }
	    
	    public int updateChuyenKhoa(int maPK, int mack,String ghichu) {
			return ck.updateChuyenKhoa(maPK, mack, ghichu);
	    	
	    	
	    }
	    
	    public int getMaPhieuKhamByNgay(int maBenhNhan, Date ngayKham) {
	    	return ck.getMaPhieuKhamByNgay(maBenhNhan, ngayKham);
	    }
	    
	    public List<PhieuKham> getLichSuKhamByMaBenhNhan(int maBenhNhan) {
			return ck.getLichSuKhamByMaBenhNhan(maBenhNhan);
	    	
	    	
	    }
	    public int getMaPhieuKhamTrongNgay(int maBenhNhan) {
			return ck.getMaPhieuKhamTrongNgay(maBenhNhan);
	    	
	    }
	    
	    public int updateTrangThaiTiepNhanChuyenKhoa(int maPK) {
			return ck.updateTrangThaiTiepNhanChuyenKhoa(maPK);
	    	
	    	
	    }
	    
	    public List<PhieuKham> getAllPhieuKhamDaKhamHomNay() {
			return ck.getAllPhieuKhamDaKhamHomNay();
	    	
	    }
	    
	    public List<PhieuKham> getAllPhieuKhamDaKhamHomNayByDK(String trangthai) {
			return ck.getAllPhieuKhamDaKhamHomNayByDK(trangthai);
	    	
	    }
	    
	    public int updateTrangThaiHoanTatCapThuoc(int maPK) {
			return ck.updateTrangThaiHoanTatCapThuoc(maPK);
	    	
	    	
	    }
	    
	    public List<PhieuKham> getallPhieuKham() {
			return ck.getallPhieuKham();
	    	
	    	
	    }
}
