package DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import MODEL.KetQuaCDHA;
import MODEL.KetQuaXetNghiem;
import MODEL.Thuoc;

public class XetNghiemDao {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/dbphongkham";
    private static final String USER = "root";
    private static final String PASS = "";

  
    public KetQuaXetNghiem getByMaChiTiet(int maChiTiet) {
        KetQuaXetNghiem kq = null;
        String sql = "SELECT * FROM ket_qua_xet_nghiem WHERE ma_chi_tiet_chi_dinh = ?";
        
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement pstm = conn.prepareStatement(sql)) {
            pstm.setInt(1, maChiTiet);
            ResultSet rs = pstm.executeQuery();
            if (rs.next()) {
                kq = new KetQuaXetNghiem();
                kq.setId(rs.getInt("id"));
                kq.setMaChiTietChiDinh(rs.getInt("ma_chi_tiet_chi_dinh"));
                kq.setKetLuan(rs.getString("ket_luan"));
                kq.setNguoiThucHien(rs.getInt("nguoi_thuc_hien"));
                
    
                kq.setHongCau(rs.getFloat("hong_cau"));
                kq.setBachCau(rs.getFloat("bach_cau"));
                kq.setTieuCau(rs.getFloat("tieu_cau"));
                kq.setHuyetSacTo(rs.getFloat("huyet_sac_to"));
                kq.setHematocrit(rs.getFloat("hematocrit"));
                kq.setNhomMau(rs.getString("nhom_mau"));
                kq.setTocDoMauLang(rs.getInt("toc_do_mau_lang"));
                kq.setDongMauCoBan(rs.getString("dong_mau_co_ban"));
                
    
                kq.setGlucose(rs.getFloat("glucose"));
                kq.setHbA1c(rs.getFloat("hbA1c"));
                kq.setUre(rs.getFloat("ure"));
                kq.setCreatinine(rs.getFloat("creatinine"));
                kq.setAstGot(rs.getFloat("ast_got"));
                kq.setAltGpt(rs.getFloat("alt_gpt"));
                kq.setGgt(rs.getFloat("ggt"));
                kq.setCholesterolTp(rs.getFloat("cholesterol_tp"));
                kq.setTriglyceride(rs.getFloat("triglyceride"));
                kq.setHdlC(rs.getFloat("hdl_c"));
                kq.setLdlC(rs.getFloat("ldl_c"));
                kq.setAcidUric(rs.getFloat("acid_uric"));
                kq.setBilirubinTp(rs.getFloat("bilirubin_tp"));
                
                // Nước tiểu
                kq.setTyTrong(rs.getFloat("ty_trong"));
                kq.setPh(rs.getFloat("ph"));
                kq.setBachCauNuocTieu(rs.getString("bach_cau_nuoc_tieu"));
                kq.setHongCauNuocTieu(rs.getString("hong_cau_nuoc_tieu"));
                kq.setProteinNuocTieu(rs.getString("protein_nuoc_tieu"));
                kq.setDuongNuocTieu(rs.getString("duong_nuoc_tieu"));
                kq.setNitrit(rs.getString("nitrit"));
                kq.setKetone(rs.getString("ketone"));
                
                // Miễn dịch
                kq.setHbsag(rs.getString("hbsag"));
                kq.setHcvAb(rs.getString("hcv_ab"));
                kq.setHivAb(rs.getString("hiv_ab"));
                kq.setTphaSyphilis(rs.getString("tpha_syphilis"));
                kq.setCrpDinhLuong(rs.getFloat("crp_dinh_luong"));
                kq.setRfDinhLuong(rs.getFloat("rf_dinh_luong"));
                
                kq.setGhiChuThem(rs.getString("ghi_chu_them"));
            }
        } catch (Exception e) { e.printStackTrace(); }
        return kq;
    }


    public boolean insert(KetQuaXetNghiem kq) {
        String sql = "INSERT INTO ket_qua_xet_nghiem ("
                + "ma_chi_tiet_chi_dinh, ket_luan, ngay_thuc_hien, nguoi_thuc_hien, "
                + "hong_cau, bach_cau, tieu_cau, huyet_sac_to, hematocrit, nhom_mau, toc_do_mau_lang, dong_mau_co_ban, "
                + "glucose, hbA1c, ure, creatinine, ast_got, alt_gpt, ggt, cholesterol_tp, triglyceride, hdl_c, ldl_c, acid_uric, bilirubin_tp, "
                + "ty_trong, ph, bach_cau_nuoc_tieu, hong_cau_nuoc_tieu, protein_nuoc_tieu, duong_nuoc_tieu, nitrit, ketone, "
                + "hbsag, hcv_ab, hiv_ab, tpha_syphilis, crp_dinh_luong, rf_dinh_luong, ghi_chu_them) "
                + "VALUES (?, ?, NOW(), ?, "
                + "?, ?, ?, ?, ?, ?, ?, ?, " 
                + "?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, " 
                + "?, ?, ?, ?, ?, ?, ?, ?, " 
                + "?, ?, ?, ?, ?, ?, ?)"; 

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            int i = 1;
            ps.setInt(i++, kq.getMaChiTietChiDinh());
            ps.setString(i++, kq.getKetLuan());
            ps.setInt(i++, kq.getNguoiThucHien());
            
            // Huyết học
            ps.setFloat(i++, kq.getHongCau());
            ps.setFloat(i++, kq.getBachCau());
            ps.setFloat(i++, kq.getTieuCau());
            ps.setFloat(i++, kq.getHuyetSacTo());
            ps.setFloat(i++, kq.getHematocrit());
            ps.setString(i++, kq.getNhomMau());
            ps.setInt(i++, kq.getTocDoMauLang());
            ps.setString(i++, kq.getDongMauCoBan());
            

            ps.setFloat(i++, kq.getGlucose());
            ps.setFloat(i++, kq.getHbA1c());
            ps.setFloat(i++, kq.getUre());
            ps.setFloat(i++, kq.getCreatinine());
            ps.setFloat(i++, kq.getAstGot());
            ps.setFloat(i++, kq.getAltGpt());
            ps.setFloat(i++, kq.getGgt());
            ps.setFloat(i++, kq.getCholesterolTp());
            ps.setFloat(i++, kq.getTriglyceride());
            ps.setFloat(i++, kq.getHdlC());
            ps.setFloat(i++, kq.getLdlC());
            ps.setFloat(i++, kq.getAcidUric());
            ps.setFloat(i++, kq.getBilirubinTp());
            

            ps.setFloat(i++, kq.getTyTrong());
            ps.setFloat(i++, kq.getPh());
            ps.setString(i++, kq.getBachCauNuocTieu());
            ps.setString(i++, kq.getHongCauNuocTieu());
            ps.setString(i++, kq.getProteinNuocTieu());
            ps.setString(i++, kq.getDuongNuocTieu());
            ps.setString(i++, kq.getNitrit());
            ps.setString(i++, kq.getKetone());
            
 
            ps.setString(i++, kq.getHbsag());
            ps.setString(i++, kq.getHcvAb());
            ps.setString(i++, kq.getHivAb());
            ps.setString(i++, kq.getTphaSyphilis());
            ps.setFloat(i++, kq.getCrpDinhLuong());
            ps.setFloat(i++, kq.getRfDinhLuong());
            ps.setString(i++, kq.getGhiChuThem());

            return ps.executeUpdate() > 0;
        } catch (Exception e) { e.printStackTrace(); return false; }
    }

   
    public boolean update(KetQuaXetNghiem kq) {
        String sql = "UPDATE ket_qua_xet_nghiem SET "
                + "ket_luan = ?, nguoi_thuc_hien = ?, ngay_thuc_hien = NOW(), "
         
                + "hong_cau = ?, bach_cau = ?, tieu_cau = ?, huyet_sac_to = ?, hematocrit = ?, nhom_mau = ?, toc_do_mau_lang = ?, dong_mau_co_ban = ?, "
                // Sinh hóa
                + "glucose = ?, hbA1c = ?, ure = ?, creatinine = ?, ast_got = ?, alt_gpt = ?, ggt = ?, cholesterol_tp = ?, triglyceride = ?, hdl_c = ?, ldl_c = ?, acid_uric = ?, bilirubin_tp = ?, "
     
                + "ty_trong = ?, ph = ?, bach_cau_nuoc_tieu = ?, hong_cau_nuoc_tieu = ?, protein_nuoc_tieu = ?, duong_nuoc_tieu = ?, nitrit = ?, ketone = ?, "
   
                + "hbsag = ?, hcv_ab = ?, hiv_ab = ?, tpha_syphilis = ?, crp_dinh_luong = ?, rf_dinh_luong = ?, ghi_chu_them = ? "
                + "WHERE ma_chi_tiet_chi_dinh = ?";

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            int i = 1;
            ps.setString(i++, kq.getKetLuan());
            ps.setInt(i++, kq.getNguoiThucHien());
            
 
            ps.setFloat(i++, kq.getHongCau());
            ps.setFloat(i++, kq.getBachCau());
            ps.setFloat(i++, kq.getTieuCau());
            ps.setFloat(i++, kq.getHuyetSacTo());
            ps.setFloat(i++, kq.getHematocrit());
            ps.setString(i++, kq.getNhomMau());
            ps.setInt(i++, kq.getTocDoMauLang());
            ps.setString(i++, kq.getDongMauCoBan());

            ps.setFloat(i++, kq.getGlucose());
            ps.setFloat(i++, kq.getHbA1c());
            ps.setFloat(i++, kq.getUre());
            ps.setFloat(i++, kq.getCreatinine());
            ps.setFloat(i++, kq.getAstGot());
            ps.setFloat(i++, kq.getAltGpt());
            ps.setFloat(i++, kq.getGgt());
            ps.setFloat(i++, kq.getCholesterolTp());
            ps.setFloat(i++, kq.getTriglyceride());
            ps.setFloat(i++, kq.getHdlC());
            ps.setFloat(i++, kq.getLdlC());
            ps.setFloat(i++, kq.getAcidUric());
            ps.setFloat(i++, kq.getBilirubinTp());
            

            ps.setFloat(i++, kq.getTyTrong());
            ps.setFloat(i++, kq.getPh());
            ps.setString(i++, kq.getBachCauNuocTieu());
            ps.setString(i++, kq.getHongCauNuocTieu());
            ps.setString(i++, kq.getProteinNuocTieu());
            ps.setString(i++, kq.getDuongNuocTieu());
            ps.setString(i++, kq.getNitrit());
            ps.setString(i++, kq.getKetone());
            

            ps.setString(i++, kq.getHbsag());
            ps.setString(i++, kq.getHcvAb());
            ps.setString(i++, kq.getHivAb());
            ps.setString(i++, kq.getTphaSyphilis());
            ps.setFloat(i++, kq.getCrpDinhLuong());
            ps.setFloat(i++, kq.getRfDinhLuong());
            ps.setString(i++, kq.getGhiChuThem());
            

            ps.setInt(i++, kq.getMaChiTietChiDinh());

            return ps.executeUpdate() > 0;
        } catch (Exception e) { e.printStackTrace(); return false; }
    }
    
    
    public boolean BSupdate(KetQuaXetNghiem kq) {
        String sql = "UPDATE ket_qua_xet_nghiem SET "
                + "ket_luan = ?, ma_bs_ket_luan = ?, ngay_thuc_hien = NOW(), "
         
                + "hong_cau = ?, bach_cau = ?, tieu_cau = ?, huyet_sac_to = ?, hematocrit = ?, nhom_mau = ?, toc_do_mau_lang = ?, dong_mau_co_ban = ?, "
         
                + "glucose = ?, hbA1c = ?, ure = ?, creatinine = ?, ast_got = ?, alt_gpt = ?, ggt = ?, cholesterol_tp = ?, triglyceride = ?, hdl_c = ?, ldl_c = ?, acid_uric = ?, bilirubin_tp = ?, "
           
                + "ty_trong = ?, ph = ?, bach_cau_nuoc_tieu = ?, hong_cau_nuoc_tieu = ?, protein_nuoc_tieu = ?, duong_nuoc_tieu = ?, nitrit = ?, ketone = ?, "
           
                + "hbsag = ?, hcv_ab = ?, hiv_ab = ?, tpha_syphilis = ?, crp_dinh_luong = ?, rf_dinh_luong = ?, ghi_chu_them = ? "
                + "WHERE ma_chi_tiet_chi_dinh = ?";

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement ps = conn.prepareStatement(sql)) {
            
            int i = 1;
            ps.setString(i++, kq.getKetLuan());
            ps.setInt(i++, kq.getMaBSketluan());
            

            ps.setFloat(i++, kq.getHongCau());
            ps.setFloat(i++, kq.getBachCau());
            ps.setFloat(i++, kq.getTieuCau());
            ps.setFloat(i++, kq.getHuyetSacTo());
            ps.setFloat(i++, kq.getHematocrit());
            ps.setString(i++, kq.getNhomMau());
            ps.setInt(i++, kq.getTocDoMauLang());
            ps.setString(i++, kq.getDongMauCoBan());
            

            ps.setFloat(i++, kq.getGlucose());
            ps.setFloat(i++, kq.getHbA1c());
            ps.setFloat(i++, kq.getUre());
            ps.setFloat(i++, kq.getCreatinine());
            ps.setFloat(i++, kq.getAstGot());
            ps.setFloat(i++, kq.getAltGpt());
            ps.setFloat(i++, kq.getGgt());
            ps.setFloat(i++, kq.getCholesterolTp());
            ps.setFloat(i++, kq.getTriglyceride());
            ps.setFloat(i++, kq.getHdlC());
            ps.setFloat(i++, kq.getLdlC());
            ps.setFloat(i++, kq.getAcidUric());
            ps.setFloat(i++, kq.getBilirubinTp());
            
 
            ps.setFloat(i++, kq.getTyTrong());
            ps.setFloat(i++, kq.getPh());
            ps.setString(i++, kq.getBachCauNuocTieu());
            ps.setString(i++, kq.getHongCauNuocTieu());
            ps.setString(i++, kq.getProteinNuocTieu());
            ps.setString(i++, kq.getDuongNuocTieu());
            ps.setString(i++, kq.getNitrit());
            ps.setString(i++, kq.getKetone());
            
    
            ps.setString(i++, kq.getHbsag());
            ps.setString(i++, kq.getHcvAb());
            ps.setString(i++, kq.getHivAb());
            ps.setString(i++, kq.getTphaSyphilis());
            ps.setFloat(i++, kq.getCrpDinhLuong());
            ps.setFloat(i++, kq.getRfDinhLuong());
            ps.setString(i++, kq.getGhiChuThem());
            

            ps.setInt(i++, kq.getMaChiTietChiDinh());

            return ps.executeUpdate() > 0;
        } catch (Exception e) { e.printStackTrace(); return false; }
    }


    public void updateStatusChiTiet(int idChiTiet) {

        String sql = "UPDATE chi_tiet_chi_dinh SET trang_thai_dv = 'DA_CO_KET_QUA' WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement pstm = conn.prepareStatement(sql)) {
            pstm.setInt(1, idChiTiet);
            pstm.executeUpdate();
        } catch (Exception e) { e.printStackTrace(); }
    }
    

    public List<KetQuaXetNghiem> GetKqXetNghiemByMaPhieu(int maphieu) {
        List<KetQuaXetNghiem> list = new ArrayList<>();
        String query = "SELECT kq.ket_luan\n"
        		+ "FROM ket_qua_xet_nghiem kq\n"
        		+ "JOIN chi_tiet_chi_dinh ct ON kq.ma_chi_tiet_chi_dinh = ct.id\n"
        		+ "JOIN phieu_chi_dinh pcd ON ct.ma_phieu_chi_dinh = pcd.ma_phieu_chi_dinh\n"
        		+ "WHERE pcd.ma_phieu_kham = ?"
        		;

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement pstm = conn.prepareStatement(query)){
        		
        		pstm.setInt(1, maphieu);
             ResultSet rs = pstm.executeQuery();

            while (rs.next()) {
            	KetQuaXetNghiem t = new KetQuaXetNghiem();
                t.setKetLuan(rs.getString("ket_luan"));
     

                list.add(t);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

    public List<KetQuaCDHA> GetKqCDHAByMaPhieu(int maphieu) {
        List<KetQuaCDHA> list = new ArrayList<>();
        

        String query = "SELECT kq.id, kq.ket_luan\n" 
                + "FROM ket_qua_cdha kq\n"
                + "JOIN chi_tiet_chi_dinh ct ON kq.id_chi_tiet_chi_dinh = ct.id\n"
                + "JOIN phieu_chi_dinh pcd ON ct.ma_phieu_chi_dinh = pcd.ma_phieu_chi_dinh\n"
                + "WHERE pcd.ma_phieu_kham = ?";

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement pstm = conn.prepareStatement(query)) {
                
            pstm.setInt(1, maphieu);
            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {
                KetQuaCDHA t = new KetQuaCDHA();
                

                t.setId(rs.getInt("id")); 
                
                t.setKetLuan(rs.getString("ket_luan"));

                list.add(t);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }
    
    
    public int layIdChiTietByMaPhieuKham(int maDangKy) {
    	   int idChiTiet = 0;
  
    	    String sql = "SELECT ma_chi_tiet_chi_dinh FROM dang_ky_kham_benh WHERE id = ?";

    	    try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
    	         PreparedStatement ps = conn.prepareStatement(sql)) {
    	        
    	        ps.setInt(1, maDangKy);
    	        ResultSet rs = ps.executeQuery();
    	        
    	        if (rs.next()) {
    	            idChiTiet = rs.getInt("ma_chi_tiet_chi_dinh");
    	        }
    	    } catch (SQLException e) {
    	        e.printStackTrace();
    	    }
    	    return idChiTiet;
    	}
    


    public List<KetQuaXetNghiem> GetKqXetNghiemByMaPhieu1(int maPhieuKham) {
        List<KetQuaXetNghiem> list = new ArrayList<>();
        
     
        String sql = """
            SELECT 
                kq.id,             
                kq.ket_luan
            FROM 
                ket_qua_xet_nghiem kq
            INNER JOIN 
                chi_tiet_chi_dinh ct ON kq.ma_chi_tiet_chi_dinh = ct.id
            INNER JOIN 
                phieu_chi_dinh pcd ON ct.ma_phieu_chi_dinh = pcd.ma_phieu_chi_dinh
            WHERE 
                pcd.ma_phieu_kham = ?
        """;

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement ps = conn.prepareStatement(sql)) {
             
            ps.setInt(1, maPhieuKham);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                KetQuaXetNghiem obj = new KetQuaXetNghiem();
                
    
                obj.setId(rs.getInt("id")); 
                
                obj.setKetLuan(rs.getString("ket_luan"));
                
                list.add(obj);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    
    public List<KetQuaXetNghiem> GetKqXetNghiemByMaPhieu2(int idKetQua) { 
        List<KetQuaXetNghiem> list = new ArrayList<>();

        String sql = "SELECT * FROM ket_qua_xet_nghiem kq WHERE kq.id = ?";

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement ps = conn.prepareStatement(sql)) {
             
            ps.setInt(1, idKetQua);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {
                KetQuaXetNghiem kq = new KetQuaXetNghiem();
                
      
                kq.setId(rs.getInt("id"));
                kq.setKetLuan(rs.getString("ket_luan"));
                kq.setGhiChuThem(rs.getString("ghi_chu_them"));
                
             
                kq.setHongCau(rs.getFloat("hong_cau"));
                kq.setBachCau(rs.getFloat("bach_cau"));
                kq.setTieuCau(rs.getFloat("tieu_cau"));
                kq.setHuyetSacTo(rs.getFloat("huyet_sac_to"));
                kq.setHematocrit(rs.getFloat("hematocrit"));
                kq.setNhomMau(rs.getString("nhom_mau"));
                kq.setTocDoMauLang(rs.getInt("toc_do_mau_lang"));
                kq.setDongMauCoBan(rs.getString("dong_mau_co_ban"));
                
    
                kq.setGlucose(rs.getFloat("glucose"));
                kq.setHbA1c(rs.getFloat("hbA1c"));
                kq.setUre(rs.getFloat("ure"));
                kq.setCreatinine(rs.getFloat("creatinine"));
                kq.setAstGot(rs.getFloat("ast_got"));
                kq.setAltGpt(rs.getFloat("alt_gpt"));
                kq.setGgt(rs.getFloat("ggt"));
                kq.setCholesterolTp(rs.getFloat("cholesterol_tp"));
                kq.setTriglyceride(rs.getFloat("triglyceride"));
                kq.setHdlC(rs.getFloat("hdl_c"));
                kq.setLdlC(rs.getFloat("ldl_c"));
                kq.setAcidUric(rs.getFloat("acid_uric"));
                kq.setBilirubinTp(rs.getFloat("bilirubin_tp"));
                
      
                kq.setTyTrong(rs.getFloat("ty_trong"));
                kq.setPh(rs.getFloat("ph"));
                kq.setBachCauNuocTieu(rs.getString("bach_cau_nuoc_tieu"));
                kq.setHongCauNuocTieu(rs.getString("hong_cau_nuoc_tieu"));
                kq.setProteinNuocTieu(rs.getString("protein_nuoc_tieu"));
                kq.setDuongNuocTieu(rs.getString("duong_nuoc_tieu"));
                kq.setNitrit(rs.getString("nitrit"));
                kq.setKetone(rs.getString("ketone"));
                
    
                kq.setHbsag(rs.getString("hbsag"));
                kq.setHcvAb(rs.getString("hcv_ab"));
                kq.setHivAb(rs.getString("hiv_ab"));
                kq.setTphaSyphilis(rs.getString("tpha_syphilis"));
                kq.setCrpDinhLuong(rs.getFloat("crp_dinh_luong"));
                kq.setRfDinhLuong(rs.getFloat("rf_dinh_luong"));
                
                kq.setGhiChuThem(rs.getString("ghi_chu_them"));
                
                list.add(kq);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}