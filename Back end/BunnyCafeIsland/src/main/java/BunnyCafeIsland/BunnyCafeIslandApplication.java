package BunnyCafeIsland;

import BunnyCafeIsland.Enums.AvailabilityStatus;
import BunnyCafeIsland.Enums.Gender;
import BunnyCafeIsland.Repository.BunnyDAO;
import BunnyCafeIsland.Entity.Bunny;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@SpringBootApplication      /*(exclude = SecurityAutoConfiguration.class)*/
public class BunnyCafeIslandApplication {

	public static void main(String[] args) {
		SpringApplication.run(BunnyCafeIslandApplication.class, args);
	}



	
}
