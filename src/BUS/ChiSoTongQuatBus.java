package BUS;

import java.util.List;

import DAO.ChiSoDao;
import MODEL.ChiSoTongQuat;
import MODEL.KhamLamSang;

public class ChiSoTongQuatBus {
	
	ChiSoDao dao = new ChiSoDao();
    public boolean luuChiSo(ChiSoTongQuat cs) {
		return dao.luuChiSo(cs); 
    	
    	
    }
    

    public List<ChiSoTongQuat> getallChiSo(int id) {
		return dao.getallChiSo(id);
    	
    	
    }
    
    
    
    public boolean updateSinhHieu(ChiSoTongQuat cs,int id) {
		return dao.updateSinhHieu(cs, id);
    	
    	
    	
    }
    
    public ChiSoTongQuat getSinhHieuMoiNhatTrongNgay(int maBenhNhan) {
		return dao.getSinhHieuMoiNhatTrongNgay(maBenhNhan);
    	
    }
    public KhamLamSang getKhamLamSangMoiNhatTrongNgay(int maBenhNhan) {
		return dao.getKhamLamSangMoiNhatTrongNgay(maBenhNhan);
    	
    	
    }
    
    
    public boolean updateNhiKhoa(ChiSoTongQuat cs, int id) {
		return dao.updateNhiKhoa(cs, id);
    	
    }
    public boolean updateRangHamMat(ChiSoTongQuat cs, int id) {
		return dao.updateRangHamMat(cs, id);
    	
    }
    
    public boolean updateTaiMuiHong(ChiSoTongQuat cs, int id) {
		return dao.updateTaiMuiHong(cs, id);
    	
    }
    
    public boolean updateTimMach(ChiSoTongQuat cs, int id) {
		return dao.updateTimMach(cs, id);
    	
    }
}
