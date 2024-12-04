package BunnyCafeIsland.API;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import BunnyCafeIsland.Entity.MenuItem;
import BunnyCafeIsland.Exception.BadRequestException;
import BunnyCafeIsland.Service.MenuItemService;

public class AdoptionFormAPI {
    /* 
    private MenuItemService menuItemService;

    @Autowired
    public AdoptionFormAPI(MenuItemService menuItemService){
        this.menuItemService=menuItemService;
    }
    
    @GetMapping("/menuItems")
    public List<MenuItem> getAllBunny() {
        List<MenuItem> bunnyList = menuItemService.getAllMenuItems();
        return bunnyList;
    }

    @GetMapping("/menuItems/{menuItemId}")
    public MenuItem getABunny(@PathVariable int menuItemId) {
        MenuItem aMenuItem = menuItemService.getMenuItemById(menuItemId);
        if(aMenuItem==null){
            throw new BadRequestException("Menu Item not found - ID: "+menuItemId);
        }
        return aMenuItem;
    }

    @PostMapping("/menuItems")
    public MenuItem addBunny(@RequestBody MenuItem aMenuItem) {
        aMenuItem.setId(0);
        MenuItem dbMenuItem=menuItemService.save(aMenuItem);
        
        return dbMenuItem;
    }
    
    @PutMapping("/menuItems")
    public MenuItem updateBunny(@RequestBody MenuItem aMenuItem) {
        MenuItem dbMenuItem=menuItemService.save(aMenuItem);
        return dbMenuItem;
    }

    /*@PatchMapping("/menuItems/{menuItemId}")
    public MenuItem changeMenuItemStatus(@PathVariable int bunnyId, @RequestParam AvailabilityStatus status) {
        MenuItem aMenuItem = menuItemService.getBunnyById(bunnyId);
        if(aMenuItem==null){
            throw new BadRequestException("Bunny not found  ID: "+bunnyId);
        }
        MenuItem dbMenuItem=menuItemService.changeStatus(bunnyId, status);
        return dbMenuItem;
    }*/
    
    /* 
    @DeleteMapping("/menuItems/{menuItemId}")
    public String deleteBunny(@PathVariable int menuItemId) {
        MenuItem aBunny = menuItemService.getMenuItemById(menuItemId);
        if(aBunny==null){
            throw new BadRequestException("Menu Item not found  ID: "+menuItemId);
        }
        menuItemService.deleteMenuItemById(menuItemId);
        return "Delete Menu Item ID: "+menuItemId;
    }

    */
}
