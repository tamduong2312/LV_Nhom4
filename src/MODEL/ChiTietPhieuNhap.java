package MODEL;

public class ChiTietPhieuNhap {
    private int id;
    private int maPhieuNhapThuoc;
    private int maThuoc;
    private int soLuongNhap;
    private long donGiaNhap;
    private long thanhTien;

    public ChiTietPhieuNhap() {
    }

    public ChiTietPhieuNhap(int id, int maPhieuNhapThuoc, int maThuoc, int soLuongNhap, long donGiaNhap, long thanhTien) {
        this.id = id;
        this.maPhieuNhapThuoc = maPhieuNhapThuoc;
        this.maThuoc = maThuoc;
        this.soLuongNhap = soLuongNhap;
        this.donGiaNhap = donGiaNhap;
        this.thanhTien = thanhTien;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMaPhieuNhapThuoc() {
        return maPhieuNhapThuoc;
    }

    public void setMaPhieuNhapThuoc(int maPhieuNhapThuoc) {
        this.maPhieuNhapThuoc = maPhieuNhapThuoc;
    }

    public int getMaThuoc() {
        return maThuoc;
    }

    public void setMaThuoc(int maThuoc) {
        this.maThuoc = maThuoc;
    }

    public int getSoLuongNhap() {
        return soLuongNhap;
    }

    public void setSoLuongNhap(int soLuongNhap) {
        this.soLuongNhap = soLuongNhap;
    }

    public double getDonGiaNhap() {
        return donGiaNhap;
    }

    public void setDonGiaNhap(long donGiaNhap) {
        this.donGiaNhap = donGiaNhap;
    }

    public double getThanhTien() {
        return thanhTien;
    }

    public void setThanhTien(long thanhTien) {
        this.thanhTien = thanhTien;
    }
}