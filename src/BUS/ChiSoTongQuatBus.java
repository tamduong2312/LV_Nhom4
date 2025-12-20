package BUS;

import java.util.List;

import DAO.ChiSoDao;
import MODEL.ChiSoTongQuat;

public class ChiSoTongQuatBus {
	
	ChiSoDao dao = new ChiSoDao();
    public boolean luuChiSo(ChiSoTongQuat cs) {
		return dao.luuChiSo(cs); 
    	
    	
    }
    
    public boolean capnhatChiSo(ChiSoTongQuat cs , int id) {
		return dao.capnhatChiSo(cs, id);
    	
    	
    }
    
    public List<ChiSoTongQuat> getallChiSo(int id) {
		return dao.getallChiSo(id);
    	
    	
    }
}
