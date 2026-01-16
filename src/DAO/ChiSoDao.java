package DAO;

import MODEL.ChiSoTongQuat;
import MODEL.KhamLamSang;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ChiSoDao {
    private final String DB_URL = "jdbc:mysql://localhost:3306/dbphongkham";
    private final String USER = "root";
    private final String PASS = "";


    public boolean luuChiSo(ChiSoTongQuat cs) {
        String sql = "INSERT INTO chi_so_kham_tong_hop ("
            + "ma_phieu_kham, nhiet_do, nhip_tim, nhip_tho, huyet_ap_tam_thu, huyet_ap_tam_truong, "
            + "can_nang, chieu_cao, spo2, ghi_chu,ma_nhan_vien_nhap) "
            + "VALUES (?,?,?,?,?,?,?,?,?,?,?)";

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, cs.getMaPhieuKham());
            ps.setDouble(2, cs.getNhietDo());
            ps.setInt(3, cs.getNhipTim());
            ps.setInt(4, cs.getNhipTho());
            ps.setInt(5, cs.getHuyetApTamThu());
            ps.setInt(6, cs.getHuyetApTamTruong());
            ps.setDouble(7, cs.getCanNang());
            ps.setDouble(8, cs.getChieuCao());
            ps.setDouble(9, cs.getSpo2());
            ps.setString(10, cs.getGhiChu());
            ps.setInt(11, cs.getMaNhanVienThucHien());
            return ps.executeUpdate() > 0;
        } catch (Exception e) { e.printStackTrace(); return false; }
    }


    public boolean updateSinhHieu(ChiSoTongQuat cs, int id) {
        String sql = "UPDATE chi_so_kham_tong_hop SET "
            + "nhiet_do=?, nhip_tim=?, nhip_tho=?, huyet_ap_tam_thu=?, huyet_ap_tam_truong=?, "
            + "can_nang=?, chieu_cao=?, spo2=?, ghi_chu=? "
            + "WHERE id=?"; 
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setDouble(1, cs.getNhietDo());
            ps.setInt(2, cs.getNhipTim());
            ps.setInt(3, cs.getNhipTho());
            ps.setInt(4, cs.getHuyetApTamThu());
            ps.setInt(5, cs.getHuyetApTamTruong());
            ps.setDouble(6, cs.getCanNang());
            ps.setDouble(7, cs.getChieuCao());
            ps.setDouble(8, cs.getSpo2());
            ps.setString(9, cs.getGhiChu());
            ps.setInt(10, id); 
            return ps.executeUpdate() > 0;
        } catch (Exception e) { e.printStackTrace(); return false; }
    }


    public boolean updateNhiKhoa(ChiSoTongQuat cs, int id) {
        String sql = "UPDATE chi_so_kham_tong_hop SET "
            + "vong_dau=?, tinh_trang_dinh_duong=?, tam_ly_hanh_vi=?, "
            + "kham_tai_mui_hong_nhi=?, kham_ho_hap_nhi=?, kham_da_niem_mac_nhi=?, co_quan_khac_nhi=?, ghi_chu=? "
            + "WHERE id=?";
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setDouble(1, cs.getVongDau());
            ps.setString(2, cs.getTinhTrangDinhDuong());
            ps.setString(3, cs.getTamLyHanhVi());
            ps.setString(4, cs.getKhamTaiMuiHongNhi());
            ps.setString(5, cs.getKhamHoHapNhi());
            ps.setString(6, cs.getKhamDaNiemMacNhi());
            ps.setString(7, cs.getCoQuanKhacNhi());
            ps.setString(8, cs.getGhiChu());
            ps.setInt(9, id);
            return ps.executeUpdate() > 0;
        } catch (Exception e) { e.printStackTrace(); return false; }
    }


    public boolean updateRangHamMat(ChiSoTongQuat cs, int id) {
        String sql = "UPDATE chi_so_kham_tong_hop SET "
            + "tinh_trang_rang=?, sau_rang=?, cao_rang=?, viem_nuou=?, khop_can=?, "
            + "niem_mac_mieng=?, do_lung_lay=?, phu_hinh_cu=?, benh_ly_khac_rhm=?, ghi_chu=? "
            + "WHERE id=?";
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, cs.getTinhTrangRang());
            ps.setString(2, cs.getSauRang());
            ps.setString(3, cs.getCaoRang());
            ps.setString(4, cs.getViemNuou());
            ps.setString(5, cs.getKhopCan());
            ps.setString(6, cs.getNiemMacMieng());
            ps.setString(7, cs.getDoLungLay());
            ps.setString(8, cs.getPhuHinhCu());
            ps.setString(9, cs.getBenhLyKhacRHM());
            ps.setString(10, cs.getGhiChu());
            ps.setInt(11, id);
            return ps.executeUpdate() > 0;
        } catch (Exception e) { e.printStackTrace(); return false; }
    }


    public boolean updateTaiMuiHong(ChiSoTongQuat cs, int id) {
        String sql = "UPDATE chi_so_kham_tong_hop SET "
            + "thinh_luc_tai_trai=?, thinh_luc_tai_phai=?, tinh_trang_mui=?, tinh_trang_hong=?, "
            + "soi_tai_mui_hong=?, ong_tai=?, mang_nhi_phai=?, mang_nhi_trai=?, vach_ngan=?, "
            + "cuon_mui=?, khe_mui=?, amidan=?, thanh_quan=?, ghi_chu=? "
            + "WHERE id=?";
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, cs.getThinhLucTaiTrai());
            ps.setString(2, cs.getThinhLucTaiPhai());
            ps.setString(3, cs.getTinhTrangMui());
            ps.setString(4, cs.getTinhTrangHong());
            ps.setString(5, cs.getSoiTaiMuiHong());
            ps.setString(6, cs.getOngTai());
            ps.setString(7, cs.getMangNhiPhai());
            ps.setString(8, cs.getMangNhiTrai());
            ps.setString(9, cs.getVachNgan());
            ps.setString(10, cs.getCuonMui());
            ps.setString(11, cs.getKheMui());
            ps.setString(12, cs.getAmidan());
            ps.setString(13, cs.getThanhQuan());
            ps.setString(14, cs.getGhiChu());
            ps.setInt(15, id);
            return ps.executeUpdate() > 0;
        } catch (Exception e) { e.printStackTrace(); return false; }
    }


    public boolean updateTimMach(ChiSoTongQuat cs, int id) {
        String sql = "UPDATE chi_so_kham_tong_hop SET "
            + "cholesterol=?, hdl_cholesterol=?, ldl_cholesterol=?, triglyceride=?, "
            + "duong_huyet=?, ecg_ket_qua=?, sieu_am_tim=?, ghi_chu=? "
            + "WHERE id=?";
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setDouble(1, cs.getCholesterol());
            ps.setDouble(2, cs.getHdlCholesterol());
            ps.setDouble(3, cs.getLdlCholesterol());
            ps.setDouble(4, cs.getTriglyceride());
            ps.setDouble(5, cs.getDuongHuyet());
            ps.setString(6, cs.getEcgKetQua());
            ps.setString(7, cs.getSieuAmTim());
            ps.setString(8, cs.getGhiChu());
            ps.setInt(9, id);
            return ps.executeUpdate() > 0;
        } catch (Exception e) { e.printStackTrace(); return false; }
    }


    public List<ChiSoTongQuat> getallChiSo(int maPhieuKham) {
        List<ChiSoTongQuat> list = new ArrayList<>();
        String query = "SELECT * FROM chi_so_kham_tong_hop WHERE ma_phieu_kham = ? ORDER BY id DESC";
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, maPhieuKham);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                ChiSoTongQuat cs = new ChiSoTongQuat();
             
                cs.setId(rs.getInt("id"));
          
                cs.setMaPhieuKham(rs.getInt("ma_phieu_kham"));

                cs.setNhietDo(rs.getDouble("nhiet_do"));
                cs.setNhipTim(rs.getInt("nhip_tim"));
                cs.setNhipTho(rs.getInt("nhip_tho"));
                cs.setHuyetApTamThu(rs.getInt("huyet_ap_tam_thu"));
                cs.setHuyetApTamTruong(rs.getInt("huyet_ap_tam_truong"));
                cs.setCanNang(rs.getDouble("can_nang"));
                cs.setChieuCao(rs.getDouble("chieu_cao"));
                cs.setSpo2(rs.getDouble("spo2"));
       
                cs.setVongDau(rs.getDouble("vong_dau"));
                cs.setTinhTrangDinhDuong(rs.getString("tinh_trang_dinh_duong"));
                cs.setTamLyHanhVi(rs.getString("tam_ly_hanh_vi"));
                cs.setKhamTaiMuiHongNhi(rs.getString("kham_tai_mui_hong_nhi"));
                cs.setKhamHoHapNhi(rs.getString("kham_ho_hap_nhi"));
                cs.setKhamDaNiemMacNhi(rs.getString("kham_da_niem_mac_nhi"));
                cs.setCoQuanKhacNhi(rs.getString("co_quan_khac_nhi"));
     
                cs.setTinhTrangRang(rs.getString("tinh_trang_rang"));
                cs.setSauRang(rs.getString("sau_rang"));
                cs.setCaoRang(rs.getString("cao_rang"));
                cs.setViemNuou(rs.getString("viem_nuou"));
                cs.setKhopCan(rs.getString("khop_can"));
                cs.setNiemMacMieng(rs.getString("niem_mac_mieng"));
                cs.setDoLungLay(rs.getString("do_lung_lay"));
                cs.setPhuHinhCu(rs.getString("phu_hinh_cu"));
                cs.setBenhLyKhacRHM(rs.getString("benh_ly_khac_rhm"));

                cs.setThinhLucTaiTrai(rs.getString("thinh_luc_tai_trai"));
                cs.setThinhLucTaiPhai(rs.getString("thinh_luc_tai_phai"));
                cs.setTinhTrangMui(rs.getString("tinh_trang_mui"));
                cs.setTinhTrangHong(rs.getString("tinh_trang_hong"));
                cs.setSoiTaiMuiHong(rs.getString("soi_tai_mui_hong"));
                cs.setOngTai(rs.getString("ong_tai"));
                cs.setMangNhiPhai(rs.getString("mang_nhi_phai"));
                cs.setMangNhiTrai(rs.getString("mang_nhi_trai"));
                cs.setVachNgan(rs.getString("vach_ngan"));
                cs.setCuonMui(rs.getString("cuon_mui"));
                cs.setKheMui(rs.getString("khe_mui"));
                cs.setAmidan(rs.getString("amidan"));
                cs.setThanhQuan(rs.getString("thanh_quan"));

                cs.setCholesterol(rs.getDouble("cholesterol"));
                cs.setHdlCholesterol(rs.getDouble("hdl_cholesterol"));
                cs.setLdlCholesterol(rs.getDouble("ldl_cholesterol"));
                cs.setTriglyceride(rs.getDouble("triglyceride"));
                cs.setDuongHuyet(rs.getDouble("duong_huyet"));
                cs.setEcgKetQua(rs.getString("ecg_ket_qua"));
                cs.setSieuAmTim(rs.getString("sieu_am_tim"));
         
                cs.setGhiChu(rs.getString("ghi_chu"));
                Timestamp ts = rs.getTimestamp("ngay_tao");
                if(ts != null) cs.setNgayTao(ts.toLocalDateTime());

                list.add(cs);
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return list;
    }
    
    public ChiSoTongQuat getSinhHieuMoiNhatTrongNgay(int maBenhNhan) {
        ChiSoTongQuat obj = null;

        String sql = "SELECT cs.* FROM chi_so_kham_tong_hop cs " +
                     "JOIN phieu_kham pk ON cs.ma_phieu_kham = pk.ma_phieu_kham " +
                     "WHERE pk.ma_benh_nhan = ? AND DATE(pk.ngay_kham) = CURDATE() " +
                     "ORDER BY cs.id DESC LIMIT 1";

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, maBenhNhan);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                obj = new ChiSoTongQuat();
                obj.setNhietDo(rs.getDouble("nhiet_do"));
                obj.setHuyetApTamThu(rs.getInt("huyet_ap_tam_thu"));
                obj.setHuyetApTamTruong(rs.getInt("huyet_ap_tam_truong"));
                obj.setNhipTho(rs.getInt("nhip_tho"));
                obj.setNhipTim(rs.getInt("nhip_tim"));
                obj.setCanNang(rs.getDouble("can_nang"));
                obj.setChieuCao(rs.getDouble("chieu_cao"));
                obj.setSpo2(rs.getDouble("spo2"));
                obj.setGhiChu(rs.getString("ghi_chu"));
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return obj;
    }
    
    
    public KhamLamSang getKhamLamSangMoiNhatTrongNgay(int maBenhNhan) {
    	KhamLamSang obj = null;

        String sql = "SELECT cs.* FROM kham_lam_sang cs " +
                     "JOIN phieu_kham pk ON cs.ma_phieu_kham = pk.ma_phieu_kham " +
                     "WHERE pk.ma_benh_nhan = ? AND DATE(pk.ngay_kham) = CURDATE() " +
                     "ORDER BY cs.id DESC LIMIT 1";

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, maBenhNhan);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                obj = new KhamLamSang();
                obj.setLyDoKham(rs.getString("ly_do_kham"));
                obj.setTienSuBanThan(rs.getString("tien_su_ban_than"));
                obj.setBenhSu(rs.getString("benh_su"));

            }
        } catch (SQLException e) { e.printStackTrace(); }
        return obj;
    }
}