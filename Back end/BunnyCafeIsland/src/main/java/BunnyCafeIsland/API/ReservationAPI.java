package BunnyCafeIsland.API;

import java.util.List;

import BunnyCafeIsland.DTO.Request.ReservationDTORequest;
import BunnyCafeIsland.DTO.Response.ApiResponse;
import BunnyCafeIsland.DTO.Response.MenuItemDTOResponse;
import BunnyCafeIsland.DTO.Response.ReservationDTOResponse;
import BunnyCafeIsland.Service.Interface.IReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import BunnyCafeIsland.Entity.Reservation;
import BunnyCafeIsland.Exception.BadRequestException;
import BunnyCafeIsland.Service.ReservationService;


@RestController
@RequestMapping("/api")
public class ReservationAPI {

    private final IReservationService reservationService;

    @Autowired
    public ReservationAPI(ReservationService reservationService){
        this.reservationService = reservationService;
    }

    @GetMapping("/reservations")
    public ApiResponse<Page<ReservationDTOResponse>> getAllReservationsWithPagination(@RequestParam(defaultValue = "0") int page,
                                                              @RequestParam(defaultValue = "5") int size) {

        Pageable pageable = PageRequest.of(page, size);
        Page<ReservationDTOResponse> data = reservationService.getAllPageable(pageable);
        return ApiResponse.success("Get all reservation as Page success",data);
    }

    @GetMapping("/reservations/{reservationId}")
    public ApiResponse<ReservationDTOResponse> getReservationById(@PathVariable int reservationId) {
        ReservationDTOResponse response = reservationService.getById(reservationId);
        if(response==null){
            throw new BadRequestException("Menu Item not found - ID: "+reservationId);
        }
        return ApiResponse.success("Get a Menu Item success", response);
    }
    
    @PostMapping("/reservations")
    public ApiResponse<ReservationDTOResponse> addReservation(@RequestBody ReservationDTORequest dtoRequest) {
        ReservationDTOResponse response = reservationService.add(dtoRequest);
        return ApiResponse.success("Create new reservation success", response);
    }
    
    @PutMapping("/reservations/{reservationId}")
    public ApiResponse<ReservationDTOResponse> updateReservation(@RequestBody ReservationDTORequest dtoRequest,@PathVariable int reservationId) {
        ReservationDTOResponse response=reservationService.update(reservationId,dtoRequest);
        return ApiResponse.success("Update reservation success", response);
    }
    
    @DeleteMapping("/reservations/{reservationId}")
    public ApiResponse<Integer> deleteReservation(@PathVariable int reservationId) {
        ReservationDTOResponse data = reservationService.getById(reservationId);
        if(data==null){
            throw new BadRequestException("Reservation not found  ID: "+reservationId);
        }
        reservationService.delete(reservationId);
        return ApiResponse.success("Delete Reservation with ID: " + reservationId,reservationId);
    }
}
