package BunnyCafeIsland;




import BunnyCafeIsland.Service.BunnyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.text.ParseException;


@SpringBootApplication      

public class BunnyCafeIslandApplication {



	public static void main(String[] args) {
		SpringApplication.run(BunnyCafeIslandApplication.class, args);

	}





	
}
