/*package BunnyCafeIsland.API;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import BunnyCafeIsland.DTO.LoginRequest;
import BunnyCafeIsland.Service.AuthenticationService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/api")
public class AuthenticationAPI {

    private AuthenticationService authenticationService;

    @Autowired
    public AuthenticationAPI(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }



    @PostMapping("/login")
    public String postMethodName(@RequestBody LoginRequest loginRequest) {
            boolean isAuthenticated = authenticationService.authenticatedUser(loginRequest.getEmail(), loginRequest.getPassword());
            
            if(isAuthenticated){
                return "Login Success";
            }else{
                return "Bad credential";
            }
    }

    
        
        
    
}
*/