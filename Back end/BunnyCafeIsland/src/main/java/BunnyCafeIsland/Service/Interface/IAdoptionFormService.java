package BunnyCafeIsland.Service.Interface;

import BunnyCafeIsland.DTO.Request.AdoptionFormDTORequest;
import BunnyCafeIsland.DTO.Request.BunnyInfoDTORequest;
import BunnyCafeIsland.DTO.Response.AdoptionFormDetailDTOResponse;
import BunnyCafeIsland.DTO.Response.AdoptionFormViewDTOResponse;
import BunnyCafeIsland.DTO.Response.BunnyDetailDTOResponse;
import BunnyCafeIsland.DTO.Response.BunnyViewDTOResponse;
import BunnyCafeIsland.Entity.AdoptionForm;
import BunnyCafeIsland.Enums.AdoptionFormStatus;
import BunnyCafeIsland.Enums.AvailabilityStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IAdoptionFormService {
    Page<AdoptionFormViewDTOResponse> GetAllAdoptionFormsPaged(Pageable page);
    AdoptionFormDetailDTOResponse getAdoptionFormById(int id);
    AdoptionFormViewDTOResponse create(AdoptionFormDTORequest dtoRequest);
    AdoptionFormViewDTOResponse update(AdoptionFormDTORequest dtoRequest, int bunnyId);
    AdoptionFormViewDTOResponse changeAdoptionFormStatus(int id, AdoptionFormStatus status);
    void addBunnyToAdoptionForm(int adoptionFormId, int bunnyId);
    void delete(int id);
}
