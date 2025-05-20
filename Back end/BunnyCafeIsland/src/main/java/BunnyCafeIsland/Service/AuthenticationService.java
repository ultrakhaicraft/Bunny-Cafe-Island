package BunnyCafeIsland.Service;

import BunnyCafeIsland.DTO.Request.AuthenticationRequest;
import BunnyCafeIsland.DTO.Response.AuthenticationResponse;
import BunnyCafeIsland.DTO.Response.UserInfoResponse;
import BunnyCafeIsland.Service.Interface.IAuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import BunnyCafeIsland.Repository.StaffRepository;


@Service
public class AuthenticationService implements IAuthenticationService {

    private final StaffRepository staffRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

	@Autowired
	public AuthenticationService(StaffRepository staffRepository, BCryptPasswordEncoder bCryptPasswordEncoder, JwtService jwtService, AuthenticationManager authenticationManager){
		this.staffRepository=staffRepository;
        this.bCryptPasswordEncoder=bCryptPasswordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(),request.getPassword())
        );
        var user = staffRepository.findByEmail(request.getEmail()).orElseThrow();

        var JwtToken = jwtService.createTokenForUser(user);

        UserInfoResponse userInfoResponse = new UserInfoResponse(
                user.getEmail(),
                user.getName(),
                user.getPhone(),
                user.getProfilePicture(),
                user.getRole().toString()
        );

        return AuthenticationResponse.builder()
                .token(JwtToken)
                .userInfo(userInfoResponse)
                .build();
    }

//Saving old way

    /*public boolean checkPassword(String plainPassword, String encodedPassword){
        return bCryptPasswordEncoder.matches(plainPassword, encodedPassword);
    }*/

    /*
    public boolean authenticatedUser(String email, String password){
        Staff aStaff = staffRepository.findByEmail(email);

        //TODO: Email found but something wrong with the password

        if(aStaff!=null){
            String storedPassword = aStaff.getPassword();

            //Remove the {bcrypt}
            if (storedPassword.startsWith("{bcrypt}")) {
                storedPassword = storedPassword.substring("{bcrypt}".length());
            }

            return checkPassword(password, storedPassword);
        }

        return false;
    }
    */
} 
