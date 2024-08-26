package BunnyCafeIsland.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import BunnyCafeIsland.Entity.Bunny;

import BunnyCafeIsland.Enums.AvailabilityStatus;

import BunnyCafeIsland.Exception.BadRequestException;

import BunnyCafeIsland.Repository.BunnyRepository;


@Service
public class BunnyService {

	
    private BunnyRepository bunnyRepository;

	@Autowired
	public BunnyService(BunnyRepository bunnyRepository){
		this.bunnyRepository=bunnyRepository;
	}

	

	public List<Bunny> getAllBunny() {
		return bunnyRepository.findAll();
	}

	public List<Bunny> getBunnyByBreed(String breed) {
		List<Bunny> result = bunnyRepository.findByBreed(breed);
		return result;
	}

	
	public Bunny getBunnyById(int id) {
		Optional<Bunny> result =bunnyRepository.findById(id);
		Bunny aBunny=null;
		if(result.isPresent()) {
			aBunny=result.get();
		}else{
			throw new BadRequestException( "Bunny not found - ID: "+id);

		}
		return aBunny;
	}

	public Bunny save(Bunny aBunny){
        return bunnyRepository.save(aBunny);
    }


    public Bunny changeStatus(int id, AvailabilityStatus status){
		Bunny newBun =getBunnyById(id);
		if(newBun==null) {
			throw new BadRequestException( "Bunny not found, exiting change status - ID: "+id);
		}
		newBun.setAvailabilityStatus(status);
		save(newBun);
		return newBun;
	}

	public void deleteBunnyById(int id){
        bunnyRepository.deleteById(id);
    }


	
}
