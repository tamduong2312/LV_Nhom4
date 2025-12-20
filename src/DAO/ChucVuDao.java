package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import MODEL.ChiSoTongQuat;
import MODEL.ChucVu;
import MODEL.ChuyenKhoa;

public class ChucVuDao {
    private final String DB_URL = "jdbc:mysql://localhost:3306/dbphongkham";
    private final String USER = "root";
    private final String PASS = "";



    public List<ChucVu> GetAllTenChucVu() {
        List<ChucVu> list = new ArrayList<>();
        String query = "SELECT * FROM chuc_vu WHERE ten_chuc_vu NOT IN('Quản trị viên') ";

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement pstmt = conn.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
            	ChucVu nv = new ChucVu();
                nv.setId(rs.getInt("id"));
                nv.setTenChucVu(rs.getString("ten_chuc_vu"));
                list.add(nv);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }
    

    

}
