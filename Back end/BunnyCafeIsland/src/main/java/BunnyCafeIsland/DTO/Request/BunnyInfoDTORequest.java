package BunnyCafeIsland.DTO.Request;

import BunnyCafeIsland.Enums.AvailabilityStatus;
import BunnyCafeIsland.Enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BunnyInfoDTORequest {
    private String name;
    private String breed;
    private Gender gender;
    private int age;
    private String description;
    private String image;
    private AvailabilityStatus availabilityStatus;
    private String healthStatus;
    private Date dateAdded;
}
