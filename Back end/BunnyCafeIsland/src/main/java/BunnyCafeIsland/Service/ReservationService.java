package BunnyCafeIsland.Service;

import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import BunnyCafeIsland.Entity.Reservation;
import BunnyCafeIsland.Exception.BadRequestException;
import BunnyCafeIsland.Repository.ReservationRepository;

@Service
public class ReservationService {

    private ReservationRepository reservationRepository;

    @Autowired
    public ReservationService(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    public List<Reservation> getAllReservation(){
        return reservationRepository.findAll();
    }

    public Reservation getReservationById(int id){
        Optional<Reservation> result =reservationRepository.findById(id);
        Reservation aReservation =null;
        if(result.isPresent()){
            aReservation=result.get();
        }else{
            throw new BadRequestException("Reservation not found - ID: "+id);
        }
        return aReservation;
    }

    public Reservation save(Reservation aReservation){
        return reservationRepository.save(aReservation);
    }

    public void deleteReservationById(int id){
        reservationRepository.deleteById(id);
    }

}
