package BunnyCafeIsland.Service.Interface;

import BunnyCafeIsland.Entity.Reservation;

import java.util.List;

public interface IReservationService {
    List<Reservation> getAllReservation();
    Reservation getReservationById(int id);
    Reservation save(Reservation aReservation);
    void deleteReservationById(int id);
}
