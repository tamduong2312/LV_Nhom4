package MODEL;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class KhoThuoc {
    private int idKho;
    private int maThuoc;
    private int soLuongTon;
    private LocalDate ngayCapNhatCuoi;


    public KhoThuoc() {
    }

   
    public KhoThuoc(int idKho, int maThuoc, int soLuongTon, LocalDate ngayCapNhatCuoi) {
        this.idKho = idKho;
        this.maThuoc = maThuoc;
        this.soLuongTon = soLuongTon;
        this.ngayCapNhatCuoi = ngayCapNhatCuoi;
    }


    public int getIdKho() {
        return idKho;
    }

    public void setIdKho(int idKho) {
        this.idKho = idKho;
    }

    public int getMaThuoc() {
        return maThuoc;
    }

    public void setMaThuoc(int maThuoc) {
        this.maThuoc = maThuoc;
    }

    public int getSoLuongTon() {
        return soLuongTon;
    }

    public void setSoLuongTon(int soLuongTon) {
        this.soLuongTon = soLuongTon;
    }

    public LocalDate getNgayCapNhatCuoi() {
        return ngayCapNhatCuoi;
    }

    public void setNgayCapNhatCuoi(LocalDate ngayCapNhatCuoi) {
        this.ngayCapNhatCuoi = ngayCapNhatCuoi;
    }
}