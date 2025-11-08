package BUS;

import java.util.List;

import DAO.ChuyenKhoaDao;
import DAO.NhanVienDao;
import MODEL.ChuyenKhoa;
import MODEL.NhanVien;

public class ChuyenKhoaBus {

	
	private ChuyenKhoaDao ck = new ChuyenKhoaDao ();

	   public List<ChuyenKhoa> getAllCK() {
	        return ck.getallChuyenKhoa();
	    }
	   
}
