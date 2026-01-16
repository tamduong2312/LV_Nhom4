package MODEL;

import java.util.Date;

public class DanhSachChoCLS {
    private int maChiTietChiDinh;
    private int maPhieuChiDinh;
    private int maBenhNhan;
    private String tenBenhNhan;
    private int namSinh;
    private String gioiTinh;
    private String tenDichVu;
    private String LoaiDichVu;
    private String bacSiChiDinh;
    private String trangThai; 
    private Date ngayChiDinh;
    private int madangky;



    public int getMadangky() {
		return madangky;
	}

	public void setMadangky(int madangky) {
		this.madangky = madangky;
	}

	public String getLoaiDichVu() {
		return LoaiDichVu;
	}

	public void setLoaiDichVu(String loaiDichVu) {
		LoaiDichVu = loaiDichVu;
	}

	// Constructor đầy đủ
    public DanhSachChoCLS(int maChiTietChiDinh, int maPhieuChiDinh, int maBenhNhan, String tenBenhNhan, int namSinh, String gioiTinh, String tenDichVu, String bacSiChiDinh, String trangThai, Date ngayChiDinh) {
        this.maChiTietChiDinh = maChiTietChiDinh;
        this.maPhieuChiDinh = maPhieuChiDinh;
        this.maBenhNhan = maBenhNhan;
        this.tenBenhNhan = tenBenhNhan;
        this.namSinh = namSinh;
        this.gioiTinh = gioiTinh;
        this.tenDichVu = tenDichVu;
        this.bacSiChiDinh = bacSiChiDinh;
        this.trangThai = trangThai;
        this.ngayChiDinh = ngayChiDinh;
    }

	public int getMaChiTietChiDinh() {
		return maChiTietChiDinh;
	}

	public void setMaChiTietChiDinh(int maChiTietChiDinh) {
		this.maChiTietChiDinh = maChiTietChiDinh;
	}

	public int getMaPhieuChiDinh() {
		return maPhieuChiDinh;
	}

	public void setMaPhieuChiDinh(int maPhieuChiDinh) {
		this.maPhieuChiDinh = maPhieuChiDinh;
	}

	public int getMaBenhNhan() {
		return maBenhNhan;
	}

	public void setMaBenhNhan(int maBenhNhan) {
		this.maBenhNhan = maBenhNhan;
	}

	public String getTenBenhNhan() {
		return tenBenhNhan;
	}

	public void setTenBenhNhan(String tenBenhNhan) {
		this.tenBenhNhan = tenBenhNhan;
	}

	public int getNamSinh() {
		return namSinh;
	}

	public void setNamSinh(int namSinh) {
		this.namSinh = namSinh;
	}

	public String getGioiTinh() {
		return gioiTinh;
	}

	public void setGioiTinh(String gioiTinh) {
		this.gioiTinh = gioiTinh;
	}

	public String getTenDichVu() {
		return tenDichVu;
	}

	public void setTenDichVu(String tenDichVu) {
		this.tenDichVu = tenDichVu;
	}

	public String getBacSiChiDinh() {
		return bacSiChiDinh;
	}

	public void setBacSiChiDinh(String bacSiChiDinh) {
		this.bacSiChiDinh = bacSiChiDinh;
	}

	public String getTrangThai() {
		return trangThai;
	}

	public void setTrangThai(String trangThai) {
		this.trangThai = trangThai;
	}

	public Date getNgayChiDinh() {
		return ngayChiDinh;
	}

	public void setNgayChiDinh(Date ngayChiDinh) {
		this.ngayChiDinh = ngayChiDinh;
	}

	public DanhSachChoCLS() {
		super();
	}


}