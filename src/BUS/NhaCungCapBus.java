package BUS;

import java.util.List;
import DAO.NhaCungCapDao;
import MODEL.NhaCungCap;

public class NhaCungCapBus {
    private NhaCungCapDao dao = new NhaCungCapDao();

    public List<NhaCungCap> getAllNCC() {
        return dao.getAllNCC();
    }

    public boolean themNCC(NhaCungCap ncc) {
       
        if (ncc.getTenNhaCungCap().isEmpty()) return false;
        return dao.themNCC(ncc);
    }
    public boolean isSDTExist(String sdt) {
    	return dao.isSDTExist(sdt);
    }

    public boolean suaNCC(NhaCungCap ncc) {
        return dao.suaNCC(ncc);
    }

    public int xoaNCC(int maNCC) {
        return dao.xoaNCC(maNCC);
    }

    public List<NhaCungCap> timKiemNCC(String keyword) {
        return dao.timKiemNCC(keyword);
    }
}