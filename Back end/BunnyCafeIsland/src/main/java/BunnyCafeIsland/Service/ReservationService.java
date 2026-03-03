package BunnyCafeIsland.Service;

import java.time.LocalDateTime;
import java.util.Optional;


import BunnyCafeIsland.DTO.Request.MenuItemDTORequest;
import BunnyCafeIsland.DTO.Request.ReservationDTORequest;
import BunnyCafeIsland.DTO.Response.MenuItemDTOResponse;
import BunnyCafeIsland.DTO.Response.ReservationDTOResponse;
import BunnyCafeIsland.Entity.MenuItem;
import BunnyCafeIsland.Service.Interface.IReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import BunnyCafeIsland.Entity.Reservation;
import BunnyCafeIsland.Exception.BadRequestException;
import BunnyCafeIsland.Repository.ReservationRepository;

@Service
public class ReservationService implements IReservationService {

    private final ReservationRepository reservationRepository;

    @Autowired
    public ReservationService(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    @Override
    public ReservationDTOResponse getById(int id){
        Optional<Reservation> result =reservationRepository.findById(id);
        Reservation reservation =null;
        if(result.isPresent()){
            reservation=result.get();
        }else{
            throw new BadRequestException("Reservation not found - ID: "+id);
        }
        return mapToDTO(reservation);
    }

    @Override
    public ReservationDTOResponse add(ReservationDTORequest dtoRequest) {
        Reservation reservation = mapToEntity(dtoRequest);
        Reservation addedReservation= reservationRepository.save(reservation);
        return mapToDTO(addedReservation);
    }

    @Override
    public ReservationDTOResponse update(int reservationId, ReservationDTORequest dtoRequest) {
        Reservation existingReservation = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new BadRequestException("Reservation not found - ID: " + reservationId));

        updateEntityFromDTO(existingReservation, dtoRequest);

        Reservation updatedReservation= reservationRepository.save(existingReservation);
        return mapToDTO(updatedReservation);
    }



    @Override
    public Page<ReservationDTOResponse> getAllPageable(Pageable pageable){
        Page<Reservation> reservationPage= reservationRepository.findAll(pageable);

        //Convert to DTO Response page
        return reservationPage.map(this::mapToDTO);
    }

    @Override
    public void delete(int id) {
        reservationRepository.deleteById(id);
    }


    private ReservationDTOResponse mapToDTO(Reservation reservation) {
        ReservationDTOResponse reservationDTOResponse = new ReservationDTOResponse();
        if(reservation!=null){
            reservationDTOResponse.setId(reservation.getId());
        }else{
            reservationDTOResponse.setId(0);
        }
        reservationDTOResponse.setName(reservation.getName());
        reservationDTOResponse.setPhoneNumber(reservation.getPhone());
        reservationDTOResponse.setStatus(reservation.getStatus());
        reservationDTOResponse.setComment(reservation.getComment());
        reservationDTOResponse.setExpectedDate(reservation.getExpectedDate());
        reservationDTOResponse.setExpectedTime(reservation.getExpectedTime());
        reservationDTOResponse.setTableNumber(reservation.getTableNumber());
        return reservationDTOResponse;
    }


    private Reservation mapToEntity(ReservationDTORequest reservationDTORequest) {
        Reservation reservation = new Reservation();
        reservation.setId(0); //Set Id as 0 to trigger auto increment
        reservation.setName(reservationDTORequest.getName());
        reservation.setPhone(reservationDTORequest.getPhoneNumber());
        reservation.setStatus(reservationDTORequest.getStatus());
        reservation.setComment(reservationDTORequest.getComment());
        reservation.setExpectedDate(reservationDTORequest.getExpectedDate());
        reservation.setExpectedTime(reservationDTORequest.getExpectedTime());
        reservation.setTableNumber(reservationDTORequest.getTableNumber());
        return reservation;
    }

    private void updateEntityFromDTO(Reservation reservation, ReservationDTORequest dtoRequest) {
        reservation.setName(dtoRequest.getName());
        reservation.setPhone(dtoRequest.getPhoneNumber());
        reservation.setStatus(dtoRequest.getStatus());
        reservation.setComment(dtoRequest.getComment());
        reservation.setExpectedDate(dtoRequest.getExpectedDate());
        reservation.setExpectedTime(dtoRequest.getExpectedTime());
        reservation.setTableNumber(dtoRequest.getTableNumber());
    }

}
