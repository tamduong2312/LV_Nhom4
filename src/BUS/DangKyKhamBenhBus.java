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
    
    
    public int capNhatDaKhamByMaBenhNhan(int maBenhNhan) {
		return dkDao.capNhatDaKhamByMaBenhNhan(maBenhNhan);
    	
    	
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
}
