package MODEL;

import java.time.LocalDateTime;

public class NguoiDung {

    private int MaNguoiDung;
    private String TenDangNhap;
    private String MatKhau;






    public int getMaNguoiDung() {
        return MaNguoiDung;
    }

    public void setMaNguoiDung(int maNguoiDung) {
        MaNguoiDung = maNguoiDung;
    }


    public String getTenDangNhap() {
        return TenDangNhap;
    }

    public void setTenDangNhap(String tenDangNhap) {
        TenDangNhap = tenDangNhap;
    }


    public String getMatKhau() {
        return MatKhau;
    }

    public void setMatKhau(String matKhau) {
        MatKhau = matKhau;
    }


 


    public NguoiDung() {
		super();
	}
	public NguoiDung(String tenDangNhap, String matKhau) {
		super();
		TenDangNhap = tenDangNhap;
		MatKhau = matKhau;
	}
	public NguoiDung(int maNguoiDung, String tenDangNhap, String matKhau, String hoTen, String email, int sDT,
			 Boolean trangThai, LocalDateTime ngayTao) {
		super();
		MaNguoiDung = maNguoiDung;
		TenDangNhap = tenDangNhap;
		MatKhau = matKhau;



	
	}



}
