package info.scortar.irina.dogsdatabase;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class DogsDatabaseApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(DogsDatabaseApplication.class, args);
    }
}
