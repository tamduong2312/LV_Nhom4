package MODEL;

import java.sql.Date;

public class LichTaiKham {
    private int id;
    private int maBenhNhan;
    private Integer maPhieuKham; 
    private int maNhanVien;
    private Date ngayTaiKham;
    private String ghiChu;
    private String trangThai;
    
    private int mack;
    
    


    public int getMack() {
		return mack;
	}

	public void setMack(int mack) {
		this.mack = mack;
	}

	public LichTaiKham() {
    }

    public LichTaiKham(int id, int maBenhNhan, Integer maPhieuKham, int maNhanVien, Date ngayTaiKham, String ghiChu, String trangThai) {
        this.id = id;
        this.maBenhNhan = maBenhNhan;
        this.maPhieuKham = maPhieuKham;
        this.maNhanVien = maNhanVien;
        this.ngayTaiKham = ngayTaiKham;
        this.ghiChu = ghiChu;
        this.trangThai = trangThai;
    }

    public LichTaiKham(int maBenhNhan, Integer maPhieuKham, int maNhanVien, Date ngayTaiKham, String ghiChu, String trangThai) {
        this.maBenhNhan = maBenhNhan;
        this.maPhieuKham = maPhieuKham;
        this.maNhanVien = maNhanVien;
        this.ngayTaiKham = ngayTaiKham;
        this.ghiChu = ghiChu;
        this.trangThai = trangThai;
    }


    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getMaBenhNhan() { return maBenhNhan; }
    public void setMaBenhNhan(int maBenhNhan) { this.maBenhNhan = maBenhNhan; }

    public Integer getMaPhieuKham() { return maPhieuKham; }
    public void setMaPhieuKham(Integer maPhieuKham) { this.maPhieuKham = maPhieuKham; }

    public int getMaNhanVien() { return maNhanVien; }
    public void setMaNhanVien(int maNhanVien) { this.maNhanVien = maNhanVien; }

    public Date getNgayTaiKham() { return ngayTaiKham; }
    public void setNgayTaiKham(Date ngayTaiKham) { this.ngayTaiKham = ngayTaiKham; }

    public String getGhiChu() { return ghiChu; }
    public void setGhiChu(String ghiChu) { this.ghiChu = ghiChu; }

    public String getTrangThai() { return trangThai; }
    public void setTrangThai(String trangThai) { this.trangThai = trangThai; }
}