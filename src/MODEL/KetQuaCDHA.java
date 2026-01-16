package MODEL;

import java.sql.Timestamp;

public class KetQuaCDHA {
    private int id;
    private int idChiTietChiDinh;
    private String moTa;
    private String ketLuan;
    private String deNghi;
    private String anh1;
    private String anh2;
    private int maBacSi;
    private int maNhanVien;
    private Timestamp ngayThucHien;


    public KetQuaCDHA() {}


    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getMoTa() { return moTa; }
    public void setMoTa(String moTa) { this.moTa = moTa; }
    public String getKetLuan() { return ketLuan; }
    public void setKetLuan(String ketLuan) { this.ketLuan = ketLuan; }
    public String getDeNghi() { return deNghi; }
    public void setDeNghi(String deNghi) { this.deNghi = deNghi; }
    public String getAnh1() { return anh1; }
    public void setAnh1(String anh1) { this.anh1 = anh1; }
    public String getAnh2() { return anh2; }
    public void setAnh2(String anh2) { this.anh2 = anh2; }
    public int getMaBacSi() { return maBacSi; }
    public void setMaBacSi(int maBacSi) { this.maBacSi = maBacSi; }
    public Timestamp getNgayThucHien() { return ngayThucHien; }
    public void setNgayThucHien(Timestamp ngayThucHien) { this.ngayThucHien = ngayThucHien; }


	public int getIdChiTietChiDinh() {
		return idChiTietChiDinh;
	}


	public void setIdChiTietChiDinh(int idChiTietChiDinh) {
		this.idChiTietChiDinh = idChiTietChiDinh;
	}


	public int getMaNhanVien() {
		return maNhanVien;
	}


	public void setMaNhanVien(int maNhanVien) {
		this.maNhanVien = maNhanVien;
	}
    
}