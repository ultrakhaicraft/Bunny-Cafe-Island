package BunnyCafeIsland.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import BunnyCafeIsland.Entity.MedicalRecord;
import BunnyCafeIsland.Enums.Gender;
import BunnyCafeIsland.Service.Interface.IBunnyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import BunnyCafeIsland.Entity.Bunny;

import BunnyCafeIsland.Enums.AvailabilityStatus;

import BunnyCafeIsland.Exception.BadRequestException;

import BunnyCafeIsland.Repository.BunnyRepository;


@Service
public class BunnyService implements IBunnyService {

	//Intention DAO/Repository: Bunny and Medical Record

    private final BunnyRepository bunnyRepository;

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


	/*
	Not in a scope, just a test run
	*/
	public void addBunnyWithMedicalRecordExample() throws ParseException {
		try {

			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Date date1 = dateFormat.parse("2024-10-03");
			Date date2 = dateFormat.parse("2024-08-21");
			Date date3 = dateFormat.parse("2024-03-15");

			Bunny bunny1 = new Bunny(
					"Fluffy",
					"Lop",
					Gender.Female,
					2,
					"A very playful and friendly bunny.",
					"https://example.com/images/fluffy.jpg",
					AvailabilityStatus.Available,
					"Healthy",
					new Date(),
					null
			);



			Bunny bunny2 = new Bunny(
					"Snowball",
					"Dutch",
					Gender.Male,
					1,
					"A calm and gentle bunny.",
					"https://example.com/images/snowball.jpg",
					AvailabilityStatus.Available,
					"Healthy",
					date3,
					null
			);

			MedicalRecord record1 = new MedicalRecord(
					new Date(),
					"Vaccination and general checkup",
					"Administered vaccines and general health inspection",
					"Happy Tails Veterinary Clinic"

			);

			MedicalRecord record2 = new MedicalRecord(
					date2,
					"Dental care and grooming",
					"Trimmed overgrown teeth and nails",
					"Bunny Wellness Center"

			);

			MedicalRecord record3 = new MedicalRecord(
					date3,
					"Injury treatment",
					"Treated minor injury on the paw",
					"Pet Care Hospital"

			);

			bunny1.addOneMedicalRecord(record1);
			bunny1.addOneMedicalRecord(record3);

			System.out.println("Saving...");
			bunnyRepository.save(bunny2);
			System.out.println("Saving done !!");

		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
}
