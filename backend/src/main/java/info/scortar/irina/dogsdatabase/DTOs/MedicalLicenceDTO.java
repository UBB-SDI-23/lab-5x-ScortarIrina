package info.scortar.irina.dogsdatabase.DTOs;

public class MedicalLicenceDTO {

    private Long id;
    private String serial_number;
    private String university;
    private String city;
    private String vet_name;

    public MedicalLicenceDTO(Long id, String serial_number, String university, String city, String vet_name) {
        this.id = id;
        this.university = university;
        this.city = city;
        this.vet_name = vet_name;
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

    public String getVet_name() {
        return vet_name;
    }

    public void setVet_name(String vet_name) {
        this.vet_name = vet_name;
    }

    public String getSerial_number() {
        return serial_number;
    }

    public void setSerial_number(String serial_number) {
        this.serial_number = serial_number;
    }
}
