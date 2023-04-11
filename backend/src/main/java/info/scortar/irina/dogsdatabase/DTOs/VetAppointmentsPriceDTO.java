package info.scortar.irina.dogsdatabase.DTOs;

public class VetAppointmentsPriceDTO {

    private Long id;
    private String vet_id;
    private int nr_appointments;
    private float avg_price;

    public VetAppointmentsPriceDTO(Long id, String vet_id, int nr_appointments, float avg_price) {
        this.id = id;
        this.vet_id = vet_id;
        this.nr_appointments = nr_appointments;
        this.avg_price = avg_price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getVet_id() {
        return vet_id;
    }

    public void setVet_id(String vet_id) {
        this.vet_id = vet_id;
    }

    public int getNr_appointments() {
        return nr_appointments;
    }

    public void setNr_appointments(int nr_appointments) {
        this.nr_appointments = nr_appointments;
    }

    public float getAvg_price() {
        return avg_price;
    }

    public void setAvg_price(int avg_price) {
        this.avg_price = avg_price;
    }
}
