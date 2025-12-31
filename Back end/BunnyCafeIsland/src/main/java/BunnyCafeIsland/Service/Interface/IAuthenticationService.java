package BunnyCafeIsland.Service.Interface;

import BunnyCafeIsland.DTO.Request.AuthenticationRequest;
import BunnyCafeIsland.DTO.Response.AuthenticationResponse;

public interface IAuthenticationService {
    AuthenticationResponse authenticate(AuthenticationRequest request);
}
