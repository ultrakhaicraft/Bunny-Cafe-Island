package BunnyCafeIsland.Service.Interface;

import BunnyCafeIsland.DTO.Request.BunnyInfoDTORequest;
import BunnyCafeIsland.DTO.Response.BunnyDetailDTOResponse;
import BunnyCafeIsland.DTO.Response.BunnyViewDTOResponse;
import BunnyCafeIsland.Entity.Bunny;
import BunnyCafeIsland.Enums.AvailabilityStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IBunnyService {
    Page<BunnyViewDTOResponse> GetAllBunniesPaged(Pageable page);
    BunnyDetailDTOResponse getBunnyById(int id);
    BunnyViewDTOResponse create(BunnyInfoDTORequest dtoRequest);
    BunnyViewDTOResponse update(BunnyInfoDTORequest dtoRequest, int bunnyId);
    BunnyViewDTOResponse changeAvailabilityStatus(int id, AvailabilityStatus status);
    void addMedicalRecordToBunny(int bunnyId, int medicalRecordId);
    void delete(int id);
}
