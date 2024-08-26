package BunnyCafeIsland.API;

import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
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

    private StaffService staffService;

    @Autowired
    public StaffAPI(StaffService staffService){
        this.staffService=staffService;
    }
    
    
    @GetMapping("/staffs")
    public List<Staff> getAllStaff() {
        List<Staff> bunnyList = staffService.getAllStaffs();
        return bunnyList;
    }

    @GetMapping("/staffs/{staffId}")
    public Staff getAStaff(@PathVariable int staffId) {
        Staff aStaff = staffService.getStaffById(staffId);
        
        if(aStaff==null){
            throw new BadRequestException("Staff not found - ID: "+staffId);
        }

        return aStaff;
    }

    @GetMapping("/login")
    public String getMethodName(@RequestParam String email, @RequestParam String password) {
        return new String();
    }
    

    @PostMapping("/staffs")
    public Staff addStaff(@RequestBody Staff aStaff) {
        //If they pass an ID in json, set id to 0.
        //This is to force a insert of new item rather than update.
        aStaff.setId(0);
        Staff dbStaff = staffService.save(aStaff);
        return dbStaff;
    }
    
    @PutMapping("/staffs")
    public Staff updateStaff(@RequestBody Staff aStaff) {
        Staff dbStaff = staffService.save(aStaff);
        return dbStaff;
    }

    @DeleteMapping("/staffs/{staffId}")
    public String deleteStaff(@PathVariable int staffId){
        Staff aStaff = staffService.getStaffById(staffId);
        if(aStaff==null){
            throw new BadRequestException("Staff not found - ID: "+staffId);
        }

        staffService.hardRemove(staffId);
        return "Deleted Staff ID: "+ staffId;
    }
}
