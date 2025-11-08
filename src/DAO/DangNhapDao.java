package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import MODEL.NguoiDung;
import MODEL.NhanVien;
import MODEL.NhanVien.Role;
import MODEL.PhanQuyenLogin;

public class DangNhapDao {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/dbphongkham";
    private static final String USER = "root";
    private static final String PASS = "12345";


//    public String login(String tenDangNhap, String matKhau) {
//        String role = null;
//        String query = " SELECT nv.vai_tro FROM nguoi_dung nd "
//        		+ "JOIN nhan_vien nv ON nd.ma_nguoi_dung = nv.ma_nguoi_dung"
//        		+ " WHERE nd.ten_dang_nhap = ? AND nd.mat_khau = ?";
//
//
//        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
//             PreparedStatement pstmt = conn.prepareStatement(query)) {
//
//            pstmt.setString(1, tenDangNhap);
//            pstmt.setString(2, matKhau);
//
//            try (ResultSet rs = pstmt.executeQuery()) {
//                if (rs.next()) {
//                    role = rs.getString("vai_tro");
//                }
//            }
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        return role; 
//    }
    public PhanQuyenLogin BSlogin(String tenDangNhap, String matKhau) {
        PhanQuyenLogin result = null;

        String query = "SELECT nv.ma_nhan_vien, nv.vai_tro, nv.chuyen_khoa FROM nguoi_dung nd "
                     + "JOIN nhan_vien nv ON nd.ma_nguoi_dung = nv.ma_nguoi_dung "
                     + "WHERE nd.ten_dang_nhap = ? AND nd.mat_khau = ?";

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, tenDangNhap);
            pstmt.setString(2, matKhau);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    int maNV   = rs.getInt("ma_nhan_vien");
                    String role = rs.getString("vai_tro");
                    String ck   = rs.getString("chuyen_khoa");

                    result = new PhanQuyenLogin(maNV, role, ck);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }



    public List<NguoiDung> getalltk() {
        List<NguoiDung> list = new ArrayList<>();
        String query = "SELECT ma_nguoi_dung , ten_dang_nhap,mat_khau FROM nguoi_dung";

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement pstmt = conn.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
            	NguoiDung nv = new NguoiDung();
                nv.setMaNguoiDung(rs.getInt("ma_nguoi_dung"));
                nv.setTenDangNhap(rs.getString("ten_dang_nhap"));
                nv.setMatKhau(rs.getString("mat_khau"));
      
     
                list.add(nv);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    public int themTK(NguoiDung nv) {
        int success = 0;
        String query = "INSERT INTO nguoi_dung(ten_dang_nhap, mat_khau) VALUES (?, ?)";

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, nv.getTenDangNhap());
            pstmt.setString(2, nv.getMatKhau());
     

            success = pstmt.executeUpdate();


        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
		return success;
    }
    
    public int xoatk(int id) {
        int success = 0;
        String query = "DELETE FROM nguoi_dung WHERE ma_nguoi_dung = ?";
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement pstm = conn.prepareStatement(query)) {
           
            pstm.setInt(1, id);
           

            success = pstm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
        return success;
    }
    
    public int suaTK(NguoiDung nv, int id1) {
        int success = 0;
        String query = "UPDATE nguoi_dung SET ten_dang_nhap=?,mat_khau=? WHERE ma_nguoi_dung =?";

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement pstm = conn.prepareStatement(query)) {

            pstm.setString(1, nv.getTenDangNhap());
            pstm.setString(2, nv.getMatKhau());
     
            pstm.setInt(3, id1);

            success = pstm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
        return success;
    }
}
