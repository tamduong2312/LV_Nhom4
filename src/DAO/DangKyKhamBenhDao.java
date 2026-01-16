package DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import MODEL.DangKyKhamBenh;
import MODEL.DangKyKhamBenh.TRANGTHAI;


public class DangKyKhamBenhDao {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/dbphongkham";
    private static final String USER = "root";
    private static final String PASS = "";


    public List<DangKyKhamBenh> getAll() {
        List<DangKyKhamBenh> list = new ArrayList<>();
        String query = "SELECT * FROM dang_ky_kham_benh ORDER BY thoi_gian_dang_ky DESC";

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement pstm = conn.prepareStatement(query);
             ResultSet rs = pstm.executeQuery()) {

            while (rs.next()) {
                DangKyKhamBenh dk = new DangKyKhamBenh();

                dk.setId(rs.getInt("id"));
                dk.setMabenhnhan(rs.getInt("ma_benh_nhan"));
                dk.setManhanvien(rs.getInt("ma_nhan_vien"));
                dk.setMachuyenkhoa(rs.getInt("ma_chuyen_khoa"));
                dk.setSothutu(rs.getInt("so_thu_tu"));
                dk.setThoigiandangky(rs.getTimestamp("thoi_gian_dang_ky").toLocalDateTime());
                dk.setTrangthai(TRANGTHAI.valueOf(rs.getString("trang_thai")));
                dk.setGhichu(rs.getString("ghi_chu"));

                list.add(dk);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    // lay tat ca benh nhan trong ngay hom nay
    public List<DangKyKhamBenh> getAllhomnay() {
        List<DangKyKhamBenh> list = new ArrayList<>();
        String query = """
     SELECT dk.*, dv.ten_dich_vu 
FROM dang_ky_kham_benh dk
LEFT JOIN phieu_kham pk ON dk.ma_benh_nhan = pk.ma_benh_nhan 
     AND dk.ma_chuyen_khoa = pk.ma_chuyen_khoa 
     AND DATE(pk.ngay_kham) = CURDATE()
LEFT JOIN phieu_chi_dinh pcd ON pk.ma_phieu_kham = pcd.ma_phieu_kham
LEFT JOIN chi_tiet_chi_dinh ctcd ON pcd.ma_phieu_chi_dinh = ctcd.ma_phieu_chi_dinh
LEFT JOIN dich_vu dv ON ctcd.ma_dich_vu = dv.ma_dich_vu
WHERE DATE(dk.thoi_gian_dang_ky) = CURDATE()
GROUP BY dk.id -- Để tránh trùng lặp dòng
ORDER BY dk.id DESC
        """;

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement pstm = conn.prepareStatement(query);
             ResultSet rs = pstm.executeQuery()) {

            while (rs.next()) {
                DangKyKhamBenh dk = new DangKyKhamBenh();

                dk.setId(rs.getInt("id"));
                dk.setMabenhnhan(rs.getInt("ma_benh_nhan"));
                dk.setManhanvien(rs.getInt("ma_nhan_vien"));
                dk.setMachuyenkhoa(rs.getInt("ma_chuyen_khoa"));
                dk.setSothutu(rs.getInt("so_thu_tu"));
                dk.setThoigiandangky(rs.getTimestamp("thoi_gian_dang_ky").toLocalDateTime());
                dk.setTrangthai(TRANGTHAI.valueOf(rs.getString("trang_thai")));
                dk.setGhichu(rs.getString("ghi_chu"));

                list.add(dk);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }
    // lay tat ca benh nhan trong ngay hom nay của trợ lý tổng quát
    public List<DangKyKhamBenh> getAlltodayTroLyTQ(int mack) {
        List<DangKyKhamBenh> list = new ArrayList<>();
        String query = """
        	    SELECT * FROM dang_ky_kham_benh
        	    WHERE DATE(thoi_gian_dang_ky) = CURDATE() 
        	      AND  ma_chuyen_khoa = ? AND trang_thai != 'DA_KHAM' 	      
        	    ORDER BY so_thu_tu ASC
        	""";
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement pstm = conn.prepareStatement(query)){
        	
        	pstm.setInt(1, mack);
             ResultSet rs = pstm.executeQuery() ;

            while (rs.next()) {
                DangKyKhamBenh dk = new DangKyKhamBenh();

                dk.setId(rs.getInt("id"));
                dk.setMabenhnhan(rs.getInt("ma_benh_nhan"));
                dk.setManhanvien(rs.getInt("ma_nhan_vien"));
                dk.setMachuyenkhoa(rs.getInt("ma_chuyen_khoa"));
                dk.setSothutu(rs.getInt("so_thu_tu"));
                dk.setThoigiandangky(rs.getTimestamp("thoi_gian_dang_ky").toLocalDateTime());
                dk.setTrangthai(TRANGTHAI.valueOf(rs.getString("trang_thai")));
                dk.setGhichu(rs.getString("ghi_chu"));

                list.add(dk);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    public List<DangKyKhamBenh> getAllTodayByChuyenKhoa(int maChuyenKhoa) {
        List<DangKyKhamBenh> list = new ArrayList<>();

        String query = """
            SELECT 
                dk.id,
                dk.ma_benh_nhan,
                dk.ma_nhan_vien,
                dk.ma_chuyen_khoa,
                dk.so_thu_tu,
                dk.ghi_chu,
                dk.thoi_gian_dang_ky,
                
                dk.trang_thai,                     
                pk.trang_thai AS trang_thai_phieu  
            FROM 
                dang_ky_kham_benh dk
            INNER JOIN 
                phieu_kham pk ON dk.ma_benh_nhan = pk.ma_benh_nhan 
            WHERE 
                DATE(dk.thoi_gian_dang_ky) = CURDATE() 
                AND DATE(pk.ngay_kham) = CURDATE()
                AND dk.ma_chuyen_khoa = ?
                AND pk.trang_thai != 'DA_KHAM'
                AND pk.trang_thai != 'CHO_THANH_TOAN'
                AND pk.trang_thai != 'DA_THANH_TOAN'
                AND pk.trang_thai != 'DA_CAP_THUOC'
                AND pk.trang_thai != 'DOI_CHUYEN_KHOA'
                
            ORDER BY 
                dk.so_thu_tu ASC;
        """;

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement pstm = conn.prepareStatement(query)) {

     
            pstm.setInt(1, maChuyenKhoa);

            try (ResultSet rs = pstm.executeQuery()) {
                while (rs.next()) {
                    DangKyKhamBenh dk = new DangKyKhamBenh();

                    dk.setId(rs.getInt("id"));
                    dk.setMabenhnhan(rs.getInt("ma_benh_nhan"));
                    dk.setManhanvien(rs.getInt("ma_nhan_vien"));
                    dk.setMachuyenkhoa(rs.getInt("ma_chuyen_khoa"));
                    dk.setSothutu(rs.getInt("so_thu_tu"));
                    dk.setThoigiandangky(rs.getTimestamp("thoi_gian_dang_ky").toLocalDateTime());
                    dk.setGhichu(rs.getString("ghi_chu"));
                    

                    dk.setTrangthai(TRANGTHAI.valueOf(rs.getString("trang_thai")));
                    dk.setTrangThaiHienThi(rs.getString("trang_thai_phieu"));

                    list.add(dk);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }
    
    public List<DangKyKhamBenh> getAllTodayByBSChuyenKhoa(int maChuyenKhoa) {
        List<DangKyKhamBenh> list = new ArrayList<>();

        String query = """
            SELECT 
                dk.id,                         
                dk.ma_benh_nhan, 
                dk.ma_nhan_vien, 
                dk.ma_phieu_kham,            
                dk.ma_chuyen_khoa AS ma_khoa_dk,                
                dk.so_thu_tu,               
                dk.ghi_chu AS ghi_chu_dk, 
                pk.ghi_chu AS ghi_chu_pk,
                dk.thoi_gian_dang_ky,
                dk.trang_thai AS trang_thai_dk,    
                pk.trang_thai AS trang_thai_phieu  
            FROM 
                dang_ky_kham_benh dk
        
            LEFT JOIN 
                phieu_kham pk ON dk.ma_phieu_kham = pk.ma_phieu_kham
            WHERE 
                DATE(dk.thoi_gian_dang_ky) = CURDATE() 
                AND dk.ma_chuyen_khoa = ?
                AND dk.trang_thai = 'CHO_KHAM_BS'
                AND (pk.trang_thai IS NULL OR pk.trang_thai NOT IN ('CHO_THANH_TOAN'))
                
            GROUP BY dk.id 
            ORDER BY dk.so_thu_tu ASC
        """;

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement pstm = conn.prepareStatement(query)) {

            pstm.setInt(1, maChuyenKhoa);

            try (ResultSet rs = pstm.executeQuery()) {
                while (rs.next()) {
                    DangKyKhamBenh dk = new DangKyKhamBenh();
                    dk.setId(rs.getInt("id"));
                    dk.setMabenhnhan(rs.getInt("ma_benh_nhan"));
                    dk.setMachuyenkhoa(rs.getInt("ma_khoa_dk"));
                    dk.setSothutu(rs.getInt("so_thu_tu"));        
                    dk.setThoigiandangky(rs.getTimestamp("thoi_gian_dang_ky").toLocalDateTime());
                    dk.setTrangthai(DangKyKhamBenh.TRANGTHAI.valueOf(rs.getString("trang_thai_dk")));
                    dk.setMaPhieuKham(rs.getInt("ma_phieu_kham")); 

       
                    String gcPk = rs.getString("ghi_chu_pk");
                    String gcDk = rs.getString("ghi_chu_dk");
                    dk.setGhichu(gcPk != null ? gcPk : gcDk);
                    
 

                    list.add(dk);
                }
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return list;
    }
    // ==========================================
    //  LẤY SỐ THỨ TỰ TIẾP THEO TRONG NGÀY
    // ==========================================
    public int laySoThuTuTiepTheo(int maChuyenKhoa) {
    	
        String query = """
            SELECT IFNULL(MAX(so_thu_tu), 0) AS max_stt 
            FROM dang_ky_kham_benh
            WHERE ma_chuyen_khoa = ?
              AND DATE(thoi_gian_dang_ky) = CURDATE()
            """;

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement pstm = conn.prepareStatement(query)) {

            pstm.setInt(1, maChuyenKhoa);

            try (ResultSet rs = pstm.executeQuery()) {
                if (rs.next()) {
         
                    return rs.getInt("max_stt") + 1;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 1;
    }

    // ==========================================
    //  THÊM ĐĂNG KÝ KHÁM BỆNH
    // ==========================================
    public int themDangKy(DangKyKhamBenh dk) {
        int generatedId = -1;
        String sql = "INSERT INTO dang_ky_kham_benh (ma_benh_nhan, ma_nhan_vien, ma_chuyen_khoa, so_thu_tu, thoi_gian_dang_ky, trang_thai, ghi_chu) "
                   + "VALUES (?, ?, ?, ?, ?, ?, ?)";
        
    
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            
            ps.setInt(1, dk.getMabenhnhan());
            ps.setInt(2, dk.getManhanvien());
            ps.setInt(3, dk.getMachuyenkhoa());
            ps.setInt(4, dk.getSothutu());
            ps.setTimestamp(5, java.sql.Timestamp.valueOf(dk.getThoigiandangky()));
            ps.setString(6, dk.getTrangthai().toString());
            ps.setString(7, dk.getGhichu());

            int affectedRows = ps.executeUpdate();

            if (affectedRows > 0) {
                try (ResultSet rs = ps.getGeneratedKeys()) {
                    if (rs.next()) {
                        generatedId = rs.getInt(1); 
                    }
                }
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return generatedId; 
    }
    
    public int getMaPhieuKhamByIdDangKy(int idDangKy) {
        int maPK = 0;
        String sql = "SELECT ma_phieu_kham FROM dang_ky_kham_benh WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, idDangKy);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                maPK = rs.getInt("ma_phieu_kham");
                System.out.println("ID Đăng ký: " + idDangKy + " -> Mã Phiếu lấy được: " + maPK);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return maPK;
    }

    // ==========================================
    //  CẬP NHẬT TRẠNG THÁI
    // ==========================================
    public int capNhatTrangThai(int id) {
    	 String query = "UPDATE dang_ky_kham_benh SET trang_thai = 'CHO_KHAM_BS' " +
                 "WHERE id = ? AND trang_thai NOT IN ('DA_KHAM', 'DA_THANH_TOAN')";
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement pstm = conn.prepareStatement(query)) {


            pstm.setInt(1,id);

            return pstm.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }
    
    
    public boolean capNhatChuyenKhoaDangKy(int maBenhNhan, int maChuyenKhoaMoi) {

        String sql = """
            UPDATE dang_ky_kham_benh 
            SET ma_chuyen_khoa = ?, 
                trang_thai = 'CHO_KHAM_BS'
            WHERE ma_benh_nhan = ? 
              AND DATE(thoi_gian_dang_ky) = CURDATE()
              AND trang_thai != 'DA_KHAM';
        """;

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, maChuyenKhoaMoi);
            ps.setInt(2, maBenhNhan);

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    
    public int capNhatDaKhamByMaBenhNhan(int maBenhNhan) {
        String query = """
            UPDATE dang_ky_kham_benh 
            SET trang_thai = 'DA_KHAM'
            WHERE id = ? 

       
        """;

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement pstm = conn.prepareStatement(query)) {


            pstm.setInt(1, maBenhNhan);

        
            return pstm.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }
    
    

    
    
    public int capNhatDaKhamById(int idDangKy) {

        String query = "UPDATE dang_ky_kham_benh SET trang_thai = 'DA_KHAM' WHERE id = ?";

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement pstm = conn.prepareStatement(query)) {
            pstm.setInt(1, idDangKy);
            return pstm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
    
    
    
    
    public int capNhatTrangThaiThucHienCLSById(int idDangKy) {
    
        String query = "UPDATE dang_ky_kham_benh SET trang_thai = 'DA_THUC_HIEN_CLS' WHERE id = ?";

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement pstm = conn.prepareStatement(query)) {
            pstm.setInt(1, idDangKy);
            return pstm.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
    
    
    
    
    public int updateTrangThaiKQChiDinhCLS(int maPK) {
    	int kq = 0;
        String sql = "UPDATE phieu_kham SET trang_thai='DA_CO_KET_QUA_CLS' WHERE ma_phieu_kham=?";

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement ps = conn.prepareStatement(sql)) {


            ps.setInt(1, maPK);
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return kq;
    }
    
    
    
    
    public int capNhatTrangThaiDaKham(int maPhieuKham) {
   
        String query = "UPDATE phieu_kham SET trang_thai = 'DA_KHAM' " +
                       "WHERE ma_phieu_kham = ? AND DATE(ngay_kham) = CURDATE()";

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement pstm = conn.prepareStatement(query)) {

            pstm.setInt(1, maPhieuKham);

            return pstm.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }
    

    public int capNhatTrangThaiVangMat(int id) {
        String query = "UPDATE dang_ky_kham_benh SET trang_thai = 'VANG_MAT' WHERE id = ?";

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement pstm = conn.prepareStatement(query)) {


            pstm.setInt(1,id);

            return pstm.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }

    public List<DangKyKhamBenh> getAllVangMatToDay() {
        List<DangKyKhamBenh> list = new ArrayList<>();
        String query = """
            SELECT * 
            FROM dang_ky_kham_benh
            WHERE DATE(thoi_gian_dang_ky) = CURDATE() AND trang_thai='VANG_MAT' AND ma_chuyen_khoa = 1
            ORDER BY so_thu_tu ASC
        """;

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement pstm = conn.prepareStatement(query);
             ResultSet rs = pstm.executeQuery()) {

            while (rs.next()) {
                DangKyKhamBenh dk = new DangKyKhamBenh();

                dk.setId(rs.getInt("id"));
                dk.setMabenhnhan(rs.getInt("ma_benh_nhan"));
                dk.setManhanvien(rs.getInt("ma_nhan_vien"));
                dk.setMachuyenkhoa(rs.getInt("ma_chuyen_khoa"));
                dk.setSothutu(rs.getInt("so_thu_tu"));
                dk.setThoigiandangky(rs.getTimestamp("thoi_gian_dang_ky").toLocalDateTime());
                dk.setTrangthai(TRANGTHAI.valueOf(rs.getString("trang_thai")));
                dk.setGhichu(rs.getString("ghi_chu"));

                list.add(dk);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }
    
    

    public int updateTrangThaiChoKhamBS(int maPK) {
    	int kq = 0;
    	   String query = "UPDATE dang_ky_kham_benh SET trang_thai = 'CHO_KHAM_BS' " +
                   "WHERE id = ? AND trang_thai NOT IN ('DA_KHAM', 'DA_THANH_TOAN')";

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement ps = conn.prepareStatement(query)) {


            ps.setInt(1, maPK);
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return kq;
    }
    public int capNhatTrangThaiHUY(int id) {
        String query = "UPDATE dang_ky_kham_benh SET trang_thai = 'HUY' WHERE id = ?";

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement pstm = conn.prepareStatement(query)) {


            pstm.setInt(1, id);

            return pstm.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }

    // ==========================================
    //  XÓA / HỦY ĐĂNG KÝ
    // ==========================================
    public int xoaDangKy(int id) {
        String query = "DELETE FROM dang_ky_kham_benh WHERE id = ?";

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement pstm = conn.prepareStatement(query)) {

            pstm.setInt(1, id);
            return pstm.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }

    // ==========================================
    //  TÌM KIẾM
    // ==========================================
    public List<DangKyKhamBenh> timKiem(String keyword) {

        List<DangKyKhamBenh> list = new ArrayList<>();

        String query = """
            SELECT dk.* 
            FROM dang_ky_kham_benh dk 
            JOIN benh_nhan bn ON dk.ma_benh_nhan = bn.id
            WHERE bn.ho_ten LIKE ?
               OR dk.so_thu_tu LIKE ?
            """;

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            String value = "%" + keyword.trim() + "%";
            pstmt.setString(1, value);
            pstmt.setString(2, value);

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                DangKyKhamBenh dk = new DangKyKhamBenh();

                dk.setId(rs.getInt("id"));
                dk.setMabenhnhan(rs.getInt("ma_benh_nhan"));
                dk.setManhanvien(rs.getInt("ma_nhan_vien"));
                dk.setMachuyenkhoa(rs.getInt("ma_chuyen_khoa"));
                dk.setSothutu(rs.getInt("so_thu_tu"));
                dk.setThoigiandangky(rs.getTimestamp("thoi_gian_dang_ky").toLocalDateTime());
                dk.setTrangthai(TRANGTHAI.valueOf(rs.getString("trang_thai")));
                dk.setGhichu(rs.getString("ghi_chu"));

                list.add(dk);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }
    

    public boolean DangKySTTmoidobenhnhanquaylaikham(int idDangKy) {
        Connection conn = null;
        PreparedStatement pstmGet = null;
        PreparedStatement pstmUpdate = null;
        ResultSet rs = null;
        

        String sqlGetMin = """
            SELECT MIN(so_thu_tu) 
            FROM dang_ky_kham_benh
            WHERE DATE(thoi_gian_dang_ky) = CURDATE() 
            AND (trang_thai = 'CHO_KHAM_BS')
        """;


        String sqlUpdate = "UPDATE dang_ky_kham_benh SET so_thu_tu = ?, trang_thai = 'CHO_KHAM' WHERE id = ?";

        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
       
            conn.setAutoCommit(false); 

    
            pstmGet = conn.prepareStatement(sqlGetMin);
            rs = pstmGet.executeQuery();
            
            int sttMoi = 1; // 
            
            if (rs.next()) {
                int minStt = rs.getInt(1);
                if (minStt > 0) {
                
                    sttMoi = minStt  ; 
                }
            }

  
            pstmUpdate = conn.prepareStatement(sqlUpdate);
            pstmUpdate.setInt(1, sttMoi);
            pstmUpdate.setInt(2, idDangKy);
            
            int rowAffected = pstmUpdate.executeUpdate();
            
            if (rowAffected > 0) {
                conn.commit(); 
                return true;
            } else {
                conn.rollback();
                return false;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            try { 
            	
            	if (conn != null)
            		conn.rollback(); 
            	} 
            catch (SQLException ex) {
            	
            }
            return false;
        } finally {

            try {
                if (rs != null) rs.close();
                if (pstmGet != null) pstmGet.close();
                if (pstmUpdate != null) pstmUpdate.close();
                if (conn != null) conn.close();
            } catch (SQLException e) { e.printStackTrace(); }
        }
    }
    
    // lay tat ca benh nhan trong ngay hom nay của trợ lý tổng quát

//    public List<DangKyKhamBenh> getAlltodayTroLyCK(String tenChuyenKhoa) {
//        List<DangKyKhamBenh> list = new ArrayList<>();
//        
//      
//        String query = """
//            SELECT dk.* 
//            FROM dang_ky_kham_benh dk
//            JOIN chuyen_khoa ck ON dk.ma_chuyen_khoa = ck.ma_chuyen_khoa
//            WHERE DATE(dk.thoi_gian_dang_ky) = CURDATE() 
//              AND dk.trang_thai = 'CHO_KHAM' 
//              AND ck.ten_chuyen_khoa = ? 
//            ORDER BY dk.so_thu_tu ASC
//        """;
//
//        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
//             PreparedStatement pstm = conn.prepareStatement(query)) {
//            
//
//            pstm.setString(1, tenChuyenKhoa);
//            
//            ResultSet rs = pstm.executeQuery();
//            while (rs.next()) {
//                DangKyKhamBenh dk = new DangKyKhamBenh();
//
//                dk.setId(rs.getInt("id"));
//                dk.setMabenhnhan(rs.getInt("ma_benh_nhan"));
//                dk.setManhanvien(rs.getInt("ma_nhan_vien"));
//                dk.setMachuyenkhoa(rs.getInt("ma_chuyen_khoa"));
//                dk.setSothutu(rs.getInt("so_thu_tu"));
//                dk.setThoigiandangky(rs.getTimestamp("thoi_gian_dang_ky").toLocalDateTime());
//                dk.setTrangthai(TRANGTHAI.valueOf(rs.getString("trang_thai")));
//                dk.setGhichu(rs.getString("ghi_chu"));
//
//                list.add(dk);
//            }
//
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        return list;
//    }
    
    
    public List<DangKyKhamBenh> getAlltodayTroLyCK(int mack) {
        List<DangKyKhamBenh> list = new ArrayList<>();
        String query = """
				SELECT 
				    dk.id, 
				    dk.ma_benh_nhan, 
				    dk.ma_nhan_vien,
				    dk.ma_phieu_kham, 
				    dk.ma_chi_tiet_chi_dinh, 
				    dk.ma_chuyen_khoa AS ma_khoa_dk,
				    dk.so_thu_tu, 
				    dk.thoi_gian_dang_ky, 
				    dk.trang_thai AS trang_thai_dk,    
				    dk.ghi_chu,
			
				    dv.ten_dich_vu AS ten_dich_vu
				FROM dang_ky_kham_benh dk
		
				LEFT JOIN chi_tiet_chi_dinh ctcd ON dk.ma_chi_tiet_chi_dinh = ctcd.id
				LEFT JOIN dich_vu dv ON ctcd.ma_dich_vu = dv.ma_dich_vu
				
				WHERE DATE(dk.thoi_gian_dang_ky) = CURDATE() 
				    AND dk.trang_thai NOT IN ('VANG_MAT', 'HUY', 'DA_KHAM')
				    AND dk.ma_chuyen_khoa = ? 
				
				ORDER BY dk.so_thu_tu ASC
        """;

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement pstm = conn.prepareStatement(query)) {
            
            pstm.setInt(1, mack); 
 

            try (ResultSet rs = pstm.executeQuery()) {
                while (rs.next()) {
                    DangKyKhamBenh dk = new DangKyKhamBenh();
                    dk.setId(rs.getInt("id"));
                    dk.setMabenhnhan(rs.getInt("ma_benh_nhan"));
                    dk.setMaChiTietChiDinh(rs.getInt("ma_chi_tiet_chi_dinh"));
                    dk.setMachuyenkhoa(rs.getInt("ma_khoa_dk"));
                    dk.setSothutu(rs.getInt("so_thu_tu"));        
                    dk.setThoigiandangky(rs.getTimestamp("thoi_gian_dang_ky").toLocalDateTime());
                    dk.setTrangthai(DangKyKhamBenh.TRANGTHAI.valueOf(rs.getString("trang_thai_dk")));
                    dk.setMaPhieuKham(rs.getInt("ma_phieu_kham"));
                    dk.setGhichu(rs.getString("ghi_chu"));
                    
                    String services = rs.getString("ten_dich_vu");
                    dk.setTenDichVu(services != null ? services : "Khám chuyên khoa");
                    
                    list.add(dk);
                }
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return list;
    }
    public List<DangKyKhamBenh> getAllVangMatCKToDay(String ck) {
        List<DangKyKhamBenh> list = new ArrayList<>();
        String query = """
                SELECT dk.* 
                FROM dang_ky_kham_benh dk
                JOIN chuyen_khoa ck ON dk.ma_chuyen_khoa = ck.ma_chuyen_khoa
                WHERE DATE(dk.thoi_gian_dang_ky) = CURDATE() 
                  AND dk.trang_thai = 'VANG_MAT' 
                  AND ck.ten_chuyen_khoa = ? 
                ORDER BY dk.so_thu_tu ASC
            """;

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement pstm = conn.prepareStatement(query)){
            pstm.setString(1, ck);
             ResultSet rs = pstm.executeQuery() ;
    
            while (rs.next()) {
                DangKyKhamBenh dk = new DangKyKhamBenh();

                dk.setId(rs.getInt("id"));
                dk.setMabenhnhan(rs.getInt("ma_benh_nhan"));
                dk.setManhanvien(rs.getInt("ma_nhan_vien"));
                dk.setMachuyenkhoa(rs.getInt("ma_chuyen_khoa"));
                dk.setSothutu(rs.getInt("so_thu_tu"));
                dk.setThoigiandangky(rs.getTimestamp("thoi_gian_dang_ky").toLocalDateTime());
                dk.setTrangthai(TRANGTHAI.valueOf(rs.getString("trang_thai")));
                dk.setGhichu(rs.getString("ghi_chu"));

                list.add(dk);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }
    
    
    public List<DangKyKhamBenh> getAllLichSuDangKy() {
        List<DangKyKhamBenh> list = new ArrayList<>();
        String sql = """
            SELECT dk.*, bn.ho_ten, bn.so_dien_thoai, ck.ten_chuyen_khoa
            FROM dang_ky_kham_benh dk
            JOIN benh_nhan bn ON dk.ma_benh_nhan = bn.ma_benh_nhan
            JOIN chuyen_khoa ck ON dk.ma_chuyen_khoa = ck.ma_chuyen_khoa
            ORDER BY dk.thoi_gian_dang_ky DESC
        """;

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(mapRowToModel(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }


    public List<DangKyKhamBenh> timKiemLichSuTheoText(String keyword) {
        List<DangKyKhamBenh> list = new ArrayList<>();
        String sql = """
            SELECT dk.*, bn.ho_ten, bn.so_dien_thoai, ck.ten_chuyen_khoa
            FROM dang_ky_kham_benh dk
            JOIN benh_nhan bn ON dk.ma_benh_nhan = bn.ma_benh_nhan
            JOIN chuyen_khoa ck ON dk.ma_chuyen_khoa = ck.ma_chuyen_khoa
            WHERE bn.ho_ten LIKE ? 
               OR bn.so_dien_thoai LIKE ?
               OR CAST(bn.cccd AS CHAR) LIKE ?
            ORDER BY dk.thoi_gian_dang_ky DESC
        """;

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement ps = conn.prepareStatement(sql)) {

            String val = "%" + keyword.trim() + "%";
            ps.setString(1, val);
            ps.setString(2, val);
            ps.setString(3, val);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(mapRowToModel(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<DangKyKhamBenh> locLichSuTheoNgay(String tuNgay, String denNgay) {
        List<DangKyKhamBenh> list = new ArrayList<>();
        String sql = """
            SELECT dk.*, bn.ho_ten, bn.so_dien_thoai, ck.ten_chuyen_khoa
            FROM dang_ky_kham_benh dk
            JOIN benh_nhan bn ON dk.ma_benh_nhan = bn.id
            JOIN chuyen_khoa ck ON dk.ma_chuyen_khoa = ck.ma_chuyen_khoa
            WHERE DATE(dk.thoi_gian_dang_ky) BETWEEN ? AND ?
            ORDER BY dk.thoi_gian_dang_ky DESC
        """;

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, tuNgay);
            ps.setString(2, denNgay);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(mapRowToModel(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }


    private DangKyKhamBenh mapRowToModel(ResultSet rs) throws SQLException {
        DangKyKhamBenh dk = new DangKyKhamBenh();
        dk.setId(rs.getInt("id"));
        dk.setMabenhnhan(rs.getInt("ma_benh_nhan"));
        dk.setManhanvien(rs.getInt("ma_nhan_vien"));
        dk.setMachuyenkhoa(rs.getInt("ma_chuyen_khoa"));
        dk.setSothutu(rs.getInt("so_thu_tu"));
        dk.setThoigiandangky(rs.getTimestamp("thoi_gian_dang_ky").toLocalDateTime());
        dk.setTrangthai(TRANGTHAI.valueOf(rs.getString("trang_thai")));
        

        
        return dk;
    }
    
    public boolean taoDangKyDichVuCLS(int maBN, int maNV, int maKhoaCLS, String ghiChu,int mapk, int mactchidinh) {

        int nextSTT = 1;
        String sqlMaxSTT = "SELECT IFNULL(MAX(so_thu_tu), 0) + 1 FROM dang_ky_kham_benh "
                         + "WHERE ma_chuyen_khoa = ? AND DATE(thoi_gian_dang_ky) = CURDATE()";
        

        String sqlInsert = "INSERT INTO dang_ky_kham_benh (ma_benh_nhan, ma_nhan_vien, ma_chuyen_khoa, so_thu_tu, thoi_gian_dang_ky, trang_thai, ghi_chu,ma_phieu_kham,ma_chi_tiet_chi_dinh) "
                         + "VALUES (?, ?, ?, ?, NOW(), 'CHO_CLS', ?,?,?)";


        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)) {

            try (PreparedStatement ps1 = conn.prepareStatement(sqlMaxSTT)) {
                ps1.setInt(1, maKhoaCLS);
                ResultSet rs = ps1.executeQuery();
                if (rs.next()) 
                	nextSTT = rs.getInt(1);
            }

 
            try (PreparedStatement ps2 = conn.prepareStatement(sqlInsert)) {
                ps2.setInt(1, maBN);
                ps2.setInt(2, maNV);
                ps2.setInt(3, maKhoaCLS);
                ps2.setInt(4, nextSTT);
                ps2.setString(5, ghiChu);
                ps2.setInt(6, mapk);
                ps2.setInt(7, mactchidinh);
          
                return ps2.executeUpdate() > 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean kiemTraDaDangKyTrongNgay(int maBN, int maCK) {

        String sql = "SELECT COUNT(*) FROM dang_ky_kham_benh "
                   + "WHERE ma_benh_nhan = ? AND ma_chuyen_khoa = ? "
                   + "AND DATE(thoi_gian_dang_ky) = CURDATE() "
                   + "AND trang_thai NOT IN ('HUY')"; 

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setInt(1, maBN);
            ps.setInt(2, maCK);
            
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1) > 0; 
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public int demSoCaThucTeChuaHuy(int maCK) {
        int count = 0;
        String sql = "SELECT COUNT(*) FROM dang_ky_kham_benh " +
                     "WHERE ma_chuyen_khoa = ? AND DATE(thoi_gian_dang_ky) = CURDATE() " +
                     "AND trang_thai != 'HUY'";
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, maCK);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) count = rs.getInt(1);
        } catch (SQLException e) { e.printStackTrace(); }
        return count;
    }
}