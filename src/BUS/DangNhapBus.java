package BUS;

import java.util.List;

import DAO.DangNhapDao;
import MODEL.NguoiDung;
import MODEL.NhanVien;
import MODEL.PhanQuyenLogin;

public class DangNhapBus {
	private DangNhapDao dkdao = new DangNhapDao ();


	   public PhanQuyenLogin BSlogin(String tenDangNhap, String matKhau) {
	        return dkdao.BSlogin(tenDangNhap, matKhau);
	    }
	   public List<NguoiDung> getalltk() {
	        return dkdao.getalltk();
	    }
	   public int ThemTK(NguoiDung nv) {
	        return dkdao.themTK(nv);
	    }
	   
	   public int xoaTK(int id) {
	        return dkdao.xoatk(id);
	    }
	   
	   public int SuaTK(NguoiDung nv,int id) {
	        return dkdao.suaTK(nv,id);
	    }

	}

