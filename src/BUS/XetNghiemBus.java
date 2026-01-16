package BUS;

import java.util.List;

import DAO.XetNghiemDao;
import MODEL.KetQuaCDHA;
import MODEL.KetQuaXetNghiem;

public class XetNghiemBus {
    private XetNghiemDao dao = new XetNghiemDao();

    public KetQuaXetNghiem getKetQua(int idChiTiet) {
        return dao.getByMaChiTiet(idChiTiet);
    }
    
    
    public List<KetQuaXetNghiem> getKetQua2(int idChiTiet) {
        return dao.GetKqXetNghiemByMaPhieu1(idChiTiet);
    }
    
    public List<KetQuaXetNghiem> GetKqXetNghiemByMaPhieu2(int idKetQua) {
		return dao.GetKqXetNghiemByMaPhieu2(idKetQua);
    	
    	
    }

    public boolean luuKetQua(KetQuaXetNghiem kq) {
        KetQuaXetNghiem exist = dao.getByMaChiTiet(kq.getMaChiTietChiDinh());
        boolean result;
        if (exist == null) {
            result = dao.insert(kq);
        } else {

            result = dao.update(kq); 
        }
        
        if (result) {
            dao.updateStatusChiTiet(kq.getMaChiTietChiDinh());
        }
        return result;
    }
    
    public boolean BSupdate(KetQuaXetNghiem kq) {
		return dao.BSupdate(kq);
    	
    }
    
    public List<KetQuaXetNghiem> GetKqXetNghiemByMaPhieu(int maphieu) {
		return dao.GetKqXetNghiemByMaPhieu1(maphieu);
    	
    }
    
    public List<KetQuaCDHA> GetKqCDHAByMaPhieu(int maphieu) {
		return dao.GetKqCDHAByMaPhieu(maphieu);
    	
    }
    
    public int layIdChiTietByMaPhieuKham(int maPK) {
		return dao.layIdChiTietByMaPhieuKham(maPK);
    	
    }
}