package BunnyCafeIsland.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import BunnyCafeIsland.Entity.Staff;

public interface StaffRepository extends JpaRepository<Staff, Integer> {
    Staff findByPhone(String phone);
    Optional<Staff> findByEmail(String email);
    List<Staff> findByName(String name);
    
}
