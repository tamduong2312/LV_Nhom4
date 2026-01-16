package DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import MODEL.CTToaThuoc;
import MODEL.DanhMucBenhLy;
import MODEL.Thuoc;

public class ThuocDao {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/dbphongkham";
    private static final String USER = "root";
    private static final String PASS = "";


    public List<Thuoc> getAllThuoc() {
        List<Thuoc> list = new ArrayList<>();
        String query = "SELECT * FROM thuoc";

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement pstm = conn.prepareStatement(query);
             ResultSet rs = pstm.executeQuery()) {

            while (rs.next()) {
                Thuoc t = new Thuoc();
                t.setMathuoc(rs.getInt("ma_thuoc"));
                t.setTenthuoc(rs.getString("ten_thuoc"));
                t.setHoatchat(rs.getString("hoat_chat"));
                t.setHamluong(rs.getString("ham_luong"));
                t.setDangthuoc(rs.getString("dang_thuoc"));
                t.setLoaithuoc(rs.getString("loai_thuoc"));
                t.setDonvitinh(rs.getString("don_vi_tinh"));
                t.setDongianhap(rs.getDouble("don_gia_nhap"));
                t.setDongiaban(rs.getDouble("don_gia_ban"));
                //t.setSoluongton(rs.getInt("so_luong_ton"));
                t.setNgaysanxuat(rs.getDate("ngay_san_xuat").toLocalDate());
                t.setHansudung(rs.getDate("han_su_dung").toLocalDate());
                t.setNhasanxuat(rs.getString("nha_san_xuat"));
                t.setNuocsanxuat(rs.getString("nuoc_san_xuat"));
                t.setGhichu(rs.getString("ghi_chu"));

                list.add(t);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }




    public int themThuoc(Thuoc t) {
        String query = """
            INSERT INTO thuoc (ten_thuoc, hoat_chat, ham_luong, dang_thuoc, loai_thuoc, 
                               don_vi_tinh,don_gia_nhap, don_gia_ban, 
                               ngay_san_xuat, han_su_dung, nha_san_xuat, nuoc_san_xuat, ghi_chu)
            VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
            """;

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement pstm = conn.prepareStatement(query)) {

            pstm.setString(1, t.getTenthuoc());
            pstm.setString(2, t.getHoatchat());
            pstm.setString(3, t.getHamluong());
            pstm.setString(4, t.getDangthuoc());
            pstm.setString(5, t.getLoaithuoc());
            pstm.setString(6, t.getDonvitinh());
            pstm.setDouble(7, t.getDongianhap());
            pstm.setDouble(8, t.getDongiaban());
//            pstm.setInt(8, t.getSoluongton());
            pstm.setDate(9, java.sql.Date.valueOf(t.getNgaysanxuat()));
            pstm.setDate(10, java.sql.Date.valueOf(t.getHansudung()));
            pstm.setString(11, t.getNhasanxuat());
            pstm.setString(12, t.getNuocsanxuat());
            pstm.setString(13, t.getGhichu());

            return pstm.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
         	  throw new RuntimeException(e.getMessage());
        }
    }



    public int xoaThuoc(int id) {
        String query = "DELETE FROM thuoc WHERE ma_thuoc = ?";

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement pstm = conn.prepareStatement(query)) {

            pstm.setInt(1, id);
            int rowsAffected = pstm.executeUpdate();
            return rowsAffected > 0 ? 1 : 0; 

        } catch (java.sql.SQLIntegrityConstraintViolationException e) {

            System.err.println("Lỗi ràng buộc: " + e.getMessage());
            return -1; 
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }


    public int suaThuoc(Thuoc t, int id) {
        String query = """
            UPDATE thuoc SET ten_thuoc = ?, hoat_chat = ?, ham_luong = ?, dang_thuoc = ?, 
                             loai_thuoc = ?, don_vi_tinh = ?, don_gia_nhap = ?,
                             don_gia_ban = ?, ngay_san_xuat = ?, 
                             han_su_dung = ?, nha_san_xuat = ?, nuoc_san_xuat = ?, 
                             ghi_chu = ? 
            WHERE ma_thuoc = ?
            """;

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement pstm = conn.prepareStatement(query)) {

            pstm.setString(1, t.getTenthuoc());
            pstm.setString(2, t.getHoatchat());
            pstm.setString(3, t.getHamluong());
            pstm.setString(4, t.getDangthuoc());
            pstm.setString(5, t.getLoaithuoc());
            pstm.setString(6, t.getDonvitinh());
          pstm.setDouble(7, t.getDongianhap());
            pstm.setDouble(8, t.getDongiaban());
//            pstm.setInt(8, t.getSoluongton());
            pstm.setDate(9, java.sql.Date.valueOf(t.getNgaysanxuat()));
            pstm.setDate(10, java.sql.Date.valueOf(t.getHansudung()));
            pstm.setString(11, t.getNhasanxuat());
            pstm.setString(12, t.getNuocsanxuat());
            pstm.setString(13, t.getGhichu());
            pstm.setInt(14, id);

            return pstm.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
         	  throw new RuntimeException(e.getMessage());
        }
    }


    public List<String> getThuocByTenthuoc(String keyword) {
        List<String> list = new ArrayList<>();
        String query = "SELECT ma_thuoc, ten_thuoc FROM thuoc WHERE ten_thuoc LIKE ? LIMIT 20";

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            
            pstmt.setString(1, "%" + keyword + "%");
            ResultSet rs = pstmt.executeQuery();
            
            while (rs.next()) {
            	  String ma = rs.getString("ma_thuoc");
                  String ten = rs.getString("ten_thuoc");
                
                String hienThi = ma + "." + ten;
                list.add(hienThi); 
             
            }
        } catch (Exception e) { e.printStackTrace(); }
        return list;
    }

    public List<Thuoc> TimKiemThuoc(String keyword) {
        List<Thuoc> list = new ArrayList<>();

        String query = """
            SELECT * FROM thuoc 
            WHERE ten_thuoc LIKE ?
               OR hoat_chat LIKE ?
               OR ham_luong LIKE ?
               OR dang_thuoc LIKE ?
               OR loai_thuoc LIKE ?
               OR don_vi_tinh LIKE ?
                           OR don_gia_nhap LIKE ?
      
               OR CAST(don_gia_ban AS CHAR) LIKE ?

               OR nha_san_xuat LIKE ?
               OR nuoc_san_xuat LIKE ?
               OR ghi_chu LIKE ?
            """;

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            String value = "%" + keyword.trim() + "%";

            for (int i = 1; i <= 12; i++) {
                pstmt.setString(i, value);
            }

            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Thuoc t = new Thuoc();

                t.setMathuoc(rs.getInt("ma_thuoc"));
                t.setTenthuoc(rs.getString("ten_thuoc"));
                t.setHoatchat(rs.getString("hoat_chat"));
                t.setHamluong(rs.getString("ham_luong"));
                t.setDangthuoc(rs.getString("dang_thuoc"));
                t.setLoaithuoc(rs.getString("loai_thuoc"));
                t.setDonvitinh(rs.getString("don_vi_tinh"));
          
                t.setDongiaban(rs.getDouble("don_gia_ban"));
                t.setDongianhap(rs.getDouble("don_gia_nhap"));

                t.setNgaysanxuat(rs.getDate("ngay_san_xuat").toLocalDate());
                t.setHansudung(rs.getDate("han_su_dung").toLocalDate());
                t.setNhasanxuat(rs.getString("nha_san_xuat"));
                t.setNuocsanxuat(rs.getString("nuoc_san_xuat"));
                t.setGhichu(rs.getString("ghi_chu"));

                list.add(t);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }
    public Thuoc getThuocById(int maThuoc) {
        Thuoc t = null;
        String query = "SELECT * FROM thuoc WHERE ma_thuoc = ?";
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            
            pstmt.setInt(1, maThuoc);
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
            	 t = new Thuoc();
                t.setMathuoc(rs.getInt("ma_thuoc"));
                t.setTenthuoc(rs.getString("ten_thuoc"));
                t.setHoatchat(rs.getString("hoat_chat"));
                t.setHamluong(rs.getString("ham_luong"));
                t.setDangthuoc(rs.getString("dang_thuoc"));
                t.setLoaithuoc(rs.getString("loai_thuoc"));
                t.setDonvitinh(rs.getString("don_vi_tinh"));
          
                t.setDongiaban(rs.getDouble("don_gia_ban"));
                t.setDongianhap(rs.getDouble("don_gia_nhap"));

                t.setNgaysanxuat(rs.getDate("ngay_san_xuat").toLocalDate());
                t.setHansudung(rs.getDate("han_su_dung").toLocalDate());
                t.setNhasanxuat(rs.getString("nha_san_xuat"));
                t.setNuocsanxuat(rs.getString("nuoc_san_xuat"));
                t.setGhichu(rs.getString("ghi_chu"));
            }
        } catch (Exception e) { e.printStackTrace(); }
        return t;
    }


    
}
