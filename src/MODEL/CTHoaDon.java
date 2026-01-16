package MODEL;

public class CTHoaDon {
    private int id;
    private int maHoaDon;
    private String noiDung;
    private String loaiMuc; 
    private int idGoc;      
    private int soLuong;
    private double donGia;  
    private double thanhTien;
    public CTHoaDon() {}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getMaHoaDon() {
		return maHoaDon;
	}
	public void setMaHoaDon(int maHoaDon) {
		this.maHoaDon = maHoaDon;
	}
	public String getNoiDung() {
		return noiDung;
	}
	public void setNoiDung(String noiDung) {
		this.noiDung = noiDung;
	}
	public String getLoaiMuc() {
		return loaiMuc;
	}
	public void setLoaiMuc(String loaiMuc) {
		this.loaiMuc = loaiMuc;
	}
	public int getIdGoc() {
		return idGoc;
	}
	public void setIdGoc(int idGoc) {
		this.idGoc = idGoc;
	}
	public int getSoLuong() {
		return soLuong;
	}
	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}
	public double getDonGia() {
		return donGia;
	}
	public void setDonGia(double donGia) {
		this.donGia = donGia;
	}
	public double getThanhTien() {
		return thanhTien;
	}
	public void setThanhTien(double thanhTien) {
		this.thanhTien = thanhTien;
	}
	public CTHoaDon(int id, int maHoaDon, String noiDung, String loaiMuc, int idGoc, int soLuong, double donGia,
			double thanhTien) {
		super();
		this.id = id;
		this.maHoaDon = maHoaDon;
		this.noiDung = noiDung;
		this.loaiMuc = loaiMuc;
		this.idGoc = idGoc;
		this.soLuong = soLuong;
		this.donGia = donGia;
		this.thanhTien = thanhTien;
	}


}