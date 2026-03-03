package BunnyCafeIsland.DTO.Response;

import BunnyCafeIsland.Entity.MedicalRecord;
import BunnyCafeIsland.Enums.AvailabilityStatus;
import BunnyCafeIsland.Enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BunnyDetailDTOResponse {
    private int id;
    private String name;
    private String breed;
    private Gender gender;
    private int age;
    private String description;
    private String image;
    private AvailabilityStatus availabilityStatus;
    private String healthStatus;
    private Date dateAdded;
    private List<MedicalRecordDTOResponse> medicalRecordsList; //Optional, can return empty
}
