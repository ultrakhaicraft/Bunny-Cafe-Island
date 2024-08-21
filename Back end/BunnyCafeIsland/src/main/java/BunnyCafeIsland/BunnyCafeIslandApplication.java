package BunnyCafeIsland;

import BunnyCafeIsland.DAO.BunnyDAO;
import BunnyCafeIsland.Enums.AvailabilityStatus;
import BunnyCafeIsland.Enums.Gender;
import BunnyCafeIsland.Entity.Bunny;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@SpringBootApplication
public class BunnyCafeIslandApplication {

	public static void main(String[] args) {
		SpringApplication.run(BunnyCafeIslandApplication.class, args);
	}


	@Bean
	public CommandLineRunner commandLineRunner(BunnyDAO bunnyDAO){
		return runner ->{
			//getABunny(bunnyDAO);
			//createBunny(bunnyDAO);
			
			//getBunnyByBreed(bunnyDAO);
			//updateBunny(bunnyDAO);
			//softRemove(bunnyDAO);
			hardRemove(bunnyDAO);
			//getAllBunny(bunnyDAO);
		};
	}

	private void softRemove(BunnyDAO bunnyDAO){
		Bunny newBun =bunnyDAO.findById(6);
		if(newBun==null) {
			System.out.println("Bunny not found!!, Exiting soft remove");
			return;
		}
		bunnyDAO.softRemove(newBun);
		System.out.println("New bunny info"+newBun);
	}

	private void hardRemove(BunnyDAO bunnyDAO){
		bunnyDAO.hardRemove(6);

	}

	private void updateBunny(BunnyDAO bunnyDAO) {
		Bunny newBun =bunnyDAO.findById(1);
		if(newBun==null) {
			System.out.println("Bunny not found!!, Exiting update");
			return;
		}

		System.out.println("Bunny found !!, Executing update");
		System.out.println("Set name to Hikari");
		newBun.setName("Hikari");

		bunnyDAO.update(newBun);
		System.out.println("Update done");
		System.out.println("New bunny info"+newBun);
	}

	private void getBunnyByBreed(BunnyDAO bunnyDAO) {
		List<Bunny> bunnies = bunnyDAO.findByBreed("Lionhead");
		for(Bunny bunny: bunnies){
			System.out.println(bunny);
		}
	}

	private void getAllBunny(BunnyDAO bunnyDAO) {
		List<Bunny> bunnies = bunnyDAO.findAll();
		for(Bunny bunny: bunnies){
			System.out.println(bunny);
		}
	}

	//TODO: Make this more resuable by passing Bunny Object or sth
	private void getABunny(BunnyDAO bunnyDAO) {
		Bunny tempBunny =bunnyDAO.findById(1);
		if(tempBunny==null) System.out.println("Bunny not found!!");
		System.out.println("Bunny found: "+ tempBunny);
	}

	private void createBunny(BunnyDAO bunnyDAO){
		System.out.println("Create a bunny here...");
		Date tempDate = new Date();
		Bunny tempBunny= new Bunny("Peppy","Mini Lop", Gender.Female,4, "Energetic","Peppy.png", AvailabilityStatus.Available,"Healthy",tempDate);

		System.out.println("Saving bunny here...");
		bunnyDAO.save(tempBunny);

		System.out.println("Confirm bunny creation here...");
		Bunny checked =bunnyDAO.findById(tempBunny.getId());
		if(checked==null) System.out.println("Bunny not found!!");
		System.out.println("Bunny found: "+ checked);
	}
}
