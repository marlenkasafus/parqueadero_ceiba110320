package parqueadero_ddd;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@ComponentScan("parqueadero_ddd")
@PropertySource("classpath:parqueadero.properties")
@EnableAutoConfiguration
public class Application {
	
	public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
	
}
