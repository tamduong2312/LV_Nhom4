package BUS;

import java.util.List;

import DAO.BenhNhanDao;
import DAO.ThuocDao;
import MODEL.BenhNhan;
import MODEL.Thuoc;

public class ThuocBus {
	private ThuocDao nvdao = new ThuocDao ();

	   public List<Thuoc> getAllTHUOC() {
	        return nvdao.getAllThuoc();
	    }
	   
	   public int ThemThuoc(Thuoc nv) {
	        return nvdao.themThuoc(nv);
	    }
	   
	   public int xoaThuoc(int id) {
	        return nvdao.xoaThuoc(id);
	    }
	   
	   public int Suathuoc(Thuoc nv,int id) {
	        return nvdao.suaThuoc(nv,id);
	    }
	   
	   public List<Thuoc> Timkiemthuoc(String id) {
	        return nvdao.TimKiemThuoc(id);
	    }
	   
	    public List<String> getThuocByTenthuoc(String keyword) {
			return nvdao.getThuocByTenthuoc(keyword);
	    	
	    	
	    }
	    
	    public Thuoc getThuocById(int maThuoc) {
			return nvdao.getThuocById(maThuoc);
	    	
	    	
	    }
}
