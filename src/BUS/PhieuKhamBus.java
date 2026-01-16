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
	   

	   
	   
	    public int TroLyTQTaoPhieuKham(int maBenhNhan, int maNhanVien, int maChuyenKhoa) {
			return ck.TroLyTQTaoPhieuKham(maBenhNhan, maNhanVien, maChuyenKhoa);
	    	
	    	
	    }
	    
	    public int BSTaoPhieuKham(int maBenhNhan, int maNhanVien, int maChuyenKhoa, String ghichu) {
			return ck.BSTaoPhieuKham(maBenhNhan, maNhanVien, maChuyenKhoa, ghichu);
	    	
	    }
	    
	    public int taoDangKyChuyenKhoa(int maBenhNhan, int maNhanVien, int maChuyenKhoaMoi, String ghiChu) {
			return ck.taoDangKyChuyenKhoa(maBenhNhan, maNhanVien, maChuyenKhoaMoi, ghiChu);
	
	    	
	    }
	    public void lienKetPhieuKhamVaoDangKy(int idDangKy, int maPK) {
	    	ck.lienKetPhieuKhamVaoDangKy(idDangKy, maPK);
	    }
	    

	
	    public void capNhatDaKhamByID(int id) {
	        ck.capNhatDaKhamByID(id); 
	    }
	    public int updateTrangThai(int maPK) {
			return ck.updateTrangThai(maPK);
	    	
	    }
	    
	    public int getMaPhieuKhamByBenhNhan(int maBenhNhan, int mapk) {
			return ck.getMaPhieuKhamByBenhNhan(maBenhNhan,mapk);
	    	
	    }
	    
	    public int getMaPhieuKhamByIdDangKy(int idDangKy) {
			return ck.getMaPhieuKhamByIdDangKy(idDangKy);
	    	
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
	    
	    public int updateTrangThaiDAKHAMChiDinhCLS(int maPK) {
			return ck.updateTrangThaiDAKHAMChiDinhCLS(maPK);
	    	
	    }
	    
	    public boolean updateTrangThaiPhieuKhamTheoMaDangKy(int idDangKy) {
			return ck.updateTrangThaiPhieuKhamTheoMaDangKy(idDangKy);
	    	
	    	
	    }
	    
	    
	    public int updateTrangThaiDAKHAMTrongChiDinhCLS(int maPK) {
			return ck.updateTrangThaiDAKHAMTrongChiDinhCLS(maPK);
	    	
	    }
	    
	    
	    public int updateTrangThaiThucHienCLSTrongCTChiDinh(int maPK) {
	    	
	    	return ck.updateTrangThaiThucHienCLSTrongCTChiDinh(maPK);
	    }
	    
	    public int updateTrangThaiHoantatKhamBenh(int maPK , int manv) {
			return ck.updateTrangThaiHoantatKhamBenh(maPK,manv);
	    	
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
	    public int getMaPhieuKhamTrongNgay(int maBenhNhan, int mack) {
			return ck.getMaPhieuKhamTrongNgay(maBenhNhan, mack);
	    	
	    }
	    
	    public int updateTrangThaiTiepNhanChuyenKhoa(int maPK) {
			return ck.updateTrangThaiTiepNhanChuyenKhoa(maPK);
	    	
	    	
	    }
	    
	    public List<PhieuKham> getAllPhieuKhamDaKhamHomNay() {
			return ck.getAllPhieuKhamDaKhamHomNay();
	    	
	    }
	    
	    public List<PhieuKham> getDanhSachBenhNhanThuNgan() {
			return ck.getDanhSachBenhNhanThuNgan();
	    	
	    	
	    }
	    
	    public List<PhieuKham> getPhieuKhamTheoBenhNhanTrongNgay1(int maBN) {
			return ck.getPhieuKhamTheoBenhNhanTrongNgay1(maBN);
	    	
	    }
	    
	    public List<PhieuKham> getPhieuKhamTheoBenhNhanTrongNgay(int maBN) {
			return ck.getPhieuKhamTheoBenhNhanTrongNgay(maBN);
	    	
	    }
	    
	    public List<PhieuKham> getDanhsachBenhNhanDaKhamTrongNgay(int maBN) {
			return ck.getDanhsachBenhNhanDaKhamTrongNgay(maBN);
	    	
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
	    public int getMaPhieuKhamGocTuChiDinh(int maBN, int maCK_CLS) {
			return ck.getMaPhieuKhamGocTuChiDinh(maBN, maCK_CLS);
	    	
	    	
	    }
	    
	    public String getTrangThaiByMaPK(int maPK) {
	        if (maPK <= 0) return "";
	        return ck.getTrangThai(maPK); 
	    }
}
