package BunnyCafeIsland.API;

import BunnyCafeIsland.DTO.AuthenticationRequest;
import BunnyCafeIsland.DTO.AuthenticationResponse;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import BunnyCafeIsland.DTO.LoginRequest;
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

    /*
    @PostMapping("/login")
    public ResponseEntity<?> postMethodName(@RequestBody LoginRequest loginRequest) {
            boolean isAuthenticated = authenticationService.authenticatedUser(loginRequest.getEmail(), loginRequest.getPassword());

        if (isAuthenticated) {
            return ResponseEntity.ok("{\"message\": \"Login successful\"}");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("{\"message\": \"Bad credentials\"}");
        }
    }
    */
    
        
        
    
}
