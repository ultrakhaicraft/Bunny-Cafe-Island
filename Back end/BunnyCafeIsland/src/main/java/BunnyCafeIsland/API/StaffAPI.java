package BunnyCafeIsland.API;


import BunnyCafeIsland.DTO.Request.StaffInfoDTORequest;
import BunnyCafeIsland.DTO.Response.ApiResponse;
import BunnyCafeIsland.DTO.Response.ReservationDTOResponse;
import BunnyCafeIsland.DTO.Response.StaffInfoDTOResponse;
import BunnyCafeIsland.Service.Interface.IStaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import BunnyCafeIsland.Entity.Staff;
import BunnyCafeIsland.Exception.BadRequestException;
import BunnyCafeIsland.Service.StaffService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;





@RestController
@RequestMapping("/api")
public class StaffAPI {

    private final IStaffService staffService;

    @Autowired
    public StaffAPI(StaffService staffService){
        this.staffService=staffService;
    }

    @GetMapping("/staffs")
    public ApiResponse<Page<StaffInfoDTOResponse>> getAllStaffWithPagination(@RequestParam(defaultValue = "0") int page,
                                                                                      @RequestParam(defaultValue = "5") int size) {

        Pageable pageable = PageRequest.of(page, size);
        Page<StaffInfoDTOResponse> data = staffService.getAllStaffsPageable(pageable);
        return ApiResponse.success("Get all staff as Page success",data);
    }

    @GetMapping("/staffs/{staffId}")
    public ApiResponse<StaffInfoDTOResponse> getAStaff(@PathVariable int staffId) {
        StaffInfoDTOResponse staff = staffService.getStaffById(staffId);
        
        if(staff ==null){
            throw new BadRequestException("Staff not found - ID: "+staffId);
        }

        return ApiResponse.success("Get a Staff Info success", staff);

    }

    @PostMapping("/staffs")
    public ApiResponse<StaffInfoDTOResponse> addStaff(@RequestBody StaffInfoDTORequest dtoRequest) {
        StaffInfoDTOResponse response = staffService.create(dtoRequest);
        return ApiResponse.success("Create new staff success", response);
    }
    
    @PutMapping("/staffs/{staffId}")
    public ApiResponse<StaffInfoDTOResponse> updateStaff(@RequestBody StaffInfoDTORequest dtoRequest, @PathVariable int staffId) {
        StaffInfoDTOResponse response=staffService.update(dtoRequest, staffId);
        return ApiResponse.success("Update staff success", response);
    }

    @DeleteMapping("/staffs/{staffId}")
    public ApiResponse<Integer> deleteStaff(@PathVariable int staffId){
        StaffInfoDTOResponse response = staffService.getStaffById(staffId);
        if(response==null){
            throw new BadRequestException("Staff not found - ID: "+staffId);
        }
        staffService.delete(staffId);
        return ApiResponse.success("Delete Reservation with ID: " + staffId,staffId);
    }
}
