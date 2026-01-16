package MODEL;

import java.sql.Time;

public class BangPhanCongCaLam {

    private int id;
    private int maNhanVien;
    private String phong;
    private Time gioLam;
    private Time gioKetThuc;
    private String thu;


    public BangPhanCongCaLam() {
    }
    public BangPhanCongCaLam(int maNhanVien, String phong, Time gioLam, Time gioKetThuc, String thu) {
        this.maNhanVien = maNhanVien;
        this.phong = phong;
        this.gioLam = gioLam;
        this.gioKetThuc = gioKetThuc;
        this.thu = thu;

    }
    public BangPhanCongCaLam(int id, int maNhanVien, String phong, Time gioLam, Time gioKetThuc, String thu) {
        this.id = id;
        this.maNhanVien = maNhanVien;
        this.phong = phong;
        this.gioLam = gioLam;
        this.gioKetThuc = gioKetThuc;
        this.thu = thu;

    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMaNhanVien() {
        return maNhanVien;
    }

    public void setMaNhanVien(int maNhanVien) {
        this.maNhanVien = maNhanVien;
    }

    public String getPhong() {
        return phong;
    }

    public void setPhong(String phong) {
        this.phong = phong;
    }

    public Time getGioLam() {
        return gioLam;
    }

    public void setGioLam(Time gioLam) {
        this.gioLam = gioLam;
    }

    public Time getGioKetThuc() {
        return gioKetThuc;
    }

    public void setGioKetThuc(Time gioKetThuc) {
        this.gioKetThuc = gioKetThuc;
    }

    public String getThu() {
        return thu;
    }

    public void setThu(String thu) {
        this.thu = thu;
    }



}
