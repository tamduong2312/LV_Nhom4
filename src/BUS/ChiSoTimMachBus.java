package BUS;

import DAO.ChiSoTimMachDao;
import MODEL.ChiSoTimMach;

public class ChiSoTimMachBus {
    private ChiSoTimMachDao dao = new ChiSoTimMachDao();

    public boolean luuHoacCapNhat(ChiSoTimMach obj) {
        if (dao.getByMaPhieuKham(obj.getMaPhieuKham()) != null) {
            return dao.update(obj);
        } else {
            return dao.insert(obj);
        }
    }
    
    public ChiSoTimMach getChiSoByMaPhieu(int maPhieu) {
        return dao.getByMaPhieuKham(maPhieu);
    }

    public boolean updateChanDoan(int maPhieu, String chanDoan) {
        return dao.updateChanDoanKhamLamSang(maPhieu, chanDoan);
    }
}