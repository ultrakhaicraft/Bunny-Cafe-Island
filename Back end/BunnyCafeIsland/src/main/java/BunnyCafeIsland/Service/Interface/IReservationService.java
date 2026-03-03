package BunnyCafeIsland.Service.Interface;

import BunnyCafeIsland.DTO.Request.MenuItemDTORequest;
import BunnyCafeIsland.DTO.Request.ReservationDTORequest;
import BunnyCafeIsland.DTO.Response.MenuItemDTOResponse;
import BunnyCafeIsland.DTO.Response.ReservationDTOResponse;
import BunnyCafeIsland.Entity.Reservation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IReservationService {
    Page<ReservationDTOResponse> getAllPageable(Pageable pageable);
    ReservationDTOResponse getById(int id);
    ReservationDTOResponse add(ReservationDTORequest dtoRequest);
    ReservationDTOResponse update(int reservationId, ReservationDTORequest dtoRequest);
    void delete(int id);
}
