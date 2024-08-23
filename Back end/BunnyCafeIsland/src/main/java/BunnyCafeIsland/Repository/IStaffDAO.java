package BunnyCafeIsland.Repository;

import java.util.List;


import BunnyCafeIsland.Entity.Staff;

public interface IStaffDAO {
    Staff save(Staff aStaff);
    Staff findById(int id);
    List<Staff> findAll();
    List<Staff> findByPhone(String phone);
    List<Staff> findByEmail(String email);
    List<Staff> findByName(String name);
    void update(Staff aStaff);
    void softRemove(Staff aStaff);
    void hardRemove(int id);
}
