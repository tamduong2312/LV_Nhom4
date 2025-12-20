package DAO;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import MODEL.NguoiDung;
import MODEL.NhanVien;
import MODEL.PasswordEncoder;

import MODEL.PhanQuyenLogin;

public class DangNhapDao {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/dbphongkham";
    private static final String USER = "root";
    private static final String PASS = "";



    public PhanQuyenLogin BSlogin(String email, String mknhap) {
        PhanQuyenLogin result = null;
        String mkmahoa = null;

        String query = "SELECT nd.mat_khau, nv.ma_nhan_vien, nv.ho_ten, nd.vai_tro,nv.chuyen_khoa," 
                + "ck.ma_chuyen_khoa, ck.ten_chuyen_khoa, "
                + "nd.ma_tai_khoan, nd.lan_dau_dang_nhap "
                + "FROM tai_khoan nd "
                + "LEFT JOIN nhan_vien nv ON nd.ma_nhan_vien = nv.ma_nhan_vien " 
                + "LEFT JOIN chuyen_khoa ck ON nv.chuyen_khoa = ck.ten_chuyen_khoa " 
                + "WHERE nd.email = ?";

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, email);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
              
                    mkmahoa = rs.getString("mat_khau");
                    
                    if (mkmahoa != null && PasswordEncoder.checkPassword(mknhap, mkmahoa)) {
                        
                        int maNV = rs.getInt("ma_nhan_vien");
                        String tennv = rs.getString("ho_ten");
                        String role = rs.getString("vai_tro");
                        String ck = rs.getString("chuyen_khoa");
                        int ma_tai_khoan = rs.getInt("ma_tai_khoan");
                        int maCK = rs.getInt("ma_chuyen_khoa");
                        
                     
                        boolean lan_dau_dang_nhap = rs.getBoolean("lan_dau_dang_nhap");
                   

                        result = new PhanQuyenLogin(maNV, tennv, role, ck, ma_tai_khoan, lan_dau_dang_nhap,maCK);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result; 
    }

    public List<NguoiDung> getalltk() {
        List<NguoiDung> list = new ArrayList<>();

        String query = "SELECT ma_tai_khoan, username, mat_khau, email, vai_tro, ma_nhan_vien FROM tai_khoan";

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement pstmt = conn.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
            	NguoiDung nd = new NguoiDung(); 
                nd.setMaNguoiDung(rs.getInt("ma_tai_khoan"));
                nd.setTenDangNhap(rs.getString("username")); 
                nd.setMatKhau(rs.getString("mat_khau"));
                
      
                nd.setEmail(rs.getString("email")); 
                
   
                nd.setRole((rs.getString("vai_tro"))) ;
                nd.setNhanvienid(rs.getInt("ma_nhan_vien"));
      
 
       
                list.add(nd);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    

    public boolean themNhanVienKemTaiKhoan(NhanVien nv, String username, String password, String role) throws SQLException {
        Connection conn = null;
        CallableStatement cstmt = null; 

  
        String sql = "{call sp_ThemNhanVienVaTaiKhoan(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}";

        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            
    
            cstmt = conn.prepareCall(sql);


            cstmt.setString(1, nv.getHoTen());
            cstmt.setBoolean(2, nv.getGT());
            cstmt.setDate(3, java.sql.Date.valueOf(nv.getNgay_sinh()));
            cstmt.setString(4, String.valueOf(nv.getCCCD())); 
            cstmt.setString(5, nv.getDiaChi());
            cstmt.setString(6, "0" + nv.getSDT()); 
            cstmt.setString(7, nv.getEmail());
            cstmt.setString(8, nv.getBangCap());
            cstmt.setString(9, nv.getChucVu());
            cstmt.setString(10, nv.getMaChuyenKhoa());
            cstmt.setDate(11, java.sql.Date.valueOf(nv.getNgayVaoLam()));

        cstmt.setString(12, username);
            cstmt.setString(13, password);
            cstmt.setString(14, role);


            cstmt.execute();
            
            return true; 

        } catch (SQLException e) {
            e.printStackTrace();

            throw e; 
        } finally {
            if (cstmt != null) cstmt.close();
            if (conn != null) conn.close();
        }
    }
    
    public int themTK(NguoiDung nv) {
        int success = 0;
        String query = "INSERT INTO tai_khoan(username,email ,mat_khau,vai_tro,ma_nhan_vien) VALUES (?, ?,?,?,?)";

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, nv.getTenDangNhap());
            pstmt.setString(2, nv.getEmail());
            pstmt.setString(3, nv.getMatKhau());
            pstmt.setString(4, nv.getRole());
            pstmt.setInt(5,  nv.getNhanvienid());
     

            success = pstmt.executeUpdate();


        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
		return success;
    }
    
    public int xoatk(int id) {
        int success = 0;
        String query = "DELETE FROM tai_khoan WHERE ma_tai_khoan = ?";
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
        String query = "UPDATE tai_khoan SET username=?,email = ?,mat_khau=?, vai_tro = ? WHERE ma_tai_khoan =?";

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement pstm = conn.prepareStatement(query)) {

            pstm.setString(1, nv.getTenDangNhap());
            pstm.setString(2, nv.getEmail());
            pstm.setString(3, nv.getMatKhau());
            pstm.setString(4, nv.getRole());
     
            pstm.setInt(5, id1);

            success = pstm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
        return success;
    }
    

    public int suaMK(int id, String mkMoi) {
        int success = 0;
        String queryUpdate = "UPDATE tai_khoan SET mat_khau = ? WHERE ma_tai_khoan = ?";

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement pstmUpdate = conn.prepareStatement(queryUpdate)) {

            pstmUpdate.setString(1, mkMoi);
            pstmUpdate.setInt(2, id);

        
            success = pstmUpdate.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return success;
    }

    

    public boolean SSMK(int id, String mkCuNhapVao) {

        String query = "SELECT mat_khau FROM tai_khoan WHERE ma_tai_khoan = ?";

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            
            pstmt.setInt(1, id);
            
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    String dbHashPass = rs.getString("mat_khau");
                    
            
                    if (dbHashPass != null && PasswordEncoder.checkPassword(mkCuNhapVao, dbHashPass)) {
                        return true; 
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return false;
    }
    public boolean doiMatKhauTheoEmail(String email, String matKhauMoi) {
   
        String query = "UPDATE tai_khoan SET mat_khau = ? WHERE email = ?";
        
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement pstm = conn.prepareStatement(query)) {


            pstm.setString(1, matKhauMoi); 
            pstm.setString(2, email);     

            int rowsAffected = pstm.executeUpdate();
            
        
            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    
    public List<NguoiDung> getallEmail() {
        List<NguoiDung> list = new ArrayList<>();

        String query = "SELECT email FROM tai_khoan";

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement pstmt = conn.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
            	NguoiDung nd = new NguoiDung(); 
              nd.setEmail(rs.getString("email")); 
           list.add(nd);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }
    
    public List<String> timKiemEmail(String keyword) {
        List<String> list = new ArrayList<>();
        

        String query = "SELECT email FROM tai_khoan WHERE email LIKE ? LIMIT 10";

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement pstmt = conn.prepareStatement(query)) {


            pstmt.setString(1, "%" + keyword + "%");

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    list.add(rs.getString("email"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    
    public boolean capLaiTaiKhoan(int maNV, String hoTen, String email, String role) {
  
        
        String sql = "INSERT INTO tai_khoan (username, mat_khau, email, vai_tro, ma_nhan_vien) VALUES (?, ?, ?, ?, ?)";
        
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement pstm = conn.prepareStatement(sql)) {
            
           
            String username = hoTen.toLowerCase().replace(" ", ""); 
            
        
            String defaultPass = PasswordEncoder.hashPassword("123456");

            pstm.setString(1, username);
            pstm.setString(2, defaultPass);
            pstm.setString(3, email);
            pstm.setString(4, role);
            pstm.setInt(5, maNV); 
            
            int rows = pstm.executeUpdate();
            return rows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    
    public int doiMatKhauLanDau(int maTK, String matKhauMoi) {
        int result = 0;
    
        String query = "UPDATE tai_khoan SET mat_khau = ?, lan_dau_dang_nhap = 0 WHERE ma_tai_khoan = ?";
        
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement pstm = conn.prepareStatement(query)) {
            
            pstm.setString(1, matKhauMoi);
            pstm.setInt(2, maTK);
            
            result = pstm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}
