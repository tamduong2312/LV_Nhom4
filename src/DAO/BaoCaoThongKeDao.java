package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import MODEL.ThongKeDTO;

public class BaoCaoThongKeDao {
    
  
    private static final String DB_URL = "jdbc:mysql://localhost:3306/dbphongkham";
    private static final String USER = "root";
    private static final String PASS = "";

   
    public List<ThongKeDTO> getSoLuongBenhNhanTheoThang(int thang, int nam) {
        List<ThongKeDTO> list = new ArrayList<>();
        
 
        String sql = 
        		
        		"SELECT DAY(ngay_kham) AS ngay, COUNT(DISTINCT ma_benh_nhan)  AS so_luong " +
                     "FROM phieu_kham " +  
                     "WHERE MONTH(ngay_kham) = ? AND YEAR(ngay_kham) = ? " +
                     " AND trang_thai= 'DA_CAP_THUOC' " +
                     " OR trang_thai= 'DA_THANH_TOAN' " +
                     "GROUP BY DAY(ngay_kham) " +
                     "ORDER BY DAY(ngay_kham) ASC";

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, thang);
            pstmt.setInt(2, nam);

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    int ngay = rs.getInt("ngay");
                    double soLuong = rs.getDouble("so_luong");
                    
                    list.add(new ThongKeDTO(ngay, soLuong));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }


    public List<ThongKeDTO> getDoanhThuTheoThang(int thang, int nam) {
        List<ThongKeDTO> list = new ArrayList<>();

 
        String sql = "SELECT DAY(ngay_thanh_toan) AS ngay, SUM(tong_tien) AS doanh_thu " +
                     "FROM hoa_don " + 
                     "WHERE MONTH(ngay_thanh_toan) = ? AND YEAR(ngay_thanh_toan) = ? " +
                     "GROUP BY DAY(ngay_thanh_toan) " +
                     "ORDER BY DAY(ngay_thanh_toan) ASC";

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, thang);
            pstmt.setInt(2, nam);

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    int ngay = rs.getInt("ngay");
                    double tien = rs.getDouble("doanh_thu");
                    
                    list.add(new ThongKeDTO(ngay, tien));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}