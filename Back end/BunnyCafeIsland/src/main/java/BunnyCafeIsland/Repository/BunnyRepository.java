package BunnyCafeIsland.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import BunnyCafeIsland.Entity.Bunny;

public interface BunnyRepository extends JpaRepository<Bunny, Integer> {

    
    List<Bunny> findByBreed(String breed);
    
    

}
