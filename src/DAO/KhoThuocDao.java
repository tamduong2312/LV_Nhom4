package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import MODEL.ChiTietPhieuNhap;
import MODEL.KhoThuoc;
import MODEL.PhieuNhapThuoc;

public class KhoThuocDao {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/dbphongkham";
    private static final String USER = "root";
    private static final String PASS = "";
    
    
    public List<KhoThuoc> getAllKhoThuoc() {
        List<KhoThuoc> list = new ArrayList<>();
        String query = "SELECT * FROM kho_thuoc";

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement pstm = conn.prepareStatement(query);
             ResultSet rs = pstm.executeQuery()) {

            while (rs.next()) {
            	KhoThuoc t = new KhoThuoc();

                t.setIdKho(rs.getInt("id_kho"));
           
                t.setMaThuoc(rs.getInt("ma_thuoc"));
      
                t.setSoLuongTon(rs.getInt("so_luong_ton"));
                t.setNgayCapNhatCuoi(rs.getDate("ngay_cap_nhat_cuoi").toLocalDate());
        

                list.add(t);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }
    

    public boolean capNhatSoLuongTon(int maThuoc, int soLuongMoi) {
 
        String sql = "UPDATE kho_thuoc SET so_luong_ton = ? WHERE ma_thuoc = ?";
        
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             java.sql.PreparedStatement pst = conn.prepareStatement(sql)) {
             
            pst.setInt(1, soLuongMoi);
            pst.setInt(2, maThuoc);
            
            return pst.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean checkThuocTonTai(int maThuoc) {
        String sql = "SELECT count(*) FROM kho_thuoc WHERE ma_thuoc = ?";
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement pstm = conn.prepareStatement(sql)) {
            pstm.setInt(1, maThuoc);
            ResultSet rs = pstm.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


    public boolean updateSoLuongTon(int maThuoc, int soLuongThem) {
        String sql = "UPDATE kho_thuoc SET so_luong_ton = so_luong_ton + ?, ngay_cap_nhat_cuoi = NOW() WHERE ma_thuoc = ?";
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement pstm = conn.prepareStatement(sql)) {
            pstm.setInt(1, soLuongThem);
            pstm.setInt(2, maThuoc);
            return pstm.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    public boolean insertKhoMoi(int maThuoc, int soLuong) {
        String sql = "INSERT INTO kho_thuoc (ma_thuoc, so_luong_ton, ngay_cap_nhat_cuoi) VALUES (?, ?, NOW())";
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement pstm = conn.prepareStatement(sql)) {
            pstm.setInt(1, maThuoc);
            pstm.setInt(2, soLuong);
            return pstm.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    public int insertPhieuNhap(PhieuNhapThuoc pn) {
        String sql = "INSERT INTO phieu_nhap_thuoc (ngay_nhap, ma_nhan_vien_nhap, ma_nha_cung_cap, tong_tien_nhap, trang_thai) VALUES (NOW(), ?, ?, 0, 'Đang tạo')";
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement pstm = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            pstm.setInt(1, pn.getMaNhanVienNhap());
            pstm.setInt(2, pn.getMaNhaCungCap());
            pstm.executeUpdate();
            
            ResultSet rs = pstm.getGeneratedKeys();
            if (rs.next()) {
                return rs.getInt(1); 
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }


    public boolean insertChiTietPhieuNhap(ChiTietPhieuNhap ct) {
        String sql = "INSERT INTO ct_phieu_nhap_thuoc (ma_phieu_nhap_thuoc, ma_thuoc, so_luong_nhap, don_gia_nhap, thanh_tien) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement pstm = conn.prepareStatement(sql)) {
            pstm.setInt(1, ct.getMaPhieuNhapThuoc());
            pstm.setInt(2, ct.getMaThuoc());
            pstm.setInt(3, ct.getSoLuongNhap());
            pstm.setDouble(4, ct.getDonGiaNhap());
            pstm.setDouble(5, ct.getThanhTien());
            return pstm.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    public boolean hoanTatPhieuNhap(int maPhieu, double tongTien) {
        String sql = "UPDATE phieu_nhap_thuoc SET tong_tien_nhap = ?, trang_thai = 'Hoàn thành' WHERE ma_phieu_nhap_thuoc = ?";
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement pstm = conn.prepareStatement(sql)) {
            pstm.setDouble(1, tongTien);
            pstm.setInt(2, maPhieu);
            return pstm.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    public List<PhieuNhapThuoc> getAllPhieuNhap() {
        List<PhieuNhapThuoc> list = new ArrayList<>();

        String query = "SELECT * FROM phieu_nhap_thuoc ORDER BY ngay_nhap DESC"; 

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement pstm = conn.prepareStatement(query);
             ResultSet rs = pstm.executeQuery()) {

            while (rs.next()) {
                PhieuNhapThuoc pn = new PhieuNhapThuoc();
                pn.setMaPhieuNhapThuoc(rs.getInt("ma_phieu_nhap_thuoc"));
                pn.setNgayNhap(rs.getDate("ngay_nhap").toLocalDate()); 
                pn.setMaNhanVienNhap(rs.getInt("ma_nhan_vien_nhap"));
                pn.setMaNhaCungCap(rs.getInt("ma_nha_cung_cap"));
                pn.setTongTienNhap(rs.getDouble("tong_tien_nhap"));
                pn.setTrangThai(rs.getString("trang_thai"));
                pn.setGhiChu(rs.getString("ghi_chu")); 
                
                list.add(pn);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<ChiTietPhieuNhap> getChiTietByMaPhieu(int maPhieu) {
        List<ChiTietPhieuNhap> list = new ArrayList<>();
        String sql = "SELECT * FROM ct_phieu_nhap_thuoc WHERE ma_phieu_nhap_thuoc = ?";
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement pstm = conn.prepareStatement(sql)) {
            pstm.setInt(1, maPhieu);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                ChiTietPhieuNhap ct = new ChiTietPhieuNhap();

                ct.setId(rs.getInt("id")); 
                ct.setMaPhieuNhapThuoc(rs.getInt("ma_phieu_nhap_thuoc"));
                ct.setMaThuoc(rs.getInt("ma_thuoc"));
                ct.setSoLuongNhap(rs.getInt("so_luong_nhap"));
                ct.setDonGiaNhap(rs.getLong("don_gia_nhap"));
                ct.setThanhTien(rs.getLong("thanh_tien"));
                list.add(ct);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public PhieuNhapThuoc getPhieuNhapById(int maPhieu) {
        String query = "SELECT * FROM phieu_nhap_thuoc WHERE ma_phieu_nhap_thuoc = ?";
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement pstm = conn.prepareStatement(query)) {
            
            pstm.setInt(1, maPhieu);
            ResultSet rs = pstm.executeQuery();
            
            if (rs.next()) {
                PhieuNhapThuoc pn = new PhieuNhapThuoc();
                pn.setMaPhieuNhapThuoc(rs.getInt("ma_phieu_nhap_thuoc"));
                pn.setNgayNhap(rs.getDate("ngay_nhap").toLocalDate());
                pn.setMaNhanVienNhap(rs.getInt("ma_nhan_vien_nhap"));
                pn.setMaNhaCungCap(rs.getInt("ma_nha_cung_cap"));
                pn.setTongTienNhap(rs.getDouble("tong_tien_nhap"));
                pn.setTrangThai(rs.getString("trang_thai"));
                return pn;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    public int xoactphieunhap(int id) {
        int success = 0;
        String query = "DELETE FROM ct_phieu_nhap_thuoc WHERE ma_phieu_nhap_thuoc  = ?";
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
    
    public List<KhoThuoc> timKiemKhoThuoc(String keyword) {
        List<KhoThuoc> list = new ArrayList<>();

        String sql = "SELECT k.* FROM kho_thuoc k " +
                     "JOIN thuoc t ON k.ma_thuoc = t.ma_thuoc " +
                     "WHERE t.ten_thuoc LIKE ? OR k.ma_thuoc LIKE ?";
                     
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement pstm = conn.prepareStatement(sql)) {
            
            String search = "%" + keyword + "%";
            pstm.setString(1, search);
            pstm.setString(2, search); 
            
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                KhoThuoc k = new KhoThuoc();
                k.setIdKho(rs.getInt("id_kho"));
                k.setMaThuoc(rs.getInt("ma_thuoc"));
                k.setSoLuongTon(rs.getInt("so_luong_ton"));
                k.setNgayCapNhatCuoi(rs.getDate("ngay_cap_nhat_cuoi").toLocalDate());
                list.add(k);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    
    
    

    public int getSoLuongTon(int maThuoc) {
        int soLuong = 0;
        String sql = "SELECT so_luong_ton FROM kho_thuoc WHERE ma_thuoc = ?";
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             java.sql.PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setInt(1, maThuoc);
            java.sql.ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
                soLuong = rs.getInt("so_luong_ton");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return soLuong;
    }
    
    
    public String getHsd(int maThuoc) {
        LocalDate hsd  ;
        String q = "";
        String sql = "SELECT han_su_dung FROM thuoc WHERE ma_thuoc = ?";
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             java.sql.PreparedStatement ps = conn.prepareStatement(sql)) {
            
            ps.setInt(1, maThuoc);
            java.sql.ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
            	hsd = rs.getDate("han_su_dung").toLocalDate();
            	q = hsd.toString();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return q;
    }
    public boolean updateTruSoLuongTon(String tenThuoc, int soLuongTru) {
 
        String sql = "UPDATE kho_thuoc k " +
                     "JOIN thuoc t ON k.ma_thuoc = t.ma_thuoc " +
                     "SET k.so_luong_ton = k.so_luong_ton - ? " +
                     "WHERE t.ten_thuoc = ?";
        
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement pst = conn.prepareStatement(sql)) {
             
            pst.setInt(1, soLuongTru);
            pst.setString(2, tenThuoc);
            
            return pst.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


}