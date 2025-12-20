package MODEL;

public class ChiSoTimMach {
    private int id;
    private int maPhieuKham;
    private String nhipTim;
    private String huyetApTamThu;
    private String huyetApTamTruong;
    private String cholesterol;
    private float triglyceride;
    private float duongHuyet;
    private String ecgKetQua; // Điện tâm đồ
    private String ghiChu;

    public ChiSoTimMach() {
    }

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getMaPhieuKham() { return maPhieuKham; }
    public void setMaPhieuKham(int maPhieuKham) { this.maPhieuKham = maPhieuKham; }

    public String getNhipTim() { return nhipTim; }
    public void setNhipTim(String nhipTim) { this.nhipTim = nhipTim; }

    public String getHuyetApTamThu() { return huyetApTamThu; }
    public void setHuyetApTamThu(String huyetApTamThu) { this.huyetApTamThu = huyetApTamThu; }

    public String getHuyetApTamTruong() { return huyetApTamTruong; }
    public void setHuyetApTamTruong(String huyetApTamTruong) { this.huyetApTamTruong = huyetApTamTruong; }

    public String getCholesterol() { return cholesterol; }
    public void setCholesterol(String cholesterol) { this.cholesterol = cholesterol; }

    public float getTriglyceride() { return triglyceride; }
    public void setTriglyceride(float triglyceride) { this.triglyceride = triglyceride; }

    public float getDuongHuyet() { return duongHuyet; }
    public void setDuongHuyet(float duongHuyet) { this.duongHuyet = duongHuyet; }

    public String getEcgKetQua() { return ecgKetQua; }
    public void setEcgKetQua(String ecgKetQua) { this.ecgKetQua = ecgKetQua; }

    public String getGhiChu() { return ghiChu; }
    public void setGhiChu(String ghiChu) { this.ghiChu = ghiChu; }
}