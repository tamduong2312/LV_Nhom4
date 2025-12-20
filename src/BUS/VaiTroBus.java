package BUS;

import java.util.List;

import DAO.VaiTroDao;
import MODEL.VaiTro;

public class VaiTroBus {
private VaiTroDao  dao = new VaiTroDao();

public List<VaiTro> GetAllTenVaiTro() {
	return dao.GetAllTenVaiTro();
	
}
}
