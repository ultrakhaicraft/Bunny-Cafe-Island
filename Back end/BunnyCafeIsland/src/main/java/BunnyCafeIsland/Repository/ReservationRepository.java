package BunnyCafeIsland.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import BunnyCafeIsland.Entity.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation, Integer> {
    //JpaRepos use entity class and type of primary key

    

}
