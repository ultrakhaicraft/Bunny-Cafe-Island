package BunnyCafeIsland.API;

import BunnyCafeIsland.DTO.Request.AuthenticationRequest;
import BunnyCafeIsland.DTO.Response.AuthenticationResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import BunnyCafeIsland.Service.AuthenticationService;

import org.springframework.beans.factory.annotation.Autowired;


@RestController
@RequestMapping("/api/auth")
public class AuthenticationAPI {

    private final AuthenticationService authenticationService;

    @Autowired
    public AuthenticationAPI(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    //Login
    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest request
            ){
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }






    
        
        
    
}
