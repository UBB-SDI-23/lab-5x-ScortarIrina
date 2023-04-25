package info.scortar.irina.dogsdatabase.DTOs;

public class MedicalLicenceDTO {

    private Long id;
    private String serial_number;
    private String university;
    private String city;
    private Long vet_id;

    public MedicalLicenceDTO(Long id, String serial_number, String university, String city, Long vet_id) {
        this.id = id;
        this.university = university;
        this.city = city;
        this.vet_id = vet_id;
        this.serial_number = serial_number;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Long getVet_id() {
        return vet_id;
    }

    public void setVet_id(Long vet_id) {
        this.vet_id = vet_id;
    }

    public String getSerial_number() {
        return serial_number;
    }

    public void setSerial_number(String serial_number) {
        this.serial_number = serial_number;
    }
}
