package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;


import MODEL.BangPhanCongCaLam;
import MODEL.NhanVien;

public class BangPhanCongLamViecDao {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/dbphongkham";
    private static final String USER = "root";
    private static final String PASS = "";


    public List<NhanVien> getNVChuaCoCalam(String chucvu , String chuyenkhoa) {
        List<NhanVien> list = new ArrayList<>();
        
        StringBuilder query = new StringBuilder();
        query.append("SELECT nv.ma_nhan_vien, nv.ho_ten, nv.chuc_vu, nv.chuyen_khoa ");
        query.append("FROM nhan_vien nv ");
        query.append("LEFT JOIN bang_phan_cong_ca_lam tk ON nv.ma_nhan_vien = tk.ma_nhan_vien ");
        query.append("WHERE tk.ma_nhan_vien IS NULL AND nv.chuc_vu <> 'admin'  ");


        if (chucvu != null && !chucvu.isEmpty() ) {
            query.append("AND nv.chuc_vu = ? ");
            query.append("AND nv.chuyen_khoa = ? ");
            query.append("OR nv.chuyen_khoa IS NULL ");
        }

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement pstmt = conn.prepareStatement(query.toString())) {

            if (chucvu != null && !chucvu.isEmpty()) {
                pstmt.setString(1, chucvu);
            }
    
                pstmt.setString(2, chuyenkhoa);
            
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    NhanVien nv = new NhanVien();
                    nv.setMaNV(rs.getInt("ma_nhan_vien"));
                    nv.setHoTen(rs.getString("ho_ten"));
                    nv.setChucVu(rs.getString("chuc_vu"));
                    nv.setMaChuyenKhoa(rs.getString("chuyen_khoa")); 
                    list.add(nv);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<BangPhanCongCaLam> getNVDaCoCalam(String chucvu , String chuyenkhoa) {
        List<BangPhanCongCaLam> list = new ArrayList<>();
        
        String query = "SELECT b.*, nv.ho_ten, nv.chuc_vu " +
                "FROM bang_phan_cong_ca_lam b " +
                "JOIN nhan_vien nv ON b.ma_nhan_vien = nv.ma_nhan_vien " +
                "WHERE nv.chuc_vu = ? AND nv.chuyen_khoa = ? ";


   
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement pstmt = conn.prepareStatement(query.toString())) {

            if (chucvu != null && !chucvu.isEmpty()) {
                pstmt.setString(1, chucvu);
            }

                pstmt.setString(2, chuyenkhoa);
            
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                	BangPhanCongCaLam nv = new BangPhanCongCaLam();
                	nv.setId(rs.getInt("id"));
                	nv.setMaNhanVien(rs.getInt("ma_nhan_vien"));
                	nv.setPhong(rs.getString("phong"));
                	nv.setGioLam(rs.getTime("gio_lam"));
                    nv.setGioKetThuc(rs.getTime("gio_ket_thuc"));
                    nv.setThu(rs.getString("thu"));
                    list.add(nv);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    
    

    public List<BangPhanCongCaLam> getPhanCongTheoThu(String chucVu, String chuyenKhoa, String thu) {
        List<BangPhanCongCaLam> list = new ArrayList<>();
        StringBuilder sql = new StringBuilder();
        
     
        sql.append("SELECT pc.*, nv.ho_ten ");
        sql.append("FROM bang_phan_cong_ca_lam pc ");
        sql.append("JOIN nhan_vien nv ON pc.ma_nhan_vien = nv.ma_nhan_vien ");
        sql.append("WHERE pc.thu = ? AND nv.chuc_vu = ? ");


        if (chuyenKhoa != null && !chuyenKhoa.isEmpty()) {
            sql.append("AND nv.chuyen_khoa = ? ");
        } else {
            sql.append("AND (nv.chuyen_khoa IS NULL OR nv.chuyen_khoa = '') ");
        }

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement ps = conn.prepareStatement(sql.toString())) {
            
            ps.setString(1, thu);
            ps.setString(2, chucVu);
            
            if (chuyenKhoa != null && !chuyenKhoa.isEmpty()) {
                ps.setString(3, chuyenKhoa);
            }

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                BangPhanCongCaLam pc = new BangPhanCongCaLam();
                pc.setMaNhanVien(rs.getInt("ma_nhan_vien"));
                pc.setId(rs.getInt("id"));
                pc.setPhong(rs.getString("phong"));
                pc.setGioLam(rs.getTime("gio_lam"));
                pc.setGioKetThuc(rs.getTime("gio_ket_thuc"));
                pc.setThu(rs.getString("thu"));
             
                list.add(pc);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    
    
    public List<BangPhanCongCaLam> getPhanCongBSTheoThu(String chucVu, String chuyenKhoa, String thu) {
        List<BangPhanCongCaLam> list = new ArrayList<>();
        StringBuilder sql = new StringBuilder();
        

        sql.append("SELECT pc.*, nv.ho_ten ");
        sql.append("FROM bang_phan_cong_ca_lam pc ");
        sql.append("JOIN nhan_vien nv ON pc.ma_nhan_vien = nv.ma_nhan_vien ");
        sql.append("WHERE pc.thu = ? "); 

   
        if (chucVu != null && !chucVu.isEmpty()) {
            sql.append("AND nv.chuc_vu LIKE ? ");
        }

        if (chuyenKhoa != null && !chuyenKhoa.isEmpty()) {
            sql.append("AND nv.chuyen_khoa = ? ");
        }


        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement ps = conn.prepareStatement(sql.toString())) {
            
            int parameterIndex = 1;
            ps.setString(parameterIndex++, thu);
            
            if (chucVu != null && !chucVu.isEmpty()) {
   
                ps.setString(parameterIndex++, chucVu + "%"); 
            }
            
            if (chuyenKhoa != null && !chuyenKhoa.isEmpty()) {
                ps.setString(parameterIndex++, chuyenKhoa);
            }

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                BangPhanCongCaLam pc = new BangPhanCongCaLam();
  
                // pc.setId(rs.getInt("id")); 
                pc.setMaNhanVien(rs.getInt("ma_nhan_vien"));
                pc.setPhong(rs.getString("phong"));
                pc.setGioLam(rs.getTime("gio_lam"));
                pc.setGioKetThuc(rs.getTime("gio_ket_thuc"));
                pc.setThu(rs.getString("thu"));
             
                list.add(pc);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    //  Lấy danh sách KHẢ DỤNG (Chưa làm việc trong ngày đó) 

    public List<NhanVien> getNVKhadungTheoThu(String chucVu, String chuyenKhoa, String thu) {
        List<NhanVien> danhSachNV = new ArrayList<>();
        String cauLenhSQL = "";
        

        boolean coChuyenKhoa = (chuyenKhoa != null && !chuyenKhoa.isEmpty());

  
        if (coChuyenKhoa) {

            cauLenhSQL = "SELECT * FROM nhan_vien " +
                         "WHERE chuc_vu = ? " +
                         "AND chuyen_khoa = ? " +
                         "AND ma_nhan_vien NOT IN (SELECT ma_nhan_vien FROM bang_phan_cong_ca_lam WHERE thu = ?)";
        } else {
       

            cauLenhSQL = "SELECT * FROM nhan_vien " +
                         "WHERE chuc_vu = ? " +
                         "AND (chuyen_khoa IS NULL OR chuyen_khoa = '') " +
                         "AND ma_nhan_vien NOT IN (SELECT ma_nhan_vien FROM bang_phan_cong_ca_lam WHERE thu = ?)";
        }


        try {
            Connection ketNoi = DriverManager.getConnection(DB_URL, USER, PASS);
            PreparedStatement boXuLy = ketNoi.prepareStatement(cauLenhSQL);


            if (coChuyenKhoa) {

                boXuLy.setString(1, chucVu);
                boXuLy.setString(2, chuyenKhoa);
                boXuLy.setString(3, thu);
            } else {

                boXuLy.setString(1, chucVu);
                boXuLy.setString(2, thu);
            }


            ResultSet ketQua = boXuLy.executeQuery();
            while (ketQua.next()) {
                NhanVien nv = new NhanVien();
                nv.setMaNV(ketQua.getInt("ma_nhan_vien"));
                nv.setHoTen(ketQua.getString("ho_ten"));
                nv.setChucVu(ketQua.getString("chuc_vu"));
                nv.setMaChuyenKhoa(ketQua.getString("chuyen_khoa"));
                
                danhSachNV.add(nv);
            }
            

            ketNoi.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return danhSachNV;
    }
    
    // Lấy toàn bộ bảng phân công 
    public List<BangPhanCongCaLam> getAllBangPhanCong() {
        List<BangPhanCongCaLam> list = new ArrayList<>();
        String query = "SELECT b.*, nv.ho_ten, nv.chuc_vu " +
                       "FROM bang_phan_cong_ca_lam b " +
                       "JOIN nhan_vien nv ON b.ma_nhan_vien = nv.ma_nhan_vien " +
                       "ORDER BY field(b.thu, 'Thứ 2', 'Thứ 3', 'Thứ 4', 'Thứ 5', 'Thứ 6', 'Thứ 7', 'Chủ Nhật'), b.gio_lam";

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement pstmt = conn.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
            	BangPhanCongCaLam pc = new BangPhanCongCaLam();
                pc.setId(rs.getInt("id"));
                pc.setMaNhanVien(rs.getInt("ma_nhan_vien"));
                pc.setPhong(rs.getString("phong"));
                pc.setGioLam(rs.getTime("gio_lam"));
                pc.setGioKetThuc(rs.getTime("gio_ket_thuc"));
                pc.setThu(rs.getString("thu"));
          
                

                
                list.add(pc);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public boolean themPhanCong(BangPhanCongCaLam pc) {
        String query = "INSERT INTO bang_phan_cong_ca_lam (ma_nhan_vien, phong, gio_lam, gio_ket_thuc, thu) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement pstmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            
            pstmt.setInt(1, pc.getMaNhanVien());
            pstmt.setString(2, pc.getPhong());
            pstmt.setTime(3, pc.getGioLam());
            pstmt.setTime(4, pc.getGioKetThuc());
            pstmt.setString(5, pc.getThu());
            
            int rowAffected = pstmt.executeUpdate();
            if (rowAffected > 0) {
                ResultSet rs = pstmt.getGeneratedKeys();
        
                if (rs.next()) {
                    pc.setId(rs.getInt(1)); 
                }
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

  
    public boolean xoaPhanCong(int idPhanCong) {
        String query = "DELETE FROM bang_phan_cong_ca_lam WHERE id = ?";
        
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            
            pstmt.setInt(1, idPhanCong);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
 
    public boolean xoaPhanCongTheoNV(int maNV, String thu) {
        String query = "DELETE FROM bang_phan_cong_ca_lam WHERE id = ? AND thu = ?";
        
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            
            pstmt.setInt(1, maNV);
            pstmt.setString(2, thu);
            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<BangPhanCongCaLam> getCaLamCuaNhanVien(int maNV) {
        List<BangPhanCongCaLam> list = new ArrayList<>();
        

        String query = "SELECT ma_nhan_vien, phong, gio_lam, gio_ket_thuc, thu " +
                       "FROM bang_phan_cong_ca_lam " +
                       "WHERE ma_nhan_vien = ?";

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement pstmt = conn.prepareStatement(query)) {


            pstmt.setInt(1, maNV);

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
               
                    BangPhanCongCaLam pc = new BangPhanCongCaLam();
                    
                    pc.setMaNhanVien(rs.getInt("ma_nhan_vien"));
                    pc.setPhong(rs.getString("phong"));
                    pc.setGioLam(rs.getTime("gio_lam"));
                    pc.setGioKetThuc(rs.getTime("gio_ket_thuc"));
                    pc.setThu(rs.getString("thu"));
                    
         

                    list.add(pc);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    
    
    public String getTenNhanVien(int maNV) {
        String ten = "";
        String sql = "SELECT ho_ten FROM nhan_vien WHERE ma_nhan_vien = ?";
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setInt(1, maNV);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    ten = rs.getString("ho_ten");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ten;
    }
    public boolean suaPhanCong(BangPhanCongCaLam pc) {
    
        String query = "UPDATE bang_phan_cong_ca_lam SET phong=?, gio_lam=?, gio_ket_thuc=? WHERE id=?";
        
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            
            pstmt.setString(1, pc.getPhong());
            pstmt.setTime(2, pc.getGioLam());
            pstmt.setTime(3, pc.getGioKetThuc());
            pstmt.setInt(4, pc.getId()); 

            return pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    
    public int tinhToanGioiHanBNTheoLichTruc(String tenChuyenKhoa) {
        // Lấy "Thứ" hiện tại của hệ thống để khớp với cột 'thu' trong DB
        String[] dsThu = {"Chủ Nhật", "Thứ 2", "Thứ 3", "Thứ 4", "Thứ 5", "Thứ 6", "Thứ 7"};
        java.util.Calendar cal = java.util.Calendar.getInstance();
        String thuHienTai = dsThu[cal.get(java.util.Calendar.DAY_OF_WEEK) - 1];

      
        String sql = "SELECT SUM(TIME_TO_SEC(TIMEDIFF(gio_ket_thuc, gio_lam)) / 60) as tong_phut " +
                     "FROM bang_phan_cong_ca_lam bpc " +
                     "JOIN nhan_vien nv ON bpc.ma_nhan_vien = nv.ma_nhan_vien " +
                     "WHERE nv.chuyen_khoa = ? " +
                     "  AND bpc.thu = ? " +
                     "  AND nv.chuc_vu LIKE 'Bác sĩ%' " +
                     "  AND gio_ket_thuc > gio_lam"; 

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, tenChuyenKhoa);
            pstmt.setString(2, thuHienTai);
            
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    double tongPhut = rs.getDouble("tong_phut");
                    
                    if (tongPhut <= 0) return 0;


                    // Nếu làm trên 5 tiếng (300 phút) thì trừ 60 phút nghỉ trưa
                    double phutThucTe = (tongPhut > 300) ? (tongPhut - 60) : tongPhut;
                    
                    // Định mức 12 phút / 1 bệnh nhân
                    int gioiHan = (int) (phutThucTe / 12);
                    
                    return gioiHan;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    
    public String layTenBacSiTrucTheoKhoaVaThu(String tenCK, String thu) {
        String tenBS = "Chưa có bác sĩ trực";

        String sql = "SELECT nv.ho_ten FROM bang_phan_cong_ca_lam bpc " +
                     "JOIN nhan_vien nv ON bpc.ma_nhan_vien = nv.ma_nhan_vien " +
                     "WHERE nv.chuyen_khoa = ? " +
                     "  AND bpc.thu = ? " +
                     "  AND nv.chuc_vu LIKE 'Bác sĩ%' " + 
                     "LIMIT 1"; 

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, tenCK);
            pstmt.setString(2, thu);
            
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    tenBS = rs.getString("ho_ten");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tenBS;
    }
    
    
    public String kiemTraPhongDaCoNguoi(String phong, String thu, String chucVu, int idExclude) {

        String sql = "SELECT nv.ho_ten FROM bang_phan_cong_ca_lam bpc " +
                     "JOIN nhan_vien nv ON bpc.ma_nhan_vien = nv.ma_nhan_vien " +
                     "WHERE bpc.thu = ? " +
                     "AND bpc.phong = ? " +
                     "AND nv.chuc_vu = ? " + 
                     "AND bpc.id != ?";     

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, thu);
            pstmt.setString(2, phong);
            pstmt.setString(3, chucVu);
            pstmt.setInt(4, idExclude);

            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getString("ho_ten"); 
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; 
    }
    
}