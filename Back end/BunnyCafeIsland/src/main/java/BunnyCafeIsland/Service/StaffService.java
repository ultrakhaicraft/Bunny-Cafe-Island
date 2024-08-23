package BunnyCafeIsland.Service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import BunnyCafeIsland.Entity.Staff;
import BunnyCafeIsland.Enums.AvailabilityStatus;
import BunnyCafeIsland.Enums.Gender;
import BunnyCafeIsland.Repository.StaffDAO;


@Service
public class StaffService {

    private StaffDAO staffDAO;

	@Autowired
	public StaffService(StaffDAO staffDAO){
		this.staffDAO=staffDAO;
	}	

	@Transactional
	public Staff save(Staff aStaff){
		return staffDAO.save(aStaff);
	}

	@Transactional
    public void softRemove(int id){
		Staff newBun =getStaffById(id);
		if(newBun==null) {
			System.out.println("Bunny not found!!, Exiting soft remove");
			return;
		}
		staffDAO.softRemove(newBun);
		System.out.println("New bunny info"+newBun);
	}

	@Transactional
	public void hardRemove(int id){
		staffDAO.hardRemove(id);

	}


	public List<Staff> getAllStaffs() {
		List<Staff> staffs = staffDAO.findAll();
		if(staffs==null) System.out.println("Can't get all staffs");
		return staffs;
	}

	
	public Staff getStaffById(int id) {
		Staff tempStaff =staffDAO.findById(id);
		if(tempStaff==null) System.out.println("Staff not found!!");
		return tempStaff;
	}


	

}
