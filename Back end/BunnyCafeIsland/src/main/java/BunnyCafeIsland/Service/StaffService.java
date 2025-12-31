package BunnyCafeIsland.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import BunnyCafeIsland.Service.Interface.IStaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import BunnyCafeIsland.Entity.Staff;
import BunnyCafeIsland.Enums.AvailabilityStatus;
import BunnyCafeIsland.Enums.Gender;
import BunnyCafeIsland.Enums.StaffStatus;
import BunnyCafeIsland.Exception.BadRequestException;
import BunnyCafeIsland.Repository.StaffRepository;



@Service
public class StaffService implements IStaffService {

    private final StaffRepository staffRepository;

	@Autowired
	public StaffService(StaffRepository staffRepository){
		this.staffRepository=staffRepository;
	}	

	
	public Staff save(Staff aStaff){
		return staffRepository.save(aStaff);
	}

	
    public void softRemove(int id){
		Staff newStaff =getStaffById(id);
		if(newStaff==null) {
			System.out.println("Staff not found!!, Exiting soft remove");
			return;
		}
		newStaff.setStatus(StaffStatus.Inactive);
		staffRepository.save(newStaff);
		System.out.println("New Staff info"+newStaff);
	}

	
	public void hardRemove(int id){
		staffRepository.deleteById(id);

	}


	public List<Staff> getAllStaffs() {
		List<Staff> staffs = staffRepository.findAll();
		if(staffs==null) System.out.println("Can't get all staffs");
		return staffs;
	}

	
	public Staff getStaffById(int id) {
		Optional<Staff> result =staffRepository.findById(id);
		Staff aStaff=null;
		if(result.isPresent()) {
			aStaff=result.get();
		}else{
			throw new BadRequestException( "Staff not found - ID: "+id);
		}
		return aStaff;
	}


	

}
