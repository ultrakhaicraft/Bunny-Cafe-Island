package BunnyCafeIsland.Service;


import BunnyCafeIsland.Entity.Staff;
import BunnyCafeIsland.Repository.StaffRepository;
import BunnyCafeIsland.Service.Interface.IJwtUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class JwtUserDetailsService implements IJwtUserDetailsService, UserDetailsService {

    private final StaffRepository staffRepository;

    @Autowired
    public JwtUserDetailsService(StaffRepository staffRepository) {
        this.staffRepository = staffRepository;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return null;
    }
}
