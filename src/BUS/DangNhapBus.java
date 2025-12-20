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
	   public int SuaMK( int id1, String mkmoi) {
	        return dkdao.suaMK(id1,mkmoi);
	    }
	   
	   public boolean SSMK(int ma, String mk) {
		   
		   return dkdao.SSMK(ma, mk);
	   }
	   
	   public boolean doiMatKhauTheoEmail(String email, String matKhauMoi) {
		   
		   return dkdao.doiMatKhauTheoEmail(email, matKhauMoi);
	   }

	public boolean themNhanVienKemTaiKhoan(NhanVien nv, String username, String password, String role) throws Exception {

	    return dkdao.themNhanVienKemTaiKhoan(nv, username, password, role);
	}
	
    public List<NguoiDung> getallEmail() {
		return dkdao.getallEmail();
    	
    	
    }
    public List<String> timKiemEmail(String keyword) {
        return dkdao.timKiemEmail(keyword);
    }
    public boolean capLaiTaiKhoan(int maNV, String hoTen, String email, String role) {
		return dkdao.capLaiTaiKhoan(maNV, hoTen, email, role);
    	
    }
    public int doiMatKhauLanDau(int maTK, String matKhauMoi) {
        return dkdao.doiMatKhauLanDau(maTK, matKhauMoi);
    }
	}    

