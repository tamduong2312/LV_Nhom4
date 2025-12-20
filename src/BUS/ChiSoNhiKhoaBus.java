package BUS;

import DAO.ChiSoNhiKhoaDao;
import MODEL.ChiSoNhiKhoa;

public class ChiSoNhiKhoaBus {
    private ChiSoNhiKhoaDao dao = new ChiSoNhiKhoaDao();


    public boolean luuHoacCapNhat(ChiSoNhiKhoa nk) {
        if (dao.getByMaPhieuKham(nk.getMaPhieuKham()) != null) {
            return dao.update(nk);
        } else {
            return dao.insert(nk);
        }
    }
    

    public ChiSoNhiKhoa getChiSoByMaPhieu(int maPhieu) {
        return dao.getByMaPhieuKham(maPhieu);
    }


    public boolean updateChanDoan(int maPhieu, String chanDoan) {
        return dao.updateChanDoanKhamLamSang(maPhieu, chanDoan);
    }
}