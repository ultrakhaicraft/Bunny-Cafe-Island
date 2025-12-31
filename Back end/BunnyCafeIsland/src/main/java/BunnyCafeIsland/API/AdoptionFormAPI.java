package BunnyCafeIsland.API;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import BunnyCafeIsland.Exception.BadRequestException;
import BunnyCafeIsland.Service.AdoptionFormService;
import BunnyCafeIsland.Entity.AdoptionForm;


public class AdoptionFormAPI {

    private AdoptionFormService adoptionFormService;

    @Autowired
    public AdoptionFormAPI(AdoptionFormService adoptionFormService){
        this.adoptionFormService=adoptionFormService;
    }
    
    @GetMapping("/adoptionForms")
    public List<AdoptionForm> getAllBunny() {
        List<AdoptionForm> adoptionFormList = adoptionFormService.getAllAdoptionForms();
        return adoptionFormList;
    }

    @GetMapping("/adoptionForms/{adoptionFormId}")
    public AdoptionForm getABunny(@PathVariable int adoptionFormId) {
        AdoptionForm adoptionForm = adoptionFormService.getAdoptionRequestById(adoptionFormId);
        if(adoptionForm==null){
            throw new BadRequestException("Adoption Form not found - ID: "+adoptionFormId);
        }
        return adoptionForm;
    }

    @PostMapping("/adoptionForms")
    public AdoptionForm addBunny(@RequestBody AdoptionForm adoptionForm) {
        adoptionForm.setId(0);
        AdoptionForm adoptionForm1=adoptionFormService.save(adoptionForm);
        
        return adoptionForm1;
    }
    
    @PutMapping("/adoptionForms")
    public AdoptionForm updateBunny(@RequestBody AdoptionForm adoptionForm) {
        AdoptionForm adoptionForm1=adoptionFormService.save(adoptionForm);
        return adoptionForm1;
    }

    @DeleteMapping("/adoptionForms/{adoptionFormId}")
    public String deleteBunny(@PathVariable int adoptionFormId) {
        AdoptionForm adoptionForm = adoptionFormService.getAdoptionRequestById(adoptionFormId);
        if(adoptionForm==null){
            throw new BadRequestException("Menu Item not found  ID: "+adoptionFormId);
        }
        adoptionFormService.deleteAdoptionRequestById(adoptionFormId);
        return "Delete Adoption Form ID: "+adoptionFormId;
    }

    /*
    @PatchMapping("/menuItems/{menuItemId}/status")
    public AdoptionForm changeMenuItemStatus(@PathVariable int bunnyId, @RequestParam AvailabilityStatus status) {
        AdoptionForm aMenuItem = adoptionFormService.getAdoptionRequestById(bunnyId);
        if(aMenuItem==null){
            throw new BadRequestException("Bunny not found  ID: "+bunnyId);
        }
        AdoptionForm dbMenuItem=adoptionFormService.changeStatus(bunnyId, status);
        return dbMenuItem;
    }
    */

}
