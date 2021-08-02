package tourGuide;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Locale;

@SpringBootApplication
public class TourGuideApplication {

    public static void main(String[] args) {

        Locale.setDefault(new Locale("en", "US"));
        SpringApplication.run(TourGuideApplication.class, args);
    }

}
