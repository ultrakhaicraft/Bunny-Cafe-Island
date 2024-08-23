package BunnyCafeIsland.Service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import BunnyCafeIsland.Entity.Bunny;
import BunnyCafeIsland.Enums.AvailabilityStatus;
import BunnyCafeIsland.Enums.Gender;
import BunnyCafeIsland.Repository.BunnyDAO;
import jakarta.persistence.EntityManager;

@Service
public class BunnyService {

	
    private BunnyDAO bunnyDAO;

	@Autowired
	public BunnyService(BunnyDAO bunnyDAO){
		this.bunnyDAO=bunnyDAO;
	}

    public void softRemove(int id){
		Bunny newBun =getBunnyById(id);
		if(newBun==null) {
			System.out.println("Bunny not found!!, Exiting soft remove");
			return;
		}
		bunnyDAO.softRemove(newBun);
		System.out.println("New bunny info"+newBun);
	}

	public void hardRemove(int id){
		bunnyDAO.hardRemove(id);

	}

	//TODO
	public void updateBunny() {
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

	public List<Bunny> getBunnyByBreed(String breed) {
		List<Bunny> bunnies = bunnyDAO.findByBreed(breed);
		return bunnies;
	}

	public List<Bunny> getAllBunny() {
		List<Bunny> bunnies = bunnyDAO.findAll();
		if(bunnies==null) System.out.println("Can't get all bunnies");
		return bunnies;
	}

	
	public Bunny getBunnyById(int id) {
		Bunny tempBunny =bunnyDAO.findById(id);
		if(tempBunny==null) System.out.println("Bunny not found!!");
		return tempBunny;
	}


	//TODO
	public void createBunny(){
		System.out.println("Create a bunny here...");
		Date tempDate = new Date();
		Bunny tempBunny= new Bunny("Peppy","Mini Lop", Gender.Female,4, "Energetic","Peppy.png", AvailabilityStatus.Available,"Healthy",tempDate);

		System.out.println("Saving bunny here...");
		bunnyDAO.save(tempBunny);

		System.out.println("Confirm bunny creation here...");
		Bunny checked =getBunnyById(tempBunny.getId());
		if(checked==null) System.out.println("Bunny not found!!");
		System.out.println("Bunny found: "+ checked);
	}
}
