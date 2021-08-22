package tourGuide;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Locale;

@SpringBootApplication
@EnableFeignClients("tourGuide")
@EnableSwagger2
public class TourGuideApplication {

    public static void main(String[] args) {

        Locale.setDefault(new Locale("en", "US"));
        SpringApplication.run(TourGuideApplication.class, args);
    }

}
