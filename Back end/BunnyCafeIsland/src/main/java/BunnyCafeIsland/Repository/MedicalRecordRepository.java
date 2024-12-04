package BunnyCafeIsland.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import BunnyCafeIsland.Entity.MedicalRecord;

public interface MedicalRecordRepository extends JpaRepository<MedicalRecord,Integer> {

}
