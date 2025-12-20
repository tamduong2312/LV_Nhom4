package BUS;

import java.util.List;
import DAO.PhongChucNangDao;
import MODEL.PhongChucNang;

public class PhongChucNangBus {

   
    private PhongChucNangDao dao = new PhongChucNangDao();

    // 1. Lấy tất cả danh sách phòng
    public List<PhongChucNang> getAllPhong() {
        return dao.getPhongChucNang();
    }

    // 2. Thêm phòng mới
    public boolean themPhong(PhongChucNang p) {
     
        if (p.getTenphong() == null || p.getTenphong().trim().isEmpty()) {
            return false;
        }
        return dao.themPhong(p);
    }

 
    public boolean suaPhong(PhongChucNang p) {
      
        if (p.getTenphong() == null || p.getTenphong().trim().isEmpty()) {
            return false;
        }
        return dao.suaPhong(p);
    }

    // 4. Xóa phòng
    public boolean xoaPhong(int maPhong) {
        return dao.xoaPhong(maPhong);
    }

    // 5. Tìm kiếm phòng
    public List<PhongChucNang> timKiemPhong(String keyword) {
        return dao.timKiemPhong(keyword);
    }
    public List<PhongChucNang> getPhongChucNang() {
    	return dao.getPhongChucNang();
    	
    	
    }
}