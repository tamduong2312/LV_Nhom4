package MODEL;

public class ChiSoTaiMuiHong {
    private int id;
    private int maPhieuKham;
    
    // Tai
    private String thinhLucTaiTrai;
    private String thinhLucTaiPhai;
    private String ongTai;
    private String mangNhiPhai;
    private String mangNhiTrai; // Ánh xạ vào trường còn thiếu
    
    // Mũi
    private String tinhTrangMui;
    private String vachNgan;
    private String cuonMui;
    private String kheMui;
    private String soiTaiMuiHong; // Dùng cho mô tả chung
    
    // Họng & Thanh quản
    private String tinhTrangHong;
    private String amidan;
    private String thanhQuan;
    
    // Kết luận
    private String ghiChu;

    public ChiSoTaiMuiHong() { }

    // Getters & Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getMaPhieuKham() { return maPhieuKham; }
    public void setMaPhieuKham(int maPhieuKham) { this.maPhieuKham = maPhieuKham; }
    public String getThinhLucTaiTrai() { return thinhLucTaiTrai; }
    public void setThinhLucTaiTrai(String thinhLucTaiTrai) { this.thinhLucTaiTrai = thinhLucTaiTrai; }
    public String getThinhLucTaiPhai() { return thinhLucTaiPhai; }
    public void setThinhLucTaiPhai(String thinhLucTaiPhai) { this.thinhLucTaiPhai = thinhLucTaiPhai; }
    public String getOngTai() { return ongTai; }
    public void setOngTai(String ongTai) { this.ongTai = ongTai; }
    public String getMangNhiPhai() { return mangNhiPhai; }
    public void setMangNhiPhai(String mangNhiPhai) { this.mangNhiPhai = mangNhiPhai; }
    public String getMangNhiTrai() { return mangNhiTrai; }
    public void setMangNhiTrai(String mangNhiTrai) { this.mangNhiTrai = mangNhiTrai; }
    public String getTinhTrangMui() { return tinhTrangMui; }
    public void setTinhTrangMui(String tinhTrangMui) { this.tinhTrangMui = tinhTrangMui; }
    public String getVachNgan() { return vachNgan; }
    public void setVachNgan(String vachNgan) { this.vachNgan = vachNgan; }
    public String getCuonMui() { return cuonMui; }
    public void setCuonMui(String cuonMui) { this.cuonMui = cuonMui; }
    public String getKheMui() { return kheMui; }
    public void setKheMui(String kheMui) { this.kheMui = kheMui; }
    public String getSoiTaiMuiHong() { return soiTaiMuiHong; }
    public void setSoiTaiMuiHong(String soiTaiMuiHong) { this.soiTaiMuiHong = soiTaiMuiHong; }
    public String getTinhTrangHong() { return tinhTrangHong; }
    public void setTinhTrangHong(String tinhTrangHong) { this.tinhTrangHong = tinhTrangHong; }
    public String getAmidan() { return amidan; }
    public void setAmidan(String amidan) { this.amidan = amidan; }
    public String getThanhQuan() { return thanhQuan; }
    public void setThanhQuan(String thanhQuan) { this.thanhQuan = thanhQuan; }
    public String getGhiChu() { return ghiChu; }
    public void setGhiChu(String ghiChu) { this.ghiChu = ghiChu; }
}