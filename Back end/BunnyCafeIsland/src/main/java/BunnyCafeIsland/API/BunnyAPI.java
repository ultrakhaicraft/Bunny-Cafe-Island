package BunnyCafeIsland.API;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.*;

import BunnyCafeIsland.Entity.Bunny;
import BunnyCafeIsland.Exception.BadRequestException;
import BunnyCafeIsland.Exception.ErrorResponse;
import BunnyCafeIsland.Service.BunnyService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/api")
public class BunnyAPI {

    
    private BunnyService bunnyService;

    @Autowired
    public BunnyAPI(BunnyService bunnyService){
        this.bunnyService=bunnyService;
    }
    

    @GetMapping("/hello")
    public String sayHello() {
        return "Hello";
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
    
}
