package BUS;

import java.util.List;
import DAO.PhongChucNangDao;
import MODEL.PhongChucNang;

public class PhongChucNangBus {

   
    private PhongChucNangDao dao = new PhongChucNangDao();

 
    public List<PhongChucNang> getAllPhong() {
        return dao.getPhongChucNang();
    }


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

    public boolean xoaPhong(int maPhong) {
        return dao.xoaPhong(maPhong);
    }


    public List<PhongChucNang> timKiemPhong(String keyword) {
        return dao.timKiemPhong(keyword);
    }
    public List<PhongChucNang> getPhongChucNang() {
    	return dao.getPhongChucNang();
    	
    	
    }
}