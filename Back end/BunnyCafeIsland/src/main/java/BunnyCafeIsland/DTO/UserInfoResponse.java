package BunnyCafeIsland.DTO;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserInfoResponse {
    private String email;
    private String name;
    private String phoneNumber;
    private String profilePicture;
    private String role;
}
