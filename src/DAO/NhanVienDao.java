package DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import MODEL.NhanVien;


public class NhanVienDao {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/dbphongkham";
    private static final String USER = "root";
    private static final String PASS = "";


    public List<NhanVien> getAllNhanVien() {
        List<NhanVien> list = new ArrayList<>();
        String query = "SELECT * FROM nhan_vien";

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement pstmt = conn.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                NhanVien nv = new NhanVien();
                nv.setMaNV(rs.getInt("ma_nhan_vien"));
                nv.setHoTen(rs.getString("ho_ten"));
                nv.setGT(rs.getBoolean("gioi_tinh"));
                nv.setNgay_sinh(rs.getDate("ngay_sinh").toLocalDate());
                nv.setCCCD(rs.getLong("cccd"));
                nv.setDiaChi(rs.getString("dia_chi"));
                nv.setSDT(rs.getString("so_dien_thoai"));
                nv.setEmail(rs.getString("email"));
                nv.setBangCap(rs.getString("bang_cap"));
                nv.setChucVu(rs.getString("chuc_vu"));
                nv.setMaChuyenKhoa(rs.getString("chuyen_khoa"));
                nv.setNgayVaoLam(rs.getDate("ngay_vao_lam").toLocalDate());

//               nv.setMaNguoiDung(rs.getInt("ma_nguoi_dung"));
                //nv.setRole(Role.valueOf(rs.getString("vai_tro")));
     
                list.add(nv);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }
   

    
    public int themNhanVien(NhanVien nv) {
        int success = 0;
        String query = "INSERT INTO nhan_vien (" +
                "ho_ten, gioi_tinh, ngay_sinh, cccd, dia_chi, so_dien_thoai, " +
                "email, bang_cap, chuc_vu, chuyen_khoa, ngay_vao_lam)" +
      
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, nv.getHoTen());
            pstmt.setBoolean(2, nv.getGT());
            pstmt.setDate(3, java.sql.Date.valueOf(nv.getNgay_sinh())); 
            pstmt.setLong(4, nv.getCCCD());
            pstmt.setString(5, nv.getDiaChi());
            pstmt.setString(6, nv.getSDT());
            pstmt.setString(7, nv.getEmail());
            pstmt.setString(8, nv.getBangCap());
            pstmt.setString(9, nv.getChucVu());
            pstmt.setString(10, nv.getMaChuyenKhoa());
            pstmt.setDate(11, java.sql.Date.valueOf(nv.getNgayVaoLam()));
            //pstmt.setInt(12, nv.getMaNguoiDung());

            //pstmt.setString(14, nv.getRole().name()); 

            success = pstmt.executeUpdate();


        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
		return success;
    }
    
    
    
    public int xoanv(int id) {
        int result = 0;
        String query = "DELETE FROM nhan_vien WHERE ma_nhan_vien = ?"; 

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement pstm = conn.prepareStatement(query)) {
           
            pstm.setInt(1, id);
            
      
            int rows = pstm.executeUpdate();
            
            if (rows > 0) {
                result = 1; 
            } else {
                result = 0; 
            }

        } catch (SQLIntegrityConstraintViolationException e) {

            result = -1; 
            System.err.println("Lỗi ràng buộc khóa ngoại: " + e.getMessage());
            
        } catch (SQLException e) {
            e.printStackTrace();
            result = 0; 
        }
        
        return result;
    }

    public boolean checkTrungSDT(String sdt, int idHienTai) {

        String sql = "SELECT COUNT(*) FROM nhan_vien WHERE so_dien_thoai = ? AND ma_nhan_vien != ?";
        
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setString(1, sdt);
            ps.setInt(2, idHienTai);
            
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public boolean checkTrungSDT1(String sdt) {

        String sql = "SELECT COUNT(*) FROM nhan_vien WHERE so_dien_thoai = ?";
        
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setString(1, sdt);

            
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    public int suaNV(NhanVien nv, int id1) {
        int success = 0;
        String query = "UPDATE nhan_vien SET ho_ten = ?, gioi_tinh = ?, ngay_sinh = ?, cccd = ?, dia_chi = ?, so_dien_thoai = ?, " +
                       "email = ?, bang_cap = ?, chuc_vu = ?, chuyen_khoa = ?, ngay_vao_lam = ? " +
                       "WHERE ma_nhan_vien = ?";

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement pstm = conn.prepareStatement(query)) {

            pstm.setString(1, nv.getHoTen());
            pstm.setBoolean(2, nv.getGT());
            pstm.setDate(3, java.sql.Date.valueOf(nv.getNgay_sinh()));
            pstm.setLong(4, nv.getCCCD());
            pstm.setString(5, nv.getDiaChi());

            pstm.setString(6, nv.getSDT()); 

            pstm.setString(7, nv.getEmail());
            pstm.setString(8, nv.getBangCap());
            pstm.setString(9, nv.getChucVu());
            pstm.setString(10, nv.getMaChuyenKhoa());
            pstm.setDate(11, java.sql.Date.valueOf(nv.getNgayVaoLam()));
            //pstm.setInt(12, nv.getMaNguoiDung());
            //pstm.setString(13, nv.getRole().name());
     
            pstm.setInt(12, id1);

            success = pstm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
       	  throw new RuntimeException(e.getMessage());
        }
        return success;
    }

    
    public List<NhanVien> TimkiemNhanVien(String keyword) {
        List<NhanVien> list = new ArrayList<>();
        String query = """
                SELECT * FROM nhan_vien 
                WHERE CAST(cccd AS CHAR) LIKE ?
                   OR ho_ten LIKE ?
                   OR dia_chi LIKE ?
                   OR CAST(so_dien_thoai AS CHAR) LIKE ?
                   OR email LIKE ?
                   OR chuc_vu LIKE ?
                   OR bang_cap LIKE ?
                   OR chuyen_khoa LIKE ?
         
             OR (gioi_tinh = TRUE AND 'nam' LIKE ?)
              OR (gioi_tinh = FALSE AND 'nữ' LIKE ?)
            """;

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            String value = "%" + keyword.trim() + "%";
            pstmt.setString(1, value);
            pstmt.setString(2, value);
            pstmt.setString(3, value);
            pstmt.setString(4, value);
            pstmt.setString(5, value);
            pstmt.setString(6, value);
            pstmt.setString(7, value);
            pstmt.setString(8, value);
            pstmt.setString(9, value);
            pstmt.setString(10, value);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    NhanVien nv = new NhanVien();
                    nv.setMaNV(rs.getInt("ma_nhan_vien"));
                    nv.setHoTen(rs.getString("ho_ten"));
                    nv.setGT(rs.getBoolean("gioi_tinh"));
                    nv.setNgay_sinh(rs.getDate("ngay_sinh").toLocalDate());
                    nv.setCCCD(rs.getLong("cccd"));
                    nv.setDiaChi(rs.getString("dia_chi"));
                    nv.setSDT(rs.getString("so_dien_thoai"));
                    nv.setEmail(rs.getString("email"));
                    nv.setBangCap(rs.getString("bang_cap"));
                    nv.setChucVu(rs.getString("chuc_vu"));
                    nv.setMaChuyenKhoa(rs.getString("chuyen_khoa"));
                    nv.setNgayVaoLam(rs.getDate("ngay_vao_lam").toLocalDate());
           
   

                    list.add(nv);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }


    
}
