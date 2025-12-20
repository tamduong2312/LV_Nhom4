package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import MODEL.ChuyenKhoa;
import MODEL.NhanVien;


public class ChuyenKhoaDao {
	 private static final String DB_URL = "jdbc:mysql://localhost:3306/dbphongkham";
	    private static final String USER = "root";
	    private static final String PASS = "";


	    public List<ChuyenKhoa> getallChuyenKhoa() {
	        List<ChuyenKhoa> list = new ArrayList<>();
	        String query = "SELECT * FROM chuyen_khoa";

	        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
	             PreparedStatement pstmt = conn.prepareStatement(query);
	             ResultSet rs = pstmt.executeQuery()) {

	            while (rs.next()) {
	            	ChuyenKhoa nv = new ChuyenKhoa();
	                nv.setMa_chuyen_khoa(rs.getInt("ma_chuyen_khoa"));
	                nv.setTen_chuyen_khoa(rs.getString("ten_chuyen_khoa"));
	                nv.setMo_ta(rs.getString("mo_ta"));
	  
	     
	                list.add(nv);
	            }

	        } catch (SQLException e) {
	            e.printStackTrace();
	        }

	        return list;
	    }
}
