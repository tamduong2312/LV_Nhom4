package MODEL;

public class DichVu {
	
	private int MaDichVu;
	private String TenDichVu;
	private long DonGia;
	private String Loai_Dich_Vu;
	private int maphong;
	private int machuyenkhoa;
	
	
	public int getMachuyenkhoa() {
		return machuyenkhoa;
	}
	public void setMachuyenkhoa(int machuyenkhoa) {
		this.machuyenkhoa = machuyenkhoa;
	}
	public int getMaphong() {
		return maphong;
	}
	public void setMaphong(int maphong) {
		this.maphong = maphong;
	}
	public int getMaDichVu() {
		return MaDichVu;
	}
	public void setMaDichVu(int maDichVu) {
		MaDichVu = maDichVu;
	}
	public String getTenDichVu() {
		return TenDichVu;
	}
	public void setTenDichVu(String tenDichVu) {
		TenDichVu = tenDichVu;
	}
	public long getDonGia() {
		return DonGia;
	}
	public void setDonGia(long donGia) {
		DonGia = donGia;
	}
	public String getLoai_Dich_Vu() {
		return Loai_Dich_Vu;
	}
	public void setLoai_Dich_Vu(String loai_Dich_Vu) {
		Loai_Dich_Vu = loai_Dich_Vu;
	}
	public DichVu(int maDichVu, String tenDichVu, long donGia, String loai_Dich_Vu) {
		super();
		MaDichVu = maDichVu;
		TenDichVu = tenDichVu;
		DonGia = donGia;
		Loai_Dich_Vu = loai_Dich_Vu;
	}
	
	
	public DichVu(String tenDichVu, long donGia, String loai_Dich_Vu, int maphong, int machuyenkhoa) {
		super();
		TenDichVu = tenDichVu;
		DonGia = donGia;
		Loai_Dich_Vu = loai_Dich_Vu;
		this.maphong = maphong;
		this.machuyenkhoa = machuyenkhoa;
	}
	public DichVu(String tenDichVu, long donGia, String loai_Dich_Vu) {
		super();
		TenDichVu = tenDichVu;
		DonGia = donGia;
		Loai_Dich_Vu = loai_Dich_Vu;
	}
	public DichVu() {
		super();
	}
	
	

}
