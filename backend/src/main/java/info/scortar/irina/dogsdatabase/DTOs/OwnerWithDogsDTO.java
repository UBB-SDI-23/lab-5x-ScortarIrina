package info.scortar.irina.dogsdatabase.DTOs;

public class OwnerWithDogsDTO {

    private Long id;
    private String first_name;
    private String last_name;
    private int number_of_dogs;
    private float average_weight_of_dogs;

    public OwnerWithDogsDTO(Long id, String first_name, String last_name, int number_of_dogs, float average_weight_of_dogs) {
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.number_of_dogs = number_of_dogs;
        this.average_weight_of_dogs = average_weight_of_dogs;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public int getNumber_of_dogs() {
        return number_of_dogs;
    }

    public void setNumber_of_dogs(int number_of_dogs) {
        this.number_of_dogs = number_of_dogs;
    }

    public float getAverage_weight_of_dogs() {
        return average_weight_of_dogs;
    }

    public void setAverage_weight_of_dogs(float average_weight_of_dogs) {
        this.average_weight_of_dogs = average_weight_of_dogs;
    }
}
