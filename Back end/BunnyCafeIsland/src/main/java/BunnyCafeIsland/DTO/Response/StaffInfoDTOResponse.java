package BunnyCafeIsland.DTO.Response;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
//User Info - Password not allow to show
public class StaffInfoDTOResponse {
    private int id;
    private String email;
    private String name;
    private String phoneNumber;
    private String profilePicture;
    private String role;
    private String staffStatus;

    public StaffInfoDTOResponse(String email, String name, String phone, String profilePicture, String string) {
    }
}
