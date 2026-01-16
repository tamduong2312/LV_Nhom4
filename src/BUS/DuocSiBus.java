package BUS;

import java.util.List;

import DAO.DuocSiDao;
import MODEL.DonThuocCho;
import MODEL.PhieuKham;

public class DuocSiBus {

	
	private DuocSiDao dao = new DuocSiDao();
	
    public List<DonThuocCho> getDsChoCapPhat() {
		return dao.getDsChoCapPhat();
    	
    	
    }
    
    public List<DonThuocCho> timKiemPhieuKhamThanhToan(String keyword) {
		return dao.timKiemPhieuKhamThanhToan(keyword);
    	
    }
	
    public List<DonThuocCho> getDsChoTheoDK(String trangthai) {
		return dao.getDsChoTheoDK(trangthai);
    	
    	
    }
    
    public int demtrangthaidacapthuoc() {
		return dao.demtrangthaidacapthuoc();
    	
    	
    }
    
    public int demtrangthaichocapthuoc() {
		return dao.demtrangthaichocapthuoc();
    	
    }
}
