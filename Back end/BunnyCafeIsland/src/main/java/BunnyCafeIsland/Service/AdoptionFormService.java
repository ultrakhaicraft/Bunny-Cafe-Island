package BunnyCafeIsland.Service;

import java.util.List;
import java.util.Optional;

import BunnyCafeIsland.DTO.Request.AdoptionFormDTORequest;
import BunnyCafeIsland.DTO.Request.BunnyInfoDTORequest;
import BunnyCafeIsland.DTO.Response.*;
import BunnyCafeIsland.Entity.Bunny;
import BunnyCafeIsland.Entity.MedicalRecord;
import BunnyCafeIsland.Enums.AdoptionFormStatus;
import BunnyCafeIsland.Service.Interface.IAdoptionFormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import BunnyCafeIsland.Entity.AdoptionForm;
import BunnyCafeIsland.Entity.MenuItem;
import BunnyCafeIsland.Exception.BadRequestException;
import BunnyCafeIsland.Repository.AdoptionFormRepository;
import BunnyCafeIsland.Repository.MenuItemRepository;

@Service
public class AdoptionFormService implements IAdoptionFormService {

    private AdoptionFormRepository adoptionFormRepository;

    @Autowired
    public AdoptionFormService(AdoptionFormRepository adoptionFormRepository) {
        this.adoptionFormRepository = adoptionFormRepository;
    }


    @Override
    public Page<AdoptionFormViewDTOResponse> GetAllAdoptionFormsPaged(Pageable page) {
        return null;
    }

    @Override
    public AdoptionFormDetailDTOResponse getAdoptionFormById(int id) {
        return null;
    }

    @Override
    public AdoptionFormViewDTOResponse create(AdoptionFormDTORequest dtoRequest) {
        return null;
    }

    @Override
    public AdoptionFormViewDTOResponse update(AdoptionFormDTORequest dtoRequest, int bunnyId) {
        return null;
    }

    @Override
    public AdoptionFormViewDTOResponse changeAdoptionFormStatus(int id, AdoptionFormStatus status) {
        return null;
    }

    @Override
    public void addBunnyToAdoptionForm(int adoptionFormId, int bunnyId) {

    }

    @Override
    public void delete(int id) {
        adoptionFormRepository.deleteById(id);
    }

    private AdoptionFormViewDTOResponse mapToViewDTO(AdoptionForm adoptionForm) {
        AdoptionFormViewDTOResponse adoptionFormViewDTOResponse = new AdoptionFormViewDTOResponse();
        if(adoptionForm!=null){
            adoptionFormViewDTOResponse.setId(adoptionForm.getId());
        }else{
            adoptionFormViewDTOResponse.setId(0);
        }
        adoptionFormViewDTOResponse.setName(adoptionForm.getName());
        adoptionFormViewDTOResponse.setPhone(adoptionForm.getPhone());
        adoptionFormViewDTOResponse.setExpectedPickupDate(adoptionForm.getExpectedPickupDate());
        adoptionFormViewDTOResponse.setExpectedPickupTime(adoptionForm.getExpectedPickupTime());
        adoptionFormViewDTOResponse.setRequestDate(adoptionForm.getRequestDate());
        adoptionFormViewDTOResponse.setStatus(adoptionForm.getStatus());

        return adoptionFormViewDTOResponse;
    }

    private AdoptionFormDetailDTOResponse mapToDetailDTO(AdoptionForm adoptionForm) {
        AdoptionFormDetailDTOResponse adoptionFormDetailDTOResponse = new AdoptionFormDetailDTOResponse();
        if(adoptionForm!=null){
            adoptionFormDetailDTOResponse.setId(adoptionForm.getId());
        }else{
            adoptionFormDetailDTOResponse.setId(0);
        }
        adoptionFormDetailDTOResponse.setName(adoptionForm.getName());
        adoptionFormDetailDTOResponse.setPhone(adoptionForm.getPhone());
        adoptionFormDetailDTOResponse.setExpectedPickupDate(adoptionForm.getExpectedPickupDate());
        adoptionFormDetailDTOResponse.setExpectedPickupTime(adoptionForm.getExpectedPickupTime());
        adoptionFormDetailDTOResponse.setRequestDate(adoptionForm.getRequestDate());
        adoptionFormDetailDTOResponse.setStatus(adoptionForm.getStatus());
        adoptionFormDetailDTOResponse.setComment(adoptionForm.getComment());
        return adoptionFormDetailDTOResponse;
    }

    private BunnyViewDTOResponse mapToViewDTO(Bunny bunny) {
        BunnyViewDTOResponse bunnyViewDTOResponse = new BunnyViewDTOResponse();
        if(bunny!=null){
            bunnyViewDTOResponse.setId(bunny.getId());
        }else{
            bunnyViewDTOResponse.setId(0);
        }
        bunnyViewDTOResponse.setName(bunny.getName());
        bunnyViewDTOResponse.setBreed(bunny.getBreed());
        bunnyViewDTOResponse.setGender(bunny.getGender());
        bunnyViewDTOResponse.setAge(bunny.getAge());
        bunnyViewDTOResponse.setImage(bunny.getImage());
        bunnyViewDTOResponse.setDescription(bunny.getDescription());
        bunnyViewDTOResponse.setAvailabilityStatus(bunny.getAvailabilityStatus());
        bunnyViewDTOResponse.setHealthStatus(bunny.getHealthStatus());
        bunnyViewDTOResponse.setDateAdded(bunny.getDateAdded());
        return bunnyViewDTOResponse;
    }

    private AdoptionForm mapToEntity(AdoptionFormDTORequest adoptionFormDTORequest) {
        AdoptionForm adoptionForm = new AdoptionForm();
        adoptionForm.setId(0); //Set Id as 0 to trigger auto increment
        adoptionForm.setName(adoptionFormDTORequest.getName());
        adoptionForm.setPhone(adoptionFormDTORequest.getPhone());
        adoptionForm.setExpectedPickupDate(adoptionFormDTORequest.getExpectedPickupDate());
        adoptionForm.setExpectedPickupTime(adoptionFormDTORequest.getExpectedPickupTime());
        adoptionForm.setRequestDate(adoptionFormDTORequest.getRequestDate());
        adoptionForm.setStatus(adoptionFormDTORequest.getStatus());
        adoptionForm.setComment(adoptionFormDTORequest.getComment());
        return adoptionForm;
    }

    private void updateEntityFromDTO(AdoptionForm adoptionForm, AdoptionFormDTORequest adoptionFormDTORequest) {
        adoptionForm.setName(adoptionFormDTORequest.getName());
        adoptionForm.setPhone(adoptionFormDTORequest.getPhone());
        adoptionForm.setExpectedPickupDate(adoptionFormDTORequest.getExpectedPickupDate());
        adoptionForm.setExpectedPickupTime(adoptionFormDTORequest.getExpectedPickupTime());
        adoptionForm.setRequestDate(adoptionFormDTORequest.getRequestDate());
        adoptionForm.setStatus(adoptionFormDTORequest.getStatus());
        adoptionForm.setComment(adoptionFormDTORequest.getComment());
    }
}
