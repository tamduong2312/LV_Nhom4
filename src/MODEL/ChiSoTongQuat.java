package MODEL;

import java.time.LocalDateTime;

public class ChiSoTongQuat {
    private int id; 
    private int maPhieuKham; 
    private int maNhanVienThucHien;
    
    public int getMaNhanVienThucHien() {
		return maNhanVienThucHien;
	}

	public void setMaNhanVienThucHien(int maNhanVienThucHien) {
		this.maNhanVienThucHien = maNhanVienThucHien;
	}

    private double nhietDo; 
    private int nhipTim;
    private int nhipTho; 
    private int huyetApTamThu;
    private int huyetApTamTruong; 
    private double canNang; 
    private double chieuCao; 
    private double spo2; 
    

    private double vongDau;
    private String tinhTrangDinhDuong;
    private String tamLyHanhVi;
    private String khamTaiMuiHongNhi; 
    private String khamHoHapNhi; 
    private String khamDaNiemMacNhi; 
    private String coQuanKhacNhi; 
    

    private String tinhTrangRang; 
    private String sauRang; 
    private String caoRang; 
    private String viemNuou; 
    private String khopCan; 
    private String niemMacMieng; 
    private String doLungLay; 
    private String phuHinhCu; 
    private String benhLyKhacRHM;
    

    private String thinhLucTaiTrai; 
    private String thinhLucTaiPhai; 
    private String tinhTrangMui; 
    private String tinhTrangHong; 
    private String soiTaiMuiHong; 
    private String ongTai; 
    private String mangNhiPhai; 
    private String mangNhiTrai; 
    private String vachNgan; 
    private String cuonMui; 
    private String kheMui; 
    private String amidan; 
    private String thanhQuan; 

    private double cholesterol;
    private double hdlCholesterol; 
    private double ldlCholesterol; 
    private double triglyceride; 
    private double duongHuyet;
    private String ecgKetQua; 
    private String sieuAmTim; 
    

    private String ghiChu; 
    private LocalDateTime ngayTao; 

    public ChiSoTongQuat() {}


    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getMaPhieuKham() { return maPhieuKham; }
    public void setMaPhieuKham(int maPhieuKham) { this.maPhieuKham = maPhieuKham; }
    public double getNhietDo() { return nhietDo; }
    public void setNhietDo(double nhietDo) { this.nhietDo = nhietDo; }
    public int getNhipTim() { return nhipTim; }
    public void setNhipTim(int nhipTim) { this.nhipTim = nhipTim; }
    public int getNhipTho() { return nhipTho; }
    public void setNhipTho(int nhipTho) { this.nhipTho = nhipTho; }
    public int getHuyetApTamThu() { return huyetApTamThu; }
    public void setHuyetApTamThu(int huyetApTamThu) { this.huyetApTamThu = huyetApTamThu; }
    public int getHuyetApTamTruong() { return huyetApTamTruong; }
    public void setHuyetApTamTruong(int huyetApTamTruong) { this.huyetApTamTruong = huyetApTamTruong; }
    public double getCanNang() { return canNang; }
    public void setCanNang(double canNang) { this.canNang = canNang; }
    public double getChieuCao() { return chieuCao; }
    public void setChieuCao(double chieuCao) { this.chieuCao = chieuCao; }
    public double getSpo2() { return spo2; }
    public void setSpo2(double spo2) { this.spo2 = spo2; }
    public double getVongDau() { return vongDau; }
    public void setVongDau(double vongDau) { this.vongDau = vongDau; }
    public String getTinhTrangDinhDuong() { return tinhTrangDinhDuong; }
    public void setTinhTrangDinhDuong(String tinhTrangDinhDuong) { this.tinhTrangDinhDuong = tinhTrangDinhDuong; }
    public String getTamLyHanhVi() { return tamLyHanhVi; }
    public void setTamLyHanhVi(String tamLyHanhVi) { this.tamLyHanhVi = tamLyHanhVi; }
    public String getKhamTaiMuiHongNhi() { return khamTaiMuiHongNhi; }
    public void setKhamTaiMuiHongNhi(String khamTaiMuiHongNhi) { this.khamTaiMuiHongNhi = khamTaiMuiHongNhi; }
    public String getKhamHoHapNhi() { return khamHoHapNhi; }
    public void setKhamHoHapNhi(String khamHoHapNhi) { this.khamHoHapNhi = khamHoHapNhi; }
    public String getKhamDaNiemMacNhi() { return khamDaNiemMacNhi; }
    public void setKhamDaNiemMacNhi(String khamDaNiemMacNhi) { this.khamDaNiemMacNhi = khamDaNiemMacNhi; }
    public String getCoQuanKhacNhi() { return coQuanKhacNhi; }
    public void setCoQuanKhacNhi(String coQuanKhacNhi) { this.coQuanKhacNhi = coQuanKhacNhi; }
    public String getTinhTrangRang() { return tinhTrangRang; }
    public void setTinhTrangRang(String tinhTrangRang) { this.tinhTrangRang = tinhTrangRang; }
    public String getSauRang() { return sauRang; }
    public void setSauRang(String sauRang) { this.sauRang = sauRang; }
    public String getCaoRang() { return caoRang; }
    public void setCaoRang(String caoRang) { this.caoRang = caoRang; }
    public String getViemNuou() { return viemNuou; }
    public void setViemNuou(String viemNuou) { this.viemNuou = viemNuou; }
    public String getKhopCan() { return khopCan; }
    public void setKhopCan(String khopCan) { this.khopCan = khopCan; }
    public String getNiemMacMieng() { return niemMacMieng; }
    public void setNiemMacMieng(String niemMacMieng) { this.niemMacMieng = niemMacMieng; }
    public String getDoLungLay() { return doLungLay; }
    public void setDoLungLay(String doLungLay) { this.doLungLay = doLungLay; }
    public String getPhuHinhCu() { return phuHinhCu; }
    public void setPhuHinhCu(String phuHinhCu) { this.phuHinhCu = phuHinhCu; }
    public String getBenhLyKhacRHM() { return benhLyKhacRHM; }
    public void setBenhLyKhacRHM(String benhLyKhacRHM) { this.benhLyKhacRHM = benhLyKhacRHM; }
    public String getThinhLucTaiTrai() { return thinhLucTaiTrai; }
    public void setThinhLucTaiTrai(String thinhLucTaiTrai) { this.thinhLucTaiTrai = thinhLucTaiTrai; }
    public String getThinhLucTaiPhai() { return thinhLucTaiPhai; }
    public void setThinhLucTaiPhai(String thinhLucTaiPhai) { this.thinhLucTaiPhai = thinhLucTaiPhai; }
    public String getTinhTrangMui() { return tinhTrangMui; }
    public void setTinhTrangMui(String tinhTrangMui) { this.tinhTrangMui = tinhTrangMui; }
    public String getTinhTrangHong() { return tinhTrangHong; }
    public void setTinhTrangHong(String tinhTrangHong) { this.tinhTrangHong = tinhTrangHong; }
    public String getSoiTaiMuiHong() { return soiTaiMuiHong; }
    public void setSoiTaiMuiHong(String soiTaiMuiHong) { this.soiTaiMuiHong = soiTaiMuiHong; }
    public String getOngTai() { return ongTai; }
    public void setOngTai(String ongTai) { this.ongTai = ongTai; }
    public String getMangNhiPhai() { return mangNhiPhai; }
    public void setMangNhiPhai(String mangNhiPhai) { this.mangNhiPhai = mangNhiPhai; }
    public String getMangNhiTrai() { return mangNhiTrai; }
    public void setMangNhiTrai(String mangNhiTrai) { this.mangNhiTrai = mangNhiTrai; }
    public String getVachNgan() { return vachNgan; }
    public void setVachNgan(String vachNgan) { this.vachNgan = vachNgan; }
    public String getCuonMui() { return cuonMui; }
    public void setCuonMui(String cuonMui) { this.cuonMui = cuonMui; }
    public String getKheMui() { return kheMui; }
    public void setKheMui(String kheMui) { this.kheMui = kheMui; }
    public String getAmidan() { return amidan; }
    public void setAmidan(String amidan) { this.amidan = amidan; }
    public String getThanhQuan() { return thanhQuan; }
    public void setThanhQuan(String thanhQuan) { this.thanhQuan = thanhQuan; }
    public double getCholesterol() { return cholesterol; }
    public void setCholesterol(double cholesterol) { this.cholesterol = cholesterol; }
    public double getHdlCholesterol() { return hdlCholesterol; }
    public void setHdlCholesterol(double hdlCholesterol) { this.hdlCholesterol = hdlCholesterol; }
    public double getLdlCholesterol() { return ldlCholesterol; }
    public void setLdlCholesterol(double ldlCholesterol) { this.ldlCholesterol = ldlCholesterol; }
    public double getTriglyceride() { return triglyceride; }
    public void setTriglyceride(double triglyceride) { this.triglyceride = triglyceride; }
    public double getDuongHuyet() { return duongHuyet; }
    public void setDuongHuyet(double duongHuyet) { this.duongHuyet = duongHuyet; }
    public String getEcgKetQua() { return ecgKetQua; }
    public void setEcgKetQua(String ecgKetQua) { this.ecgKetQua = ecgKetQua; }
    public String getSieuAmTim() { return sieuAmTim; }
    public void setSieuAmTim(String sieuAmTim) { this.sieuAmTim = sieuAmTim; }
    public String getGhiChu() { return ghiChu; }
    public void setGhiChu(String ghiChu) { this.ghiChu = ghiChu; }
    public LocalDateTime getNgayTao() { return ngayTao; }
    public void setNgayTao(LocalDateTime ngayTao) { this.ngayTao = ngayTao; }
}