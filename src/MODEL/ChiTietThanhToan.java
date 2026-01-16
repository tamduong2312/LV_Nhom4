package MODEL;

public class ChiTietThanhToan {
	private int id;
    private String tenNoiDung;

    private int soLuong;
    private int mathuoc;
    private double donGia;
    private double thanhTien;

    public ChiTietThanhToan() {
    }

    public ChiTietThanhToan(String tenNoiDung, int soLuong, double donGia, double thanhTien) {
        this.tenNoiDung = tenNoiDung;
        this.soLuong = soLuong;
        this.donGia = donGia;
        this.thanhTien = thanhTien;
    }

    public ChiTietThanhToan(int id, String tenNoiDung, int soLuong, double donGia, double thanhTien) {
		super();
		this.id = id;
		this.tenNoiDung = tenNoiDung;
		this.soLuong = soLuong;
		this.donGia = donGia;
		this.thanhTien = thanhTien;
	}

	public int getMathuoc() {
		return mathuoc;
	}

	public void setMathuoc(int mathuoc) {
		this.mathuoc = mathuoc;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}


    public String getTenNoiDung() { return tenNoiDung; }
    public void setTenNoiDung(String tenNoiDung) { this.tenNoiDung = tenNoiDung; }

    public int getSoLuong() { return soLuong; }
    public void setSoLuong(int soLuong) { this.soLuong = soLuong; }

    public double getDonGia() { return donGia; }
    public void setDonGia(double donGia) { this.donGia = donGia; }

    public double getThanhTien() { return thanhTien; }
    public void setThanhTien(double thanhTien) { this.thanhTien = thanhTien; }
}