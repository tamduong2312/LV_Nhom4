package BUS;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import DAO.HoaDonDao;
import MODEL.CTHoaDon;
import MODEL.HoaDon;

public class HoaDonBus {
    private HoaDonDao dao = new HoaDonDao();

    public int taoHoaDon(HoaDon hd) {
        return dao.taoHoaDon(hd);
    }

    public boolean themChiTietHoaDon(CTHoaDon ct) {
        return dao.themChiTietHoaDon(ct);
    }
    

    public boolean capNhatTrangThai(int maHD, String trangThai, double tongTien) {
        return dao.capNhatTrangThai(maHD, trangThai,tongTien);
    }
    
    public List<HoaDon> getAllHoaDon() {
		return dao.getAllHoaDon();
    	
    }
    
    public List<CTHoaDon> getChiTietByMaHD(int maHoaDon) {
		return dao.getChiTietByMaHD(maHoaDon);
    	
    }
    
    public List<HoaDon> timKiemTheoText(String keyword) {
		return dao.timKiemTheoText(keyword);
    	
    	
    }
    
    public List<HoaDon> timKiemTheoNgay(String tuNgay, String denNgay) {
		return dao.timKiemTheoNgay(tuNgay, denNgay);
		
		
    	
    }
    

}