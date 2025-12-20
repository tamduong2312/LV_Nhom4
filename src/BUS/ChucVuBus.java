package BUS;

import java.util.List;

import DAO.ChucVuDao;
import MODEL.ChucVu;

public class ChucVuBus {
private ChucVuDao dao = new ChucVuDao();

public List<ChucVu> GetAllTenChucVu() {
	return dao.GetAllTenChucVu();
	
	
}
}
