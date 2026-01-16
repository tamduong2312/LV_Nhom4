package BUS;

import java.util.List;

import DAO.BenhNhanDao;
import DAO.NhanVienDao;
import MODEL.BenhNhan;
import MODEL.NhanVien;

public class BenhNhanBus {
	private BenhNhanDao nvdao = new BenhNhanDao ();

	   public List<BenhNhan> getAllBN() {
	        return nvdao.getAllBenhNhan();
	    }
	   public BenhNhan get1BN(int id) {
	        return nvdao.getBenhNhanById(id);
	    }
	   public int ThemBN(BenhNhan nv) {
	        return nvdao.themBenhNhan(nv);
	    }
	   
	   public int xoaBN(int id) {
	        return nvdao.xoaBN(id);
	    }
	   
	   public int SuaBN(BenhNhan nv,int id) {
	        return nvdao.suaBN(nv,id);
	    }
	   
	   public List<BenhNhan> TimkiemBN(String id) {
	        return nvdao.TimKiemBenhNhan(id);
	    }
	   
	    public String getTenBenhNhanById(int maBenhNhan) {
			return nvdao.getTenBenhNhanById(maBenhNhan);
	    	
	    }

}
