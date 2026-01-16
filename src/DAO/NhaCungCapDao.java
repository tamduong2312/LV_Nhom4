package DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import MODEL.NhaCungCap;

public class NhaCungCapDao {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/dbphongkham";
    private static final String USER = "root";
    private static final String PASS = "";

  
    public List<NhaCungCap> getAllNCC() {
        List<NhaCungCap> list = new ArrayList<>();
        String query = "SELECT * FROM nha_cung_cap";
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement pstm = conn.prepareStatement(query);
             ResultSet rs = pstm.executeQuery()) {
            while (rs.next()) {
                list.add(mapRow(rs));
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return list;
    }



    public boolean themNCC(NhaCungCap t) {
        String sql = "INSERT INTO nha_cung_cap (ten_nha_cung_cap, dia_chi, so_dien_thoai, Email, ghi_chu) VALUES (?,?,?,?,?)";
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, t.getTenNhaCungCap());
            ps.setString(2, t.getDiaChi());
            ps.setLong(3, t.getSDT());
            ps.setString(4, t.getEmail());
            ps.setString(5, t.getGhichu());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
        	e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }
    
    public boolean isSDTExist(String sdt) {

        String sql = "SELECT COUNT(*) FROM nha_cung_cap WHERE so_dien_thoai = ?";
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setString(1, sdt);
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
          
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean suaNCC(NhaCungCap t) {
        String sql = "UPDATE nha_cung_cap SET ten_nha_cung_cap=?, dia_chi=?, so_dien_thoai=?, Email=?, ghi_chu=? WHERE ma_nha_cung_cap=?";
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, t.getTenNhaCungCap());
            ps.setString(2, t.getDiaChi());
            ps.setLong(3, t.getSDT());
            ps.setString(4, t.getEmail());
            ps.setString(5, t.getGhichu());
            ps.setInt(6, t.getMaNhaCungCap());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) { 
        	e.printStackTrace();
        	  throw new RuntimeException(e.getMessage());
        	
        }
    }

    public int xoaNCC(int ma) {
        String sql = "DELETE FROM nha_cung_cap WHERE ma_nha_cung_cap=?";
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setInt(1, ma);
            int rows = ps.executeUpdate();
            return rows > 0 ? 1 : 0; 

        } catch (java.sql.SQLIntegrityConstraintViolationException e) {
  
            System.err.println("Lỗi ràng buộc khóa ngoại: " + e.getMessage());
            return -1; 
        } catch (SQLException e) {
            e.printStackTrace();
            return 0; 
        }
    }

    public List<NhaCungCap> timKiemNCC(String keyword) {
        List<NhaCungCap> list = new ArrayList<>();
        String sql = "SELECT * FROM nha_cung_cap WHERE ten_nha_cung_cap LIKE ? OR so_dien_thoai LIKE ?";
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement ps = conn.prepareStatement(sql)) {
            String key = "%" + keyword + "%";
            ps.setString(1, key);
            ps.setString(2, key);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) list.add(mapRow(rs));
        } catch (SQLException e) { e.printStackTrace(); }
        return list;
    }

    private NhaCungCap mapRow(ResultSet rs) throws SQLException {
        NhaCungCap t = new NhaCungCap();
        t.setMaNhaCungCap(rs.getInt("ma_nha_cung_cap"));
        t.setTenNhaCungCap(rs.getString("ten_nha_cung_cap"));
        t.setDiaChi(rs.getString("dia_chi"));
        t.setSDT(rs.getLong("so_dien_thoai"));
        t.setEmail(rs.getString("Email"));
        t.setGhichu(rs.getString("ghi_chu"));
        return t;
    }
}