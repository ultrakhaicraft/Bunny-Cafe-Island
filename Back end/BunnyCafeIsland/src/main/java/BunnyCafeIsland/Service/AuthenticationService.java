/*package BunnyCafeIsland.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import BunnyCafeIsland.Entity.Staff;
import BunnyCafeIsland.Repository.StaffRepository;


@Service
public class AuthenticationService {

    private StaffRepository staffRepository;
    private PasswordEncoder encoder;

	/*@Autowired
	public AuthenticationService(StaffRepository staffRepository, PasswordEncoder encoder){
		this.staffRepository=staffRepository;
        this.encoder=encoder;
	}

    
    public AuthenticationService(StaffRepository staffRepository){
		this.staffRepository=staffRepository;
        
	}

    public boolean authenticatedUser(String email, String password){
        Staff aStaff = staffRepository.findByEmail(email);

        if(aStaff!=null){
            String staffPassword = aStaff.getPassword();
            return checkPassword(password, staffPassword);
        }

        return false;
    }

    public boolean checkPassword(String plainPassword, String encodedPassword){
        return encoder.matches(plainPassword, encodedPassword);
    }
} */
