package BunnyCafeIsland.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import BunnyCafeIsland.Entity.AdoptionForm;
import BunnyCafeIsland.Entity.MenuItem;
import BunnyCafeIsland.Exception.BadRequestException;
import BunnyCafeIsland.Repository.AdoptionFormRepository;
import BunnyCafeIsland.Repository.MenuItemRepository;

@Service
public class AdoptionFormService {

    private AdoptionFormRepository adoptionFormRepository;

    @Autowired
    public AdoptionFormService(AdoptionFormRepository adoptionFormRepository) {
        this.adoptionFormRepository = adoptionFormRepository;
    }

    public List<AdoptionForm> getAllAdoptionForms(){
        return adoptionFormRepository.findAll();
    }

    public AdoptionForm getAdoptionRequestById(int id){
        Optional<AdoptionForm> result =adoptionFormRepository.findById(id);
        AdoptionForm anAdoptionForm =null;
        if(result.isPresent()){
            anAdoptionForm=result.get();
        }else{
            throw new BadRequestException("Adoption Form not found - ID: "+id);
        }
        return anAdoptionForm;
    }

    public AdoptionForm save(AdoptionForm anAdoptionForm){
        return adoptionFormRepository.save(anAdoptionForm);
    }

    public void deleteAdoptionRequestById(int id){
        adoptionFormRepository.deleteById(id);
    }
}
