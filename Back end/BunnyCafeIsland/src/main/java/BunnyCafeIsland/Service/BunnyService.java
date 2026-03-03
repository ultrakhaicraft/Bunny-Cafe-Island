package BunnyCafeIsland.Service;

import BunnyCafeIsland.DTO.Request.BunnyInfoDTORequest;
import BunnyCafeIsland.DTO.Request.ReservationDTORequest;
import BunnyCafeIsland.DTO.Response.BunnyDetailDTOResponse;
import BunnyCafeIsland.DTO.Response.BunnyViewDTOResponse;
import BunnyCafeIsland.DTO.Response.MedicalRecordDTOResponse;
import BunnyCafeIsland.Entity.MedicalRecord;
import BunnyCafeIsland.Entity.Reservation;
import BunnyCafeIsland.Exception.BadRequestException;
import BunnyCafeIsland.Repository.MedicalRecordRepository;
import BunnyCafeIsland.Service.Interface.IBunnyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import BunnyCafeIsland.Entity.Bunny;

import BunnyCafeIsland.Enums.AvailabilityStatus;

import BunnyCafeIsland.Repository.BunnyRepository;

import java.util.List;
import java.util.Optional;


@Service
public class BunnyService implements IBunnyService {

    private final BunnyRepository bunnyRepository;
	private final MedicalRecordRepository medicalRecordRepository;

	@Autowired
	public BunnyService(BunnyRepository bunnyRepository, MedicalRecordRepository medicalRecordRepository){
		this.medicalRecordRepository = medicalRecordRepository;
		this.bunnyRepository=bunnyRepository;
	}


	@Override
	public Page<BunnyViewDTOResponse> GetAllBunniesPaged(Pageable pageable) {
		Page<Bunny> data = bunnyRepository.findAll(pageable);
		return data.map(this::mapToViewDTO);
	}

	@Override
	public BunnyDetailDTOResponse getBunnyById(int id) {
		Optional<Bunny> result =bunnyRepository.findById(id);
		Bunny bunny =null;
		if(result.isPresent()){
			bunny=result.get();
		}else{
			throw new BadRequestException("Bunny not found - ID: "+id);
		}
		BunnyDetailDTOResponse bunnyInfoResponse= mapToDetailDTO(bunny);

		//Add list of medical record from bunny
		List<MedicalRecordDTOResponse> medicalRecordList = bunny.getMedicalRecordsList().stream()
				.map(this::mapMedicalRecordToDTO).toList();

		bunnyInfoResponse.setMedicalRecordsList(medicalRecordList);

		return bunnyInfoResponse;
	}

	@Override
	public BunnyViewDTOResponse create(BunnyInfoDTORequest dtoRequest) {
		Bunny bunny = mapToEntity(dtoRequest);
		Bunny addedBunny = bunnyRepository.save(bunny);
		return mapToViewDTO(addedBunny);
	}

	@Override
	public BunnyViewDTOResponse update(BunnyInfoDTORequest dtoRequest, int bunnyId) {
		Bunny exisitingBunny = bunnyRepository.findById(bunnyId)
				.orElseThrow(() -> new BadRequestException("Bunny not found - ID: " + bunnyId));

		updateEntityFromDTO(exisitingBunny, dtoRequest);

		Bunny updatedBunny= bunnyRepository.save(exisitingBunny);
		return mapToViewDTO(updatedBunny);
	}

	@Override
	public BunnyViewDTOResponse changeAvailabilityStatus(int id, AvailabilityStatus status) {
		Bunny exisitingBunny = bunnyRepository.findById(id)
				.orElseThrow(() -> new BadRequestException("Bunny not found - ID: " + id));

		exisitingBunny.setAvailabilityStatus(status);

		Bunny updatedBunny= bunnyRepository.save(exisitingBunny);
		return mapToViewDTO(updatedBunny);
	}

	@Override
	public void addMedicalRecordToBunny(int bunnyId, int medicalRecordId) {
		//Precondition: Medical Record and Bunny with the Id must exist/created in the database
		MedicalRecord existingMedicalRecord = medicalRecordRepository.findById(medicalRecordId)
				.orElseThrow(() -> new BadRequestException("Medical record not found - ID: " + medicalRecordId));

		Bunny exisitingBunny = bunnyRepository.findById(bunnyId)
				.orElseThrow(() -> new BadRequestException("Bunny not found - ID: " + bunnyId));

		//Add a medical record to bunny
		exisitingBunny.getMedicalRecordsList().add(existingMedicalRecord);
		bunnyRepository.save(exisitingBunny);
	}

	@Override
	public void delete(int id) {
		bunnyRepository.deleteById(id);
	}


	private BunnyViewDTOResponse mapToViewDTO(Bunny bunny) {
		BunnyViewDTOResponse bunnyViewDTOResponse = new BunnyViewDTOResponse();
		if(bunny!=null){
			bunnyViewDTOResponse.setId(bunny.getId());
		}else{
			bunnyViewDTOResponse.setId(0);
		}
		bunnyViewDTOResponse.setName(bunny.getName());
		bunnyViewDTOResponse.setBreed(bunny.getBreed());
		bunnyViewDTOResponse.setGender(bunny.getGender());
		bunnyViewDTOResponse.setAge(bunny.getAge());
		bunnyViewDTOResponse.setImage(bunny.getImage());
		bunnyViewDTOResponse.setDescription(bunny.getDescription());
		bunnyViewDTOResponse.setAvailabilityStatus(bunny.getAvailabilityStatus());
		bunnyViewDTOResponse.setHealthStatus(bunny.getHealthStatus());
		bunnyViewDTOResponse.setDateAdded(bunny.getDateAdded());
		return bunnyViewDTOResponse;
	}

	private BunnyDetailDTOResponse mapToDetailDTO(Bunny bunny) {
		BunnyDetailDTOResponse bunnyDetailDTOResponse = new BunnyDetailDTOResponse();
		if(bunny!=null){
			bunnyDetailDTOResponse.setId(bunny.getId());
		}else{
			bunnyDetailDTOResponse.setId(0);
		}
		bunnyDetailDTOResponse.setName(bunny.getName());
		bunnyDetailDTOResponse.setBreed(bunny.getBreed());
		bunnyDetailDTOResponse.setGender(bunny.getGender());
		bunnyDetailDTOResponse.setAge(bunny.getAge());
		bunnyDetailDTOResponse.setImage(bunny.getImage());
		bunnyDetailDTOResponse.setDescription(bunny.getDescription());
		bunnyDetailDTOResponse.setAvailabilityStatus(bunny.getAvailabilityStatus());
		bunnyDetailDTOResponse.setHealthStatus(bunny.getHealthStatus());
		bunnyDetailDTOResponse.setDateAdded(bunny.getDateAdded());
		return bunnyDetailDTOResponse;
	}

	private MedicalRecordDTOResponse mapMedicalRecordToDTO(MedicalRecord medicalRecord){
		MedicalRecordDTOResponse medicalRecordDTOResponse = new MedicalRecordDTOResponse();
		if(medicalRecord!=null){
			medicalRecordDTOResponse.setId(medicalRecord.getId());
		}else{
			medicalRecordDTOResponse.setId(0);
		}
		medicalRecordDTOResponse.setId(medicalRecord.getId());
		medicalRecordDTOResponse.setClinic(medicalRecord.getClinic());
		medicalRecordDTOResponse.setMedicalHistory(medicalRecord.getMedicalHistory());
		medicalRecordDTOResponse.setTreatmentDetails(medicalRecord.getTreatmentDetails());
		medicalRecordDTOResponse.setDate(medicalRecord.getDate());
		return medicalRecordDTOResponse;
	}

	private Bunny mapToEntity(BunnyInfoDTORequest bunnyInfoDTORequest) {
		Bunny bunny = new Bunny();
		bunny.setId(0); //Set Id as 0 to trigger auto increment
		bunny.setName(bunnyInfoDTORequest.getName());
		bunny.setAge(bunnyInfoDTORequest.getAge());
		bunny.setBreed(bunnyInfoDTORequest.getBreed());
		bunny.setGender(bunnyInfoDTORequest.getGender());
		bunny.setImage(bunnyInfoDTORequest.getImage());
		bunny.setDescription(bunnyInfoDTORequest.getDescription());
		bunny.setAvailabilityStatus(bunnyInfoDTORequest.getAvailabilityStatus());
		bunny.setHealthStatus(bunnyInfoDTORequest.getHealthStatus());
		bunny.setDateAdded(bunnyInfoDTORequest.getDateAdded());
		return bunny;
	}

	private void updateEntityFromDTO(Bunny bunny, BunnyInfoDTORequest bunnyInfoDTORequest) {
		bunny.setName(bunnyInfoDTORequest.getName());
		bunny.setAge(bunnyInfoDTORequest.getAge());
		bunny.setBreed(bunnyInfoDTORequest.getBreed());
		bunny.setGender(bunnyInfoDTORequest.getGender());
		bunny.setImage(bunnyInfoDTORequest.getImage());
		bunny.setDescription(bunnyInfoDTORequest.getDescription());
		bunny.setAvailabilityStatus(bunnyInfoDTORequest.getAvailabilityStatus());
		bunny.setHealthStatus(bunnyInfoDTORequest.getHealthStatus());
		bunny.setDateAdded(bunnyInfoDTORequest.getDateAdded());
	}
}
