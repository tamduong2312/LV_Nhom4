package MODEL;

public class CTToaThuoc {
    private int id;
    private int maToaThuoc;
    private int maThuoc;
    private String tenThuoc;    
    private String donViTinh;   
    private double donGia;     
    

    private String sang;
    private String trua;
    private String chieu;
    private String toi;
    private int soNgay;
    private String thoiDiemDung; 
    private String cachDung;    

    public CTToaThuoc() {}


    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getMaToaThuoc() { return maToaThuoc; }
    public void setMaToaThuoc(int maToaThuoc) { this.maToaThuoc = maToaThuoc; }
    public int getMaThuoc() { return maThuoc; }
    public void setMaThuoc(int maThuoc) { this.maThuoc = maThuoc; }
    public String getTenThuoc() { return tenThuoc; }
    public void setTenThuoc(String tenThuoc) { this.tenThuoc = tenThuoc; }
    public String getDonViTinh() { return donViTinh; }
    public void setDonViTinh(String donViTinh) { this.donViTinh = donViTinh; }
    public double getDonGia() { return donGia; }
    public void setDonGia(double donGia) { this.donGia = donGia; }
    
    public String getSang() { return sang; }
    public void setSang(String sang) { this.sang = sang; }
    public String getTrua() { return trua; }
    public void setTrua(String trua) { this.trua = trua; }
    public String getChieu() { return chieu; }
    public void setChieu(String chieu) { this.chieu = chieu; }
    public String getToi() { return toi; }
    public void setToi(String toi) { this.toi = toi; }
    
    public int getSoNgay() { return soNgay; }
    public void setSoNgay(int soNgay) { this.soNgay = soNgay; }
    public String getThoiDiemDung() { return thoiDiemDung; }
    public void setThoiDiemDung(String thoiDiemDung) { this.thoiDiemDung = thoiDiemDung; }
    public String getCachDung() { return cachDung; }
    public void setCachDung(String cachDung) { this.cachDung = cachDung; }


    public int getTongSoLuongUocTinh() {
        double s = parseDose(sang);
        double tr = parseDose(trua);
        double c = parseDose(chieu);
        double t = parseDose(toi);
        return (int) Math.ceil((s + tr + c + t) * soNgay);
    }

    private double parseDose(String text) {
        if (text == null || text.trim().isEmpty()) return 0;
        try {

            if (text.contains("/")) {
                String[] parts = text.split("/");
                return Double.parseDouble(parts[0]) / Double.parseDouble(parts[1]);
            }

            String numberOnly = text.replaceAll("[^0-9.]", "");
            return Double.parseDouble(numberOnly);
        } catch (Exception e) {
            return 0; 
        }
    }
}