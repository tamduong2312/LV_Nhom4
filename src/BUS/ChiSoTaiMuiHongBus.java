package BUS;

import DAO.ChiSoTaiMuiHongDao;
import MODEL.ChiSoTaiMuiHong;

public class ChiSoTaiMuiHongBus {
    private ChiSoTaiMuiHongDao dao = new ChiSoTaiMuiHongDao();

    public boolean luuHoacCapNhat(ChiSoTaiMuiHong obj) {
        if (dao.getByMaPhieuKham(obj.getMaPhieuKham()) != null) {
            return dao.update(obj);
        } else {
            return dao.insert(obj);
        }
    }
    
    public ChiSoTaiMuiHong getChiSoByMaPhieu(int maPhieu) {
        return dao.getByMaPhieuKham(maPhieu);
    }

    public boolean updateChanDoan(int maPhieu, String chanDoan) {
        return dao.updateChanDoanKhamLamSang(maPhieu, chanDoan);
    }
}