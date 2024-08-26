package BunnyCafeIsland.API;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.*;

import BunnyCafeIsland.Entity.Bunny;

import BunnyCafeIsland.Enums.AvailabilityStatus;
import BunnyCafeIsland.Exception.BadRequestException;

import BunnyCafeIsland.Service.BunnyService;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.DeleteMapping;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/api")
public class BunnyAPI {

    
    private BunnyService bunnyService;

    @Autowired
    public BunnyAPI(BunnyService bunnyService){
        this.bunnyService=bunnyService;
    }
    
    @GetMapping("/bunnies")
    public List<Bunny> getAllBunny() {
        List<Bunny> bunnyList = bunnyService.getAllBunny();
        return bunnyList;
    }

    @GetMapping("/bunnies/{bunnyId}")
    public Bunny getABunny(@PathVariable int bunnyId) {
        Bunny bunny = bunnyService.getBunnyById(bunnyId);
        if(bunny==null){
            throw new BadRequestException("Bunny not found - ID: "+bunnyId);
        }
        return bunny;
    }

    @PostMapping("/bunnies")
    public Bunny addBunny(@RequestBody Bunny aBunny) {
        aBunny.setId(0);
        Bunny dbReservation=bunnyService.save(aBunny);
        
        return dbReservation;
    }
    
    @PutMapping("/bunnies")
    public Bunny updateBunny(@RequestBody Bunny aBunny) {
        Bunny dbReservation=bunnyService.save(aBunny);
        return dbReservation;
    }

    @PatchMapping("/bunnies/{bunnyId}")
    public Bunny changeBunnyStatus(@PathVariable int bunnyId, @RequestParam AvailabilityStatus status) {
        Bunny aBunny = bunnyService.getBunnyById(bunnyId);
        if(aBunny==null){
            throw new BadRequestException("Bunny not found  ID: "+bunnyId);
        }
        Bunny dBunny=bunnyService.changeStatus(bunnyId, status);
        return dBunny;
    }
    
    @DeleteMapping("/bunnies/{bunnyId}")
    public String deleteBunny(@PathVariable int bunnyId) {
        Bunny aBunny = bunnyService.getBunnyById(bunnyId);
        if(aBunny==null){
            throw new BadRequestException("Bunny not found  ID: "+bunnyId);
        }
        bunnyService.deleteBunnyById(bunnyId);
        return "Delete Bunny ID: "+bunnyId;
    }

    
    
}
