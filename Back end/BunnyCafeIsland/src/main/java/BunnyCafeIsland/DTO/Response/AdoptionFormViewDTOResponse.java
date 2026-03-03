package BunnyCafeIsland.DTO.Response;

import BunnyCafeIsland.Enums.AdoptionFormStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.sql.Time;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AdoptionFormViewDTOResponse {
    private int id;
    private String name;
    private String phone;
    private Date requestDate;
    private Date expectedPickupDate;
    private Time expectedPickupTime;
    private AdoptionFormStatus status;
}
