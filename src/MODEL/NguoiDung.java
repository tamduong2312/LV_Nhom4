package MODEL;

import java.time.LocalDateTime;

public class NguoiDung {

    private int MaNguoiDung;
    private String TenDangNhap;
    private String MatKhau;
    private String role;      
    private String email;
    private int nhanvienid;


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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getNhanvienid() {
        return nhanvienid;
    }

    public void setNhanvienid(int nhanvienid) {
        this.nhanvienid = nhanvienid;
    }



    public NguoiDung() {
        super();
    }

    public NguoiDung(String tenDangNhap, String matKhau) {
        super();
        TenDangNhap = tenDangNhap;
        MatKhau = matKhau;
    }


    public NguoiDung(String tenDangNhap, String matKhau, String role, String email) {
        super();
        TenDangNhap = tenDangNhap;
        MatKhau = matKhau;
        this.role = role;
        this.email = email;
    }

  
    public NguoiDung(String tenDangNhap, String matKhau, String role, String email, int nhanvienid) {
        super();
        TenDangNhap = tenDangNhap;
        MatKhau = matKhau;
        this.role = role;
        this.email = email;
        this.nhanvienid = nhanvienid;
    }


    public NguoiDung(int maNguoiDung, String tenDangNhap, String matKhau,
                     String email, String role, int nhanvienid,
                     LocalDateTime ngayTao) {
        super();
        MaNguoiDung = maNguoiDung;
        TenDangNhap = tenDangNhap;
        MatKhau = matKhau;
        this.email = email;
        this.role = role;
        this.nhanvienid = nhanvienid;

    }
}
