package name.alp.productintegrator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;
import org.springframework.integration.config.EnableIntegration;

@SpringBootApplication
@ImportResource("classpath:integration-config.xml")
public class ProductIntegratorApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProductIntegratorApplication.class, args);
    }

}
