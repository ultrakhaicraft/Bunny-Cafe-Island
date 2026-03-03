package BunnyCafeIsland.Service;

import java.util.List;
import java.util.Optional;

import BunnyCafeIsland.DTO.Request.ReservationDTORequest;
import BunnyCafeIsland.DTO.Request.StaffInfoDTORequest;
import BunnyCafeIsland.DTO.Response.StaffInfoDTOResponse;
import BunnyCafeIsland.Entity.Reservation;
import BunnyCafeIsland.Enums.Role;
import BunnyCafeIsland.Service.Interface.IStaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import BunnyCafeIsland.Entity.Staff;
import BunnyCafeIsland.Enums.StaffStatus;
import BunnyCafeIsland.Exception.BadRequestException;
import BunnyCafeIsland.Repository.StaffRepository;



@Service
//CRUD Staff Info
public class StaffService implements IStaffService {

    private final StaffRepository staffRepository;

	@Autowired
	public StaffService(StaffRepository staffRepository){
		this.staffRepository=staffRepository;
	}


	@Override
	public StaffInfoDTOResponse create(StaffInfoDTORequest staffInfoDTORequest) {
		Staff staff = mapToEntity(staffInfoDTORequest);
		Staff addedStaff= staffRepository.save(staff);
		return mapToDTO(addedStaff);
	}

	@Override
	public StaffInfoDTOResponse update(StaffInfoDTORequest staffInfoDTORequest, int staffId) {
		Staff existingStaff = staffRepository.findById(staffId)
				.orElseThrow(() -> new BadRequestException("Staff not found - ID: " + staffId));

		updateEntityFromDTO(existingStaff, staffInfoDTORequest);

		Staff updatedReservation= staffRepository.save(existingStaff);
		return mapToDTO(updatedReservation);
	}

	@Override
	public void delete(int id) {
		staffRepository.deleteById(id);
	}

	@Override
	public Page<StaffInfoDTOResponse> getAllStaffsPageable(Pageable pageable) {
		Page<Staff> staffPage= staffRepository.findAll(pageable);

		//Convert to DTO Response page
		return staffPage.map(this::mapToDTO);
	}

	@Override
	public StaffInfoDTOResponse getStaffById(int id) {
		Optional<Staff> result =staffRepository.findById(id);
		Staff staffInfo =null;
		if(result.isPresent()){
			staffInfo=result.get();
		}else{
			throw new BadRequestException("staffInfo not found - ID: "+id);
		}
		return mapToDTO(staffInfo);
	}

	@Override
	public StaffInfoDTOResponse changeStatus(int staffId,StaffStatus newStatus) {
		Staff existingStaff = staffRepository.findById(staffId)
				.orElseThrow(() -> new BadRequestException("Staff not found - ID: " + staffId));

		//Only Update status
		existingStaff.setStatus(newStatus);

		Staff updatedStaff= staffRepository.save(existingStaff);
		return mapToDTO(updatedStaff);
	}

	private StaffInfoDTOResponse mapToDTO(Staff staff) {
		StaffInfoDTOResponse staffInfoDTOResponse = new StaffInfoDTOResponse();
		if(staff!=null){
			staffInfoDTOResponse.setId(staff.getId());
		}else{
			staffInfoDTOResponse.setId(0);
		}
		staffInfoDTOResponse.setName(staff.getName());
		staffInfoDTOResponse.setPhoneNumber(staff.getPhone());
		staffInfoDTOResponse.setEmail(staff.getEmail());
		staffInfoDTOResponse.setProfilePicture(staff.getProfilePicture());
		staffInfoDTOResponse.setRole(String.valueOf(staff.getRole()));
		staffInfoDTOResponse.setStaffStatus(String.valueOf(staff.getStatus()));
		return staffInfoDTOResponse;
	}


	private Staff mapToEntity(StaffInfoDTORequest staffInfoDTORequest) {
		Staff staff = new Staff();
		staff.setId(0); //Set Id as 0 to trigger auto increment
		staff.setName(staffInfoDTORequest.getName());
		staff.setPhone(staffInfoDTORequest.getPhoneNumber());
		staff.setEmail(staffInfoDTORequest.getEmail());
		staff.setProfilePicture(staffInfoDTORequest.getProfilePicture());
		staff.setRole(Role.valueOf(staffInfoDTORequest.getRole()));
		staff.setStatus(StaffStatus.valueOf(staffInfoDTORequest.getStaffStatus()));
		return staff;
	}

	private void updateEntityFromDTO(Staff staff, StaffInfoDTORequest staffInfoDTORequest) {
		staff.setName(staffInfoDTORequest.getName());
		staff.setPhone(staffInfoDTORequest.getPhoneNumber());
		staff.setEmail(staffInfoDTORequest.getEmail());
		staff.setProfilePicture(staffInfoDTORequest.getProfilePicture());
		staff.setRole(Role.valueOf(staffInfoDTORequest.getRole()));
		staff.setStatus(StaffStatus.valueOf(staffInfoDTORequest.getStaffStatus()));
	}
}
