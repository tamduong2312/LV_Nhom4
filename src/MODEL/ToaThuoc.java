package MODEL;

import java.sql.Timestamp;

public class ToaThuoc {
    private int maToaThuoc;
    private int maPhieuKham;
    private String ghiChu;
    private String trangThai;
    private Timestamp ngayTao;
    
    private long TongTien;
    
    

    public String getTrangThai() {
		return trangThai;
	}

	public void setTrangThai(String trangThai) {
		this.trangThai = trangThai;
	}

	public ToaThuoc() {}

    public ToaThuoc(int maToaThuoc, int maPhieuKham, String ghiChu, Timestamp ngayTao) {
        this.maToaThuoc = maToaThuoc;
        this.maPhieuKham = maPhieuKham;
        this.ghiChu = ghiChu;
        this.ngayTao = ngayTao;
    }

    
    
    public long getTongTien() {
		return TongTien;
	}

	public void setTongTien(long tongTien) {
		TongTien = tongTien;
	}


    public int getMaToaThuoc() { return maToaThuoc; }
    public void setMaToaThuoc(int maToaThuoc) { this.maToaThuoc = maToaThuoc; }
    public int getMaPhieuKham() { return maPhieuKham; }
    public void setMaPhieuKham(int maPhieuKham) { this.maPhieuKham = maPhieuKham; }
    public String getGhiChu() { return ghiChu; }
    public void setGhiChu(String ghiChu) { this.ghiChu = ghiChu; }
    public Timestamp getNgayTao() { return ngayTao; }
    public void setNgayTao(Timestamp ngayTao) { this.ngayTao = ngayTao; }
}