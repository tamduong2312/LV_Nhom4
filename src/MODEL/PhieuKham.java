package MODEL;

import java.time.LocalDate;

public class PhieuKham {

    private int maPhieuKham;
    private int maBenhNhan;
    private int maNhanVien;
    private String maChuyenKhoa;
    private LocalDate ngayKham;
    private String trieuChung;
    private String chanDoan;
    private String huongDieuTri;
    private String loiDanBacSi;
    private long tienKham;
    private String trangThai;
    private String ghiChu;
    private LocalDate ngayTao;

    public PhieuKham() {}


    public PhieuKham(int maBenhNhan, int maNhanVien, String maChuyenKhoa, LocalDate ngayKham, String trangThai) {
        this.maBenhNhan = maBenhNhan;
        this.maNhanVien = maNhanVien;
        this.maChuyenKhoa = maChuyenKhoa;
        this.ngayKham = ngayKham;
        this.trangThai = trangThai;
    }


    public int getMaPhieuKham() {
        return maPhieuKham;
    }
    public void setMaPhieuKham(int maPhieuKham) {
        this.maPhieuKham = maPhieuKham;
    }

    public int getMaBenhNhan() {
        return maBenhNhan;
    }
    public void setMaBenhNhan(int maBenhNhan) {
        this.maBenhNhan = maBenhNhan;
    }

    public int getMaNhanVien() {
        return maNhanVien;
    }
    public void setMaNhanVien(int maNhanVien) {
        this.maNhanVien = maNhanVien;
    }

    public String getMaChuyenKhoa() {
        return maChuyenKhoa;
    }
    public void setMaChuyenKhoa(String maChuyenKhoa) {
        this.maChuyenKhoa = maChuyenKhoa;
    }

    public LocalDate getNgayKham() {
        return ngayKham;
    }
    public void setNgayKham(LocalDate ngayKham) {
        this.ngayKham = ngayKham;
    }

    public String getTrieuChung() {
        return trieuChung;
    }
    public void setTrieuChung(String trieuChung) {
        this.trieuChung = trieuChung;
    }

    public String getChanDoan() {
        return chanDoan;
    }
    public void setChanDoan(String chanDoan) {
        this.chanDoan = chanDoan;
    }

    public String getHuongDieuTri() {
        return huongDieuTri;
    }
    public void setHuongDieuTri(String huongDieuTri) {
        this.huongDieuTri = huongDieuTri;
    }

    public String getLoiDanBacSi() {
        return loiDanBacSi;
    }
    public void setLoiDanBacSi(String loiDanBacSi) {
        this.loiDanBacSi = loiDanBacSi;
    }

    public long getTienKham() {
        return tienKham;
    }
    public void setTienKham(long tienKham) {
        this.tienKham = tienKham;
    }

    public String getTrangThai() {
        return trangThai;
    }
    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    public String getGhiChu() {
        return ghiChu;
    }
    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    public LocalDate getNgayTao() {
        return ngayTao;
    }
    public void setNgayTao(LocalDate ngayTao) {
        this.ngayTao = ngayTao;
    }
}
