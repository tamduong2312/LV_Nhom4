package BUS;

import java.util.List;

import DAO.DanhMucBenhLyDao;
import MODEL.DanhMucBenhLy;

public class DanhMucBenhLyBus {
private DanhMucBenhLyDao dao = new  DanhMucBenhLyDao();

public List<DanhMucBenhLy> getallDanhMucBenhLy(String keyword) {
	return dao.getListDanhMucBenhLy(keyword);
	
	
}

public List<DanhMucBenhLy> getAll() {
    return dao.getAll();
}

public boolean them(DanhMucBenhLy b) {
    return dao.them(b);
}

public boolean sua(DanhMucBenhLy b) {
    return dao.sua(b);
}

public boolean xoa(int id) {
    return dao.xoa(id);
}

public List<DanhMucBenhLy> timKiem(String keyword) {
    return dao.timKiem(keyword);
}


}
