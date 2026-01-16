package BUS;

import java.util.List;

import DAO.DangNhapDao;
import DAO.NhanVienDao;
import MODEL.NhanVien;

public class NhanVienBUS {
	private NhanVienDao nvdao = new NhanVienDao ();

	   public List<NhanVien> getAllNV() {
	        return nvdao.getAllNhanVien();
	    }

	   public int ThemNV(NhanVien nv) {
	        return nvdao.themNhanVien(nv);
	    }
	   public int xoaNV(int id) {
	        return nvdao.xoanv(id);
	    }

	   public int SuaNV(NhanVien nv,int id) {
	        return nvdao.suaNV(nv,id);
	    }
	   
	   public List<NhanVien> TimkiemNV(String id) {
	        return nvdao.TimkiemNhanVien(id);
	    }
	   
	    public boolean checkTrungSDT(String sdt, int idHienTai) {
	    	return nvdao.checkTrungSDT(sdt, idHienTai);
	    }
	    
	    public boolean checkTrungSDT1(String sdt) {
			return nvdao.checkTrungSDT1(sdt);
	    	
	    }

}
