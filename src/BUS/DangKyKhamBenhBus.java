package BUS;

import java.util.List;

import DAO.DangKyKhamBenhDao;
import MODEL.DangKyKhamBenh;

public class DangKyKhamBenhBus {

    private DangKyKhamBenhDao dkDao = new DangKyKhamBenhDao();


    public List<DangKyKhamBenh> getAll() {
        return dkDao.getAll();
    }


    public int themDangKy(DangKyKhamBenh dk) {
        return dkDao.themDangKy(dk);
    }


    public int capNhatTrangThai(int id, String trangThai) {
        return dkDao.capNhatTrangThai(id, trangThai);
    }

 
    public int xoaDangKy(int id) {
        return dkDao.xoaDangKy(id);
    }


    public List<DangKyKhamBenh> timKiem(String keyword) {
        return dkDao.timKiem(keyword);
    }


    public int laySoThuTuTiepTheo(int maChuyenKhoa) {
        return dkDao.laySoThuTuTiepTheo(maChuyenKhoa);
    }
}
