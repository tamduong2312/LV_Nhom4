package BUS;

import DAO.ChiSoRangHamMatDao;
import MODEL.ChiSoRangHamMat;

public class ChiSoRangHamMatBus {
    private ChiSoRangHamMatDao dao = new ChiSoRangHamMatDao();

    public boolean luuHoacCapNhat(ChiSoRangHamMat obj) {
        if (dao.getByMaPhieuKham(obj.getMaPhieuKham()) != null) {
            return dao.update(obj);
        } else {
            return dao.insert(obj);
        }
    }
    
    public ChiSoRangHamMat getChiSoByMaPhieu(int maPhieu) {
        return dao.getByMaPhieuKham(maPhieu);
    }

    public boolean updateChanDoan(int maPhieu, String chanDoan) {
        return dao.updateChanDoanKhamLamSang(maPhieu, chanDoan);
    }
}