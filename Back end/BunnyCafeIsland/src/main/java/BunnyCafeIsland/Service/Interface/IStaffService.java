package BunnyCafeIsland.Service.Interface;

import BunnyCafeIsland.DTO.Request.StaffInfoDTORequest;
import BunnyCafeIsland.DTO.Response.StaffInfoDTOResponse;
import BunnyCafeIsland.Entity.Staff;
import BunnyCafeIsland.Enums.StaffStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IStaffService {
    StaffInfoDTOResponse create(StaffInfoDTORequest staffInfoDTORequest);
    StaffInfoDTOResponse update(StaffInfoDTORequest staffInfoDTORequest, int staffId);
    void delete(int id);
    Page<StaffInfoDTOResponse> getAllStaffsPageable(Pageable pageable);
    StaffInfoDTOResponse getStaffById(int id);
    StaffInfoDTOResponse changeStatus(int staffId, StaffStatus status);
}
