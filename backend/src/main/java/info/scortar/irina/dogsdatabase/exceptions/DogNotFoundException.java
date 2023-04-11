package info.scortar.irina.dogsdatabase.exceptions;

public class DogNotFoundException extends RuntimeException {

    public DogNotFoundException(Long id) {
        super("Dog with id " + id + " not found!");
    }
}
