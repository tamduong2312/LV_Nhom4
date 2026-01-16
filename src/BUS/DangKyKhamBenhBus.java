package BUS;

import java.util.List;

import DAO.DangKyKhamBenhDao;
import MODEL.DangKyKhamBenh;

public class DangKyKhamBenhBus {

    private DangKyKhamBenhDao dkDao = new DangKyKhamBenhDao();


    public List<DangKyKhamBenh> getAll() {
        return dkDao.getAll();
    }


    public int themDangKy(DangKyKhamBenh dk) {
        return dkDao.themDangKy(dk);
    }

    public int getMaPhieuKhamByIdDangKy(int idDangKy) {
		return dkDao.getMaPhieuKhamByIdDangKy(idDangKy);
    	
    }
    
    
    public int capNhatDaKhamById(int idDangKy) {
		return dkDao.capNhatDaKhamById(idDangKy);
    	
    }
    
    public int capNhatTrangThaiThucHienCLSById(int idDangKy) {
    	
    	return dkDao.capNhatTrangThaiThucHienCLSById(idDangKy);
    }
    
    public int updateTrangThaiKQChiDinhCLS(int maPK) {
    	return dkDao.updateTrangThaiKQChiDinhCLS(maPK);
    }

    public int capNhatTrangThai(int id) {
        return dkDao.capNhatTrangThai(id);
    }
    public int capNhatTrangThaiHUY(int id) {
        return dkDao.capNhatTrangThaiHUY(id);
    }

 
    public int xoaDangKy(int id) {
        return dkDao.xoaDangKy(id);
    }


    public List<DangKyKhamBenh> timKiem(String keyword) {
        return dkDao.timKiem(keyword);
    }


    public int laySoThuTuTiepTheo(int maChuyenKhoa) {
        return dkDao.laySoThuTuTiepTheo(maChuyenKhoa);
    }
    
    public int demSoCaThucTeChuaHuy(int maCK) {
		return dkDao.demSoCaThucTeChuaHuy(maCK);
    	
    	
    }
    
    public List<DangKyKhamBenh> getAllToday() {
        return dkDao.getAllhomnay();
    }
    public int updateTrangThaiChoKhamBS(int maPK) {
		return dkDao.updateTrangThaiChoKhamBS(maPK);
    	
    }
    public List<DangKyKhamBenh> getAllTodayBSTQ(int maChuyenKhoa) {
        return dkDao.getAllTodayByChuyenKhoa(maChuyenKhoa);
    }
    public List<DangKyKhamBenh> getAlltodayTroLyTQ( int maChuyenKhoa) {
		return dkDao.getAlltodayTroLyTQ(maChuyenKhoa);
    	
    }
    public int capNhatTrangThaiVangMat(int id) {
		return dkDao.capNhatTrangThaiVangMat(id);
    	  	
    }
    
    public List<DangKyKhamBenh> getAllTodayByBSChuyenKhoa(int maChuyenKhoa) {
		return dkDao.getAllTodayByBSChuyenKhoa(maChuyenKhoa);
    	
    }
    
//    public List<DangKyKhamBenh> getAllTodayByBSTQ(int maChuyenKhoa) {
//		return dkDao.getAllTodayByBSTQ(maChuyenKhoa);
//    	
//    }
//    
    public List<DangKyKhamBenh> getAllVangMatToDay() {
		return dkDao.getAllVangMatToDay();
    	
    	
    }
    
    public boolean DangKySTTmoidobenhnhanquaylaikham(int idDangKy) {
		return dkDao.DangKySTTmoidobenhnhanquaylaikham(idDangKy);
    	
    }
    
    public List<DangKyKhamBenh> getAlltodayTroLyCK(int ck) {
		return dkDao.getAlltodayTroLyCK(ck);
    	
    	
    }
    
    public List<DangKyKhamBenh> getAllVangMatCKToDay(String ck) {
		return dkDao.getAllVangMatCKToDay(ck);
    	
    }
    

    
    public int capNhatTrangThaiDaKham(int maPhieuKham) {
		return dkDao.capNhatTrangThaiDaKham(maPhieuKham);
    	
    }
    
    public boolean capNhatChuyenKhoaDangKy(int maBenhNhan, int maChuyenKhoaMoi) {
		return dkDao.capNhatChuyenKhoaDangKy(maBenhNhan, maChuyenKhoaMoi);
    	
    }
    
    public List<DangKyKhamBenh> getAllLichSuDangKy() {
		return dkDao.getAllLichSuDangKy();
    	
    }
    public List<DangKyKhamBenh> timKiemLichSuTheoText(String keyword) {
    	return dkDao.timKiemLichSuTheoText(keyword);
    }
    public List<DangKyKhamBenh> locLichSuTheoNgay(String tuNgay, String denNgay) {
		return dkDao.locLichSuTheoNgay(tuNgay, denNgay);
    	
    }
    
    public boolean taoDangKyDichVuCLS(int maBN, int maNV, int maKhoaCLS, String ghiChu,int mapk, int mactchidinh) {
		return dkDao.taoDangKyDichVuCLS(maBN, maNV, maKhoaCLS, ghiChu, mapk,mactchidinh);
    	
    }
    public boolean kiemTraDaDangKyTrongNgay(int maBN, int maCK) {
		return dkDao.kiemTraDaDangKyTrongNgay(maBN, maCK);
    	
    }
    
}
