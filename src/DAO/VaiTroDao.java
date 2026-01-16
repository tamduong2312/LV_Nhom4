package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import MODEL.ChucVu;
import MODEL.VaiTro;

public class VaiTroDao {
	 private final String DB_URL = "jdbc:mysql://localhost:3306/dbphongkham";
	    private final String USER = "root";
	    private final String PASS = "";



	    public List<VaiTro> GetAllTenVaiTro() {
	        List<VaiTro> list = new ArrayList<>();
	        String query = "SELECT * FROM vai_tro";

	        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
	             PreparedStatement pstmt = conn.prepareStatement(query);
	             ResultSet rs = pstmt.executeQuery()) {

	            while (rs.next()) {
	            	VaiTro nv = new VaiTro();
	                nv.setMaVaiTro(rs.getString("ma_vai_tro"));
	                nv.setTenVaiTro(rs.getString("ten_hien_thi"));
	                list.add(nv);
	            }

	        } catch (SQLException e) {
	            e.printStackTrace();
	        }

	        return list;
	    }
}
