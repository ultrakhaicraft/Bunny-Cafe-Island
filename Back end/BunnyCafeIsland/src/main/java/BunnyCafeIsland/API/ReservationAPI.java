package BunnyCafeIsland.API;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import BunnyCafeIsland.Entity.Reservation;
import BunnyCafeIsland.Exception.BadRequestException;
import BunnyCafeIsland.Service.ReservationService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;




@RestController
@RequestMapping("/api")
public class ReservationAPI {

    private ReservationService reservationService;

    @Autowired
    public ReservationAPI(ReservationService reservationService){
        this.reservationService = reservationService;
    }

    @GetMapping("/reservations")
    public List<Reservation> getAllReservations() {
        List<Reservation> reservationsList= reservationService.getAllReservation();
        return reservationsList;
    }

    @GetMapping("/reservations/{reservationId}")
    public Reservation getReservationById(@PathVariable int reservationId) {
        Reservation reservation = reservationService.getReservationById(reservationId);
        return reservation;
    }
    
    @PostMapping("/reservations")
    public Reservation addReservation(@RequestBody Reservation aReservation) {
        aReservation.setId(0);
        Reservation dbReservation=reservationService.save(aReservation);
        
        return dbReservation;
    }
    
    @PutMapping("/reservations")
    public Reservation updateReservation(@RequestBody Reservation aReservation) {
        Reservation dbReservation=reservationService.save(aReservation);
        return dbReservation;
    }
    
    @DeleteMapping("/reservations/{reservationId}")
    public String deleteReservation(@PathVariable int reservationId) {
        Reservation aReservation = reservationService.getReservationById(reservationId);
        if(aReservation==null){
            throw new BadRequestException("Reservation not found  ID: "+reservationId);
        }
        reservationService.deleReservationById(reservationId);
        return "Delete Reservation ID: "+reservationId;
    }
}
