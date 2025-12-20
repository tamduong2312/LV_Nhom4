package MODEL;

public class ChiSoTongQuat {

    private int id;
    private int maPhieuKham;

    private double nhietDo;
    private int huyetApThu;
    private int huyetApTruong;
    private int nhipTim;
    private int nhipTho;
    private double canNang;
    private double chieuCao;
    private String ghiChu;

    public ChiSoTongQuat() {}

    public ChiSoTongQuat(int maPhieuKham, double nhietDo, int huyetApThu,
                             int huyetApTruong, int nhipTim, int nhipTho,
                             double canNang, double chieuCao, String ghiChu) {
        this.maPhieuKham = maPhieuKham;
        this.nhietDo = nhietDo;
        this.huyetApThu = huyetApThu;
        this.huyetApTruong = huyetApTruong;
        this.nhipTim = nhipTim;
        this.nhipTho = nhipTho;
        this.canNang = canNang;
        this.chieuCao = chieuCao;
        this.ghiChu = ghiChu;
    }


    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public int getMaPhieuKham() {
        return maPhieuKham;
    }
    public void setMaPhieuKham(int maPhieuKham) {
        this.maPhieuKham = maPhieuKham;
    }

    public double getNhietDo() {
        return nhietDo;
    }
    public void setNhietDo(double nhietDo) {
        this.nhietDo = nhietDo;
    }

    public int getHuyetApThu() {
        return huyetApThu;
    }
    public void setHuyetApThu(int huyetApThu) {
        this.huyetApThu = huyetApThu;
    }

    public int getHuyetApTruong() {
        return huyetApTruong;
    }
    public void setHuyetApTruong(int huyetApTruong) {
        this.huyetApTruong = huyetApTruong;
    }

    public int getNhipTim() {
        return nhipTim;
    }
    public void setNhipTim(int nhipTim) {
        this.nhipTim = nhipTim;
    }

    public int getNhipTho() {
        return nhipTho;
    }
    public void setNhipTho(int nhipTho) {
        this.nhipTho = nhipTho;
    }

    public double getCanNang() {
        return canNang;
    }
    public void setCanNang(double canNang) {
        this.canNang = canNang;
    }

    public double getChieuCao() {
        return chieuCao;
    }
    public void setChieuCao(double chieuCao) {
        this.chieuCao = chieuCao;
    }

    public String getGhiChu() {
        return ghiChu;
    }
    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }
}
