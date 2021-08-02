package rewards;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Locale;

@SpringBootApplication
public class RewardsApplication {

    public static void main(String[] args) {

        Locale.setDefault(new Locale("en", "US"));
        SpringApplication.run(RewardsApplication.class, args);
    }

}
