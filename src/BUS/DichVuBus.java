package BUS;

import java.util.List;

import DAO.DichVuDao;
import MODEL.DichVu;
import MODEL.Phong;

public class DichVuBus {
private DichVuDao dao = new DichVuDao();

public List<DichVu> getDichVuByTen(String tendichvu) {
	return dao.getDichVuByTen(tendichvu);
	
}

public List<String> timKiemDichVu(String keyword) {
	return dao.timKiemDichVu(keyword);
	
}
public List<DichVu> getDichVu() {
	return dao.getDichVu();
	
}


public List<Phong> getPhong() {
	
	return dao.getAll(); 
	}

public boolean them(DichVu dv) { 
	
	return dao.themDichVu(dv); 
	}

public boolean sua(DichVu dv) {
	
	return dao.suaDichVu(dv); 
	}

public boolean xoa(int id) {
	
	return dao.xoaDichVu(id);
	}

public List<DichVu> timKiem(String k) {
	return dao.timKiemDichVuFull(k); 
	}
}
