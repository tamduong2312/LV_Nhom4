package BUS;

import java.util.List;
import DAO.KhamLamSangDao;
import MODEL.KhamLamSang;

public class KhamLamSangBus {
    private KhamLamSangDao dao = new KhamLamSangDao();

    // Thêm mới
    public boolean themKhamLamSang(KhamLamSang kls) {

        if (kls.getMaPhieuKham() <= 0) {
            return false;
        }
        return dao.themKhamLamSang(kls);
    }


    
    public KhamLamSang getByMaPhieuKham(int maPhieuKham) {
        return dao.getKhamLamSangByMaPhieu(maPhieuKham);
    }
    

    public boolean capNhat(KhamLamSang kls) {
        return dao.bsCanUpdateKhamLamSang(kls);
    }

    public boolean luuHoacCapNhat(KhamLamSang kls) {
     
        KhamLamSang tonTai = dao.getKhamLamSangByMaPhieu(kls.getMaPhieuKham());
        
        if (tonTai != null && tonTai.getMaPhieuKham() > 0) {

            return dao.bsCanUpdateKhamLamSang(kls);
        } else {

            return dao.themKhamLamSang(kls);
        }
    }
}