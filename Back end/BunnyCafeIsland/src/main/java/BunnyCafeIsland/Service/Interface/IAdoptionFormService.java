package BunnyCafeIsland.Service.Interface;

import BunnyCafeIsland.Entity.AdoptionForm;

import java.util.List;

public interface IAdoptionFormService {
    List<AdoptionForm> getAllAdoptionForms();
    AdoptionForm getAdoptionRequestById(int id);
    AdoptionForm save(AdoptionForm anAdoptionForm);
    void deleteAdoptionRequestById(int id);
}
