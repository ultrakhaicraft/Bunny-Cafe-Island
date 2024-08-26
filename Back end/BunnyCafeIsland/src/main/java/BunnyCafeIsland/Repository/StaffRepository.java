package BunnyCafeIsland.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import BunnyCafeIsland.Entity.Staff;

public interface StaffRepository extends JpaRepository<Staff, Integer> {
    Staff findByPhone(String phone);
    Staff findByEmail(String email);
    List<Staff> findByName(String name);
    
}
