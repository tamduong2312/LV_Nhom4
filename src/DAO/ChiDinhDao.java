package DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import MODEL.PhieuChiDinh;
import MODEL.ChiTietChiDinh;
import MODEL.DanhSachChoCLS;
import MODEL.KetQuaCDHA;

public class ChiDinhDao {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/dbphongkham";
    private static final String USER = "root";
    private static final String PASS = "";



    public int taoPhieuChiDinh(int maPhieuKham, int maNguoiChiDinh) {
        int generatedKey = -1;

        String sql = "INSERT INTO phieu_chi_dinh (ma_phieu_kham, ma_nhan_vien_chi_dinh, ngay_chi_dinh, tong_tien, trang_thai) "
                   + "VALUES (?, ?, NOW(), 0, 'CHUA_THANH_TOAN')";
        
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement pstm = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            pstm.setInt(1, maPhieuKham);
            pstm.setInt(2, maNguoiChiDinh);
            
            if (pstm.executeUpdate() > 0) {
                ResultSet rs = pstm.getGeneratedKeys();
                if (rs.next()) generatedKey = rs.getInt(1); 
            }
        } catch (Exception e) { e.printStackTrace(); }
        return generatedKey;
    }


    public boolean themChiTietDichVu(ChiTietChiDinh ct) {
        String sql = "INSERT INTO chi_tiet_chi_dinh (ma_phieu_chi_dinh, ma_dich_vu, so_luong, don_gia, trang_thai_dv) "
                   + "VALUES (?, ?, ?, ?, 'CHUA_THUC_HIEN')";
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement pstm = conn.prepareStatement(sql)) {
            
            pstm.setInt(1, ct.getMaPhieuChiDinh());
            pstm.setInt(2, ct.getMaDichVu());
            pstm.setInt(3, ct.getSoLuong());
            pstm.setDouble(4, ct.getDonGia());
            
            return pstm.executeUpdate() > 0;
        } catch (Exception e) { e.printStackTrace(); return false; }
    }


    public List<ChiTietChiDinh> getChiTietByMaPhieu(int maPhieuChiDinh) {
        List<ChiTietChiDinh> list = new ArrayList<>();

        String sql = "SELECT ct.*, dv.ten_dich_vu "
                   + "FROM chi_tiet_chi_dinh ct "
                   + "JOIN dich_vu dv ON ct.ma_dich_vu = dv.ma_dich_vu "
                   + "WHERE ct.ma_phieu_chi_dinh = ?";
                   
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement pstm = conn.prepareStatement(sql)) {
            pstm.setInt(1, maPhieuChiDinh);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                ChiTietChiDinh ct = new ChiTietChiDinh();
                ct.setId(rs.getInt("id")); 
                ct.setMaDichVu(rs.getInt("ma_dich_vu")); 
                ct.setSoLuong(rs.getInt("so_luong"));
                ct.setDonGia(rs.getDouble("don_gia"));
                // ... các cột khác
                list.add(ct);
            }
        } catch (Exception e) { e.printStackTrace(); }
        return list;
    }

    //  Hàm cập nhật lại tổng tiền cho phiếu chỉ định 
    public int capNhatTongTienPhieu(int maPhieuChiDinh) {
    	
    	int kq = 0;
        String sql = "UPDATE phieu_chi_dinh p "
                   + "SET tong_tien = (SELECT SUM(so_luong * don_gia) FROM chi_tiet_chi_dinh WHERE ma_phieu_chi_dinh = ?) "
                   + "WHERE ma_phieu_chi_dinh = ?";
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement pstm = conn.prepareStatement(sql)) {
            pstm.setInt(1, maPhieuChiDinh);
            pstm.setInt(2, maPhieuChiDinh);
            pstm.executeUpdate();
        } catch (Exception e)  { 
        	e.printStackTrace(); }
        return kq;
    }
    

    public boolean xoaChiTietDichVu(int idChiTiet) {

        String sql = "DELETE FROM chi_tiet_chi_dinh WHERE id = ?";
        
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement pstm = conn.prepareStatement(sql)) {
            
            pstm.setInt(1, idChiTiet);

            int rowsAffected = pstm.executeUpdate();
            
  
            return rowsAffected > 0;
            
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    

    public int getMaPhieuChiDinhByMaPhieuKham(int maPhieuKham) {
        int maPhieuChiDinh = -1; 
        

        String sql = "SELECT ma_phieu_chi_dinh FROM phieu_chi_dinh "
                   + "WHERE ma_phieu_kham = ? "
                   + "ORDER BY ma_phieu_chi_dinh DESC LIMIT 1";

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement pstm = conn.prepareStatement(sql)) {

            pstm.setInt(1, maPhieuKham);

            try (ResultSet rs = pstm.executeQuery()) {
                if (rs.next()) {
                    maPhieuChiDinh = rs.getInt("ma_phieu_chi_dinh");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return maPhieuChiDinh;
    }
    

    public List<DanhSachChoCLS> getDanhSachChoCLS() {
        List<DanhSachChoCLS> list = new ArrayList<>();
        
   
        String sql = "SELECT * FROM View_DanhSachChoCLS "
                + "WHERE (trang_thai_dv = 'CHUA_THUC_HIEN' OR trang_thai_dv = 'DA_CO_KET_QUA')  AND DATE(ngay_chi_dinh) = CURDATE()"
                + "ORDER BY ngay_chi_dinh ASC";

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement pstm = conn.prepareStatement(sql);
             ResultSet rs = pstm.executeQuery()) {

            while (rs.next()) {
                DanhSachChoCLS item = new DanhSachChoCLS();
                item.setMaChiTietChiDinh(rs.getInt("ma_chi_tiet"));
                item.setMaPhieuChiDinh(rs.getInt("ma_phieu_chi_dinh"));
                item.setMaBenhNhan(rs.getInt("ma_benh_nhan"));
                item.setTenBenhNhan(rs.getString("ten_benh_nhan"));
                item.setNamSinh(rs.getInt("nam_sinh"));
                

                boolean gt = rs.getBoolean("gioi_tinh");
                item.setGioiTinh(gt ? "Nam" : "Nữ");
                
                item.setTenDichVu(rs.getString("ten_dich_vu"));
                item.setBacSiChiDinh(rs.getString("bac_si_chi_dinh"));
                item.setTrangThai(rs.getString("trang_thai_dv"));
                item.setLoaiDichVu(rs.getString("loai_dich_vu"));
                item.setNgayChiDinh(rs.getTimestamp("ngay_chi_dinh"));
                
                list.add(item);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    

    public boolean luuKetQuaCDHAMoi(int idChiTiet, String moTa, String ketLuan, String deNghi, 
                                    int maBacSi, String anh1, String anh2) {
        Connection conn = null;
        PreparedStatement pstmInsert = null;
        PreparedStatement pstmUpdate = null;

        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            conn.setAutoCommit(false); 

     
            String sqlInsert = "INSERT INTO ket_qua_cdha "
                             + "(id_chi_tiet_chi_dinh, mo_ta_hinh_anh, ket_luan, de_nghi, "
                             + "duong_dan_anh_1, duong_dan_anh_2, ma_bac_si_thuc_hien, ngay_thuc_hien) "
                             + "VALUES (?, ?, ?, ?, ?, ?, ?, NOW())";
            
            pstmInsert = conn.prepareStatement(sqlInsert);
            pstmInsert.setInt(1, idChiTiet);
            pstmInsert.setString(2, moTa);
            pstmInsert.setString(3, ketLuan);
            pstmInsert.setString(4, deNghi); 
            
    
            if (anh1 == null || anh1.isEmpty()) pstmInsert.setNull(5, java.sql.Types.VARCHAR);
            else pstmInsert.setString(5, anh1);
            
     
            if (anh2 == null || anh2.isEmpty()) pstmInsert.setNull(6, java.sql.Types.VARCHAR);
            else pstmInsert.setString(6, anh2);
            
            pstmInsert.setInt(7, maBacSi);
            
            pstmInsert.executeUpdate();

        
            String sqlUpdate = "UPDATE chi_tiet_chi_dinh SET trang_thai_dv = 'DA_CO_KET_QUA' WHERE id = ?";
            pstmUpdate = conn.prepareStatement(sqlUpdate);
            pstmUpdate.setInt(1, idChiTiet);
            
            pstmUpdate.executeUpdate();

            conn.commit();
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            try { if (conn != null) conn.rollback(); } catch (SQLException ex) {}
            return false;
        } finally {
            try {
                if (pstmInsert != null) pstmInsert.close();
                if (pstmUpdate != null) pstmUpdate.close();
                if (conn != null) conn.close();
            } catch (Exception e) {}
        }
    }
    
    

    public KetQuaCDHA layKetQuaTheoIdChiTiet(int idChiTiet) {
        KetQuaCDHA kq = null;
        String sql = "SELECT * FROM ket_qua_cdha WHERE id_chi_tiet_chi_dinh = ?";
        
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement pstm = conn.prepareStatement(sql)) {
            
            pstm.setInt(1, idChiTiet);
            ResultSet rs = pstm.executeQuery();
            
            if (rs.next()) {
                kq = new KetQuaCDHA();
                kq.setId(rs.getInt("id"));
                kq.setMoTa(rs.getString("mo_ta_hinh_anh"));
                kq.setKetLuan(rs.getString("ket_luan"));
                kq.setDeNghi(rs.getString("de_nghi"));
                kq.setAnh1(rs.getString("duong_dan_anh_1"));
                kq.setAnh2(rs.getString("duong_dan_anh_2"));
                kq.setMaBacSi(rs.getInt("ma_bac_si_thuc_hien"));
                kq.setNgayThucHien(rs.getTimestamp("ngay_thuc_hien"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return kq;
    }

    
    public KetQuaCDHA layKetQuaTheoId(int idChiTiet) {
        KetQuaCDHA kq = null;
        String sql = "SELECT * FROM ket_qua_cdha WHERE id = ?";
        
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement pstm = conn.prepareStatement(sql)) {
            
            pstm.setInt(1, idChiTiet);
            ResultSet rs = pstm.executeQuery();
            
            if (rs.next()) {
                kq = new KetQuaCDHA();
                kq.setId(rs.getInt("id"));
                kq.setMoTa(rs.getString("mo_ta_hinh_anh"));
                kq.setKetLuan(rs.getString("ket_luan"));
                kq.setDeNghi(rs.getString("de_nghi"));
                kq.setAnh1(rs.getString("duong_dan_anh_1"));
                kq.setAnh2(rs.getString("duong_dan_anh_2"));
                kq.setMaBacSi(rs.getInt("ma_bac_si_thuc_hien"));
                kq.setNgayThucHien(rs.getTimestamp("ngay_thuc_hien"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return kq;
    }
    
    
    


    public DanhSachChoCLS getThongTinTuMaKetQua(int maKetQua) {
        DanhSachChoCLS info = new DanhSachChoCLS();

        String sql = """
            SELECT 
                bn.ho_ten AS ten_benh_nhan,
                dv.ten_dich_vu
            FROM 
                ket_qua_cdha kq
            INNER JOIN 
                chi_tiet_chi_dinh ct ON kq.id_chi_tiet_chi_dinh = ct.id
            INNER JOIN 
                dich_vu dv ON ct.ma_dich_vu = dv.ma_dich_vu
            INNER JOIN 
                phieu_chi_dinh pcd ON ct.ma_phieu_chi_dinh = pcd.ma_phieu_chi_dinh
            INNER JOIN 
                phieu_kham pk ON pcd.ma_phieu_kham = pk.ma_phieu_kham
            INNER JOIN 
                benh_nhan bn ON pk.ma_benh_nhan = bn.ma_benh_nhan
            WHERE 
                kq.id = ?  
        """;

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement ps = conn.prepareStatement(sql)) {
             
            ps.setInt(1, maKetQua);
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                info.setTenBenhNhan(rs.getString("ten_benh_nhan"));
                info.setTenDichVu(rs.getString("ten_dich_vu"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return info;
    }
    

    public boolean capNhatKetQuaDaCo(int idChiTiet, String moTa, String ketLuan, String deNghi, 
                                     int maBacSi, String anh1, String anh2) {
        String sql = "UPDATE ket_qua_cdha SET "
                   + "mo_ta_hinh_anh = ?, ket_luan = ?, de_nghi = ?, "
                   + "duong_dan_anh_1 = ?, duong_dan_anh_2 = ?, "
                   + "ma_bac_si_thuc_hien = ?, ngay_thuc_hien = NOW() "
                   + "WHERE id_chi_tiet_chi_dinh = ?";
        
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement pstm = conn.prepareStatement(sql)) {
            
            pstm.setString(1, moTa);
            pstm.setString(2, ketLuan);
            pstm.setString(3, deNghi);
            
            if (anh1 == null) pstm.setNull(4, java.sql.Types.VARCHAR);
            else pstm.setString(4, anh1);
            
            if (anh2 == null) pstm.setNull(5, java.sql.Types.VARCHAR);
            else pstm.setString(5, anh2);
            
            pstm.setInt(6, maBacSi);
            pstm.setInt(7, idChiTiet); 
            
            return pstm.executeUpdate() > 0;
            
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}