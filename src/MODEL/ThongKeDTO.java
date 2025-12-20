package MODEL;

public class ThongKeDTO {
    private int ngay;     
    private double giaTri; 

    public ThongKeDTO() {
    }

    public ThongKeDTO(int ngay, double giaTri) {
        this.ngay = ngay;
        this.giaTri = giaTri;
    }

    public int getNgay() {
        return ngay;
    }

    public void setNgay(int ngay) {
        this.ngay = ngay;
    }

    public double getGiaTri() {
        return giaTri;
    }

    public void setGiaTri(double giaTri) {
        this.giaTri = giaTri;
    }
}