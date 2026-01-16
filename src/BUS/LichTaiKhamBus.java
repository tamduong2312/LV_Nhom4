package BUS;

import DAO.LichTaiKhamDao;
import MODEL.LichTaiKham;

import java.sql.Date;
import java.util.List;

public class LichTaiKhamBus {
    
    private LichTaiKhamDao lichTaiKhamDao = new LichTaiKhamDao();

    public List<LichTaiKham> getAllLichHen() {
        return lichTaiKhamDao.getAll();
    }

    public boolean themLichHen(LichTaiKham ltk) {
      
        if (ltk.getNgayTaiKham() == null) {
            return false;
        }

        if (ltk.getTrangThai() == null || ltk.getTrangThai().isEmpty()) {
            ltk.setTrangThai("CHUA_DEN");
        }
        return lichTaiKhamDao.themLichHen(ltk);
    }

    public boolean suaLichHen(LichTaiKham ltk) {
        return lichTaiKhamDao.suaLichHen(ltk);
    }

    public boolean xoaLichHen(int id) {
        return lichTaiKhamDao.xoaLichHen(id);
    }
    
    public List<LichTaiKham> getByMaBenhNhan(int maBenhNhan) {
		return lichTaiKhamDao.getByMaBenhNhan(maBenhNhan);
    	
    	
    }
    
    public Date getNgayTaiKhamByMaPK(int maPK) {
		return lichTaiKhamDao.getNgayTaiKhamByMaPK(maPK);
    	
    	
    }
    
    public List<LichTaiKham> timKiemLichHen(String tuKhoa) {
		return lichTaiKhamDao.timKiemLichHen(tuKhoa);
    	
    }
    
    public List<LichTaiKham> locLichHenTheoNgay(String tuNgay, String denNgay) {
		return lichTaiKhamDao.locLichHenTheoNgay(tuNgay, denNgay);
    	
    }
}