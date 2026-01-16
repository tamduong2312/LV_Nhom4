package MODEL;

import java.sql.Timestamp;

public class DonThuocCho {
    private int maPhieuKham;
    private int maToaThuoc;
    private String tenBenhNhan;
    private long SDT;
    private String tenBacSi;
    private Timestamp thoiGian;
    private String trangThai;

    public DonThuocCho() {}


    public int getMaPhieuKham() { return maPhieuKham; }
    public void setMaPhieuKham(int maPhieuKham) { this.maPhieuKham = maPhieuKham; }
    public int getMaToaThuoc() { return maToaThuoc; }
    public void setMaToaThuoc(int maToaThuoc) { this.maToaThuoc = maToaThuoc; }
    public String getTenBenhNhan() { return tenBenhNhan; }
    public void setTenBenhNhan(String tenBenhNhan) { this.tenBenhNhan = tenBenhNhan; }
    public String getTenBacSi() { return tenBacSi; }
    public void setTenBacSi(String tenBacSi) { this.tenBacSi = tenBacSi; }
    public Timestamp getThoiGian() { return thoiGian; }
    public void setThoiGian(Timestamp thoiGian) { this.thoiGian = thoiGian; }

	public long getSDT() {
		return SDT;
	}

	public void setSDT(long sDT) {
		SDT = sDT;
	}

	public String getTrangThai() {
		return trangThai;
	}

	public void setTrangThai(String trangThai) {
		this.trangThai = trangThai;
	}
    
    
}