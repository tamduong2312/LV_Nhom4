package DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import MODEL.Thuoc;

public class ThuocDao {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/dbphongkham";
    private static final String USER = "root";
    private static final String PASS = "12345";


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
                t.setSoluongton(rs.getInt("so_luong_ton"));
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
                               don_vi_tinh, don_gia_nhap, don_gia_ban, so_luong_ton, 
                               ngay_san_xuat, han_su_dung, nha_san_xuat, nuoc_san_xuat, ghi_chu)
            VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
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
            pstm.setInt(9, t.getSoluongton());
            pstm.setDate(10, java.sql.Date.valueOf(t.getNgaysanxuat()));
            pstm.setDate(11, java.sql.Date.valueOf(t.getHansudung()));
            pstm.setString(12, t.getNhasanxuat());
            pstm.setString(13, t.getNuocsanxuat());
            pstm.setString(14, t.getGhichu());

            return pstm.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }



    public int xoaThuoc(int id) {
        String query = "DELETE FROM thuoc WHERE ma_thuoc = ?";

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement pstm = conn.prepareStatement(query)) {

            pstm.setInt(1, id);
            return pstm.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }


    public int suaThuoc(Thuoc t, int id) {
        String query = """
            UPDATE thuoc SET ten_thuoc = ?, hoat_chat = ?, ham_luong = ?, dang_thuoc = ?, 
                             loai_thuoc = ?, don_vi_tinh = ?, don_gia_nhap = ?, 
                             don_gia_ban = ?, so_luong_ton = ?, ngay_san_xuat = ?, 
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
            pstm.setInt(9, t.getSoluongton());
            pstm.setDate(10, java.sql.Date.valueOf(t.getNgaysanxuat()));
            pstm.setDate(11, java.sql.Date.valueOf(t.getHansudung()));
            pstm.setString(12, t.getNhasanxuat());
            pstm.setString(13, t.getNuocsanxuat());
            pstm.setString(14, t.getGhichu());
            pstm.setInt(15, id);

            return pstm.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
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
               OR CAST(don_gia_nhap AS CHAR) LIKE ?
               OR CAST(don_gia_ban AS CHAR) LIKE ?
               OR CAST(so_luong_ton AS CHAR) LIKE ?
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
                t.setDongianhap(rs.getDouble("don_gia_nhap"));
                t.setDongiaban(rs.getDouble("don_gia_ban"));
                t.setSoluongton(rs.getInt("so_luong_ton"));
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

}
