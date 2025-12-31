package BunnyCafeIsland.Service.Interface;

import BunnyCafeIsland.Entity.Staff;

import java.util.List;

public interface IStaffService {
    Staff save(Staff aStaff);
    void softRemove(int id);
    void hardRemove(int id);
    List<Staff> getAllStaffs();
    Staff getStaffById(int id);
}
