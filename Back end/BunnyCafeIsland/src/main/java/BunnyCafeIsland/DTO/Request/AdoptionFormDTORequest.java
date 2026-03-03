package BunnyCafeIsland.DTO.Request;

import BunnyCafeIsland.DTO.Response.BunnyViewDTOResponse;
import BunnyCafeIsland.Enums.AdoptionFormStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AdoptionFormDTORequest {
    private int id;
    private String name;
    private String phone;
    private Date requestDate;
    private String comment;
    private Date expectedPickupDate;
    private Time expectedPickupTime;
    private AdoptionFormStatus status;
}
