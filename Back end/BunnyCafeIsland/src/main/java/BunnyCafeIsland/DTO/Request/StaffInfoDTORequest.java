package BunnyCafeIsland.DTO.Request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
//User Info - Password not allow to edit
public class StaffInfoDTORequest {
    private String email;
    private String name;
    private String phoneNumber;
    private String profilePicture;
    private String role;
    private String staffStatus;
}
