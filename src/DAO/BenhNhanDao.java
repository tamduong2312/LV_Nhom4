package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

import MODEL.BenhNhan;
import MODEL.NhanVien;


public class BenhNhanDao {
	
    private static final String DB_URL = "jdbc:mysql://localhost:3306/dbphongkham";
    private static final String USER = "root";
    private static final String PASS = "";


    public List<BenhNhan> getAllBenhNhan() {
        List<BenhNhan> list = new ArrayList<>();
        String query = "SELECT * FROM benh_nhan";

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement pstmt = conn.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
            	BenhNhan nv = new BenhNhan();
                nv.setMaBenhNhan(rs.getInt("ma_benh_nhan"));
                nv.setHoTen(rs.getString("ho_ten"));
                nv.setGT(rs.getBoolean("gioi_tinh"));
                nv.setNgaySinh(rs.getDate("ngay_sinh").toLocalDate());
                nv.setCCCD(rs.getLong("cccd"));
                nv.setDiaChi(rs.getString("dia_chi"));
                nv.setSDT(rs.getString("so_dien_thoai"));
                nv.setEmail(rs.getString("email"));
                nv.setNgheNghiep(rs.getString("nghe_nghiep"));
                nv.setNhomMau(rs.getString("nhom_mau"));
                nv.setDiUngThuoc(rs.getString("di_ung_thuoc"));
                nv.setNguoiGiamHo(rs.getString("nguoi_giam_ho"));
                nv.setSDTNguoiGiamHo(rs.getLong("so_dien_thoai_nguoi_giam_ho"));
                nv.setGhiChu(rs.getString("ghi_chu"));
                nv.setCCCD(rs.getLong("cccd"));

     
                list.add(nv);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }
    
    
    public BenhNhan getBenhNhanById(int id) {
        String query = "SELECT * FROM benh_nhan WHERE ma_benh_nhan = ?";
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setInt(1, id);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    BenhNhan bn = new BenhNhan();
                    bn.setMaBenhNhan(rs.getInt("ma_benh_nhan"));
                    bn.setHoTen(rs.getString("ho_ten"));
                    bn.setGT(rs.getBoolean("gioi_tinh"));
                    bn.setNgaySinh(rs.getDate("ngay_sinh").toLocalDate());
                    bn.setCCCD(rs.getLong("cccd"));
                    bn.setDiaChi(rs.getString("dia_chi"));
                    bn.setSDT(rs.getString("so_dien_thoai"));
                    bn.setEmail(rs.getString("email"));
                    bn.setNgheNghiep(rs.getString("nghe_nghiep"));
                    bn.setNhomMau(rs.getString("nhom_mau"));
                    bn.setDiUngThuoc(rs.getString("di_ung_thuoc"));
                    bn.setNguoiGiamHo(rs.getString("nguoi_giam_ho"));
                    bn.setSDTNguoiGiamHo(rs.getLong("so_dien_thoai_nguoi_giam_ho"));
                    bn.setGhiChu(rs.getString("ghi_chu"));
                    return bn;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    
    public int themBenhNhan(BenhNhan nv) {
        int success = 0;
        String query = "INSERT INTO `benh_nhan`(`ho_ten`, `ngay_sinh`, `dia_chi`, `so_dien_thoai`, `email`, `nghe_nghiep`, `nhom_mau`, `di_ung_thuoc`, `nguoi_giam_ho`, `so_dien_thoai_nguoi_giam_ho`, `ghi_chu`, `cccd`, `gioi_tinh`) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, nv.getHoTen());
            pstmt.setDate(2, java.sql.Date.valueOf(nv.getNgaySinh())); 
            pstmt.setString(3, nv.getDiaChi());
            pstmt.setString(4, nv.getSDT());
            if (nv.getEmail() == null || nv.getEmail().trim().isEmpty()) {
            	pstmt.setNull(5, java.sql.Types.VARCHAR);
            } else {
            	pstmt.setString(5, nv.getEmail());
            }
            pstmt.setString(6, nv.getNgheNghiep());
            pstmt.setString(7, nv.getNhomMau());
            pstmt.setString(8, nv.getDiUngThuoc());
            pstmt.setString(9, nv.getNguoiGiamHo());
            pstmt.setLong(10, nv.getSDTNguoiGiamHo());
            pstmt.setString(11, nv.getGhiChu());
            pstmt.setLong(12, nv.getCCCD());
            pstmt.setBoolean(13, nv.getGT());
         

      

            success = pstmt.executeUpdate();


        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
  
        }
		return success;
    }
    public int xoaBN(int id) {
        int result = 0;
        String query = "DELETE FROM benh_nhan WHERE ma_benh_nhan = ?";

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement pstm = conn.prepareStatement(query)) {
            
            pstm.setInt(1, id);
            
            int rows = pstm.executeUpdate();
            if (rows > 0) {
                result = 1; 
            }

        } catch (SQLIntegrityConstraintViolationException  e) {

            System.err.println("Không thể xóa bệnh nhân do có dữ liệu liên quan: " + e.getMessage());
            result = -1; 
        } catch (SQLException e) {
            e.printStackTrace();
            result = 0; 
        }
        
        return result;
    }

    
    public int suaBN(BenhNhan nv, int id) {
        int success = 0;
        String query = "UPDATE benh_nhan SET ho_ten = ?, ngay_sinh = ?, dia_chi = ?, so_dien_thoai = ?, email = ?, " +
                       "nghe_nghiep = ?, nhom_mau = ?, di_ung_thuoc = ?, nguoi_giam_ho = ?, so_dien_thoai_nguoi_giam_ho = ?, " +
                       "ghi_chu = ?, cccd = ?, gioi_tinh = ? WHERE ma_benh_nhan = ?";

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement pstm = conn.prepareStatement(query)) {

            pstm.setString(1, nv.getHoTen());
            pstm.setDate(2, java.sql.Date.valueOf(nv.getNgaySinh()));
            pstm.setString(3, nv.getDiaChi());
            pstm.setString(4, nv.getSDT());
            if (nv.getEmail() == null || nv.getEmail().trim().isEmpty()) {
                pstm.setNull(5, java.sql.Types.VARCHAR);
            } else {
                pstm.setString(5, nv.getEmail());
            }
            pstm.setString(6, nv.getNgheNghiep());
            pstm.setString(7, nv.getNhomMau());
            pstm.setString(8, nv.getDiUngThuoc());
            pstm.setString(9, nv.getNguoiGiamHo());
            pstm.setLong(10, nv.getSDTNguoiGiamHo());
            pstm.setString(11, nv.getGhiChu());
            pstm.setLong(12, nv.getCCCD());
            pstm.setBoolean(13, nv.getGT());

            pstm.setInt(14, id);

            success = pstm.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }

        return success;
    }
    public List<BenhNhan> TimKiemBenhNhan(String keyword) {
        List<BenhNhan> list = new ArrayList<>();

        String query = """
                SELECT * FROM benh_nhan
                WHERE CAST(cccd AS CHAR) LIKE ?
                   OR ma_benh_nhan LIKE ?
                   OR ho_ten LIKE ?
                   OR dia_chi LIKE ?
                   OR CAST(so_dien_thoai AS CHAR) LIKE ?
                   OR email LIKE ?
                   OR nghe_nghiep LIKE ?
                   OR nhom_mau LIKE ?
                   OR di_ung_thuoc LIKE ?
                   OR nguoi_giam_ho LIKE ?
                   OR CAST(so_dien_thoai_nguoi_giam_ho AS CHAR) LIKE ?
                   OR ghi_chu LIKE ?
                   OR CAST(ngay_sinh AS CHAR) LIKE ?
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
            pstmt.setString(11, value); 
            pstmt.setString(12, value); 
            pstmt.setString(13, value);
            pstmt.setString(14, value);      
            pstmt.setString(15, value);  

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {

                    BenhNhan bn = new BenhNhan();
                    bn.setMaBenhNhan(rs.getInt("ma_benh_nhan"));
                    bn.setHoTen(rs.getString("ho_ten"));
                    bn.setGT(rs.getBoolean("gioi_tinh"));
                    bn.setNgaySinh(rs.getDate("ngay_sinh").toLocalDate());
                    bn.setDiaChi(rs.getString("dia_chi"));
                    bn.setSDT(rs.getString("so_dien_thoai"));
                    bn.setEmail(rs.getString("email"));
                    bn.setNgheNghiep(rs.getString("nghe_nghiep"));
                    bn.setNhomMau(rs.getString("nhom_mau"));
                    bn.setDiUngThuoc(rs.getString("di_ung_thuoc"));
                    bn.setNguoiGiamHo(rs.getString("nguoi_giam_ho"));
                    bn.setSDTNguoiGiamHo(rs.getLong("so_dien_thoai_nguoi_giam_ho"));
                    bn.setGhiChu(rs.getString("ghi_chu"));
                    bn.setCCCD(rs.getLong("cccd"));

                    list.add(bn);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }

        return list;
    }


    public String getTenBenhNhanById(int maBenhNhan) {
        String tenBenhNhan = "Không xác định";
        
    
        String sql = "SELECT ho_ten FROM benh_nhan WHERE ma_benh_nhan = ?";

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, maBenhNhan);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
       
                tenBenhNhan = rs.getString("ho_ten");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return tenBenhNhan;
    }
}
