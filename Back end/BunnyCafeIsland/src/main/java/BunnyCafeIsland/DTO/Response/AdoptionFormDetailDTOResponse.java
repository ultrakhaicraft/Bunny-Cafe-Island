package BunnyCafeIsland.DTO.Response;

import BunnyCafeIsland.Entity.Bunny;
import BunnyCafeIsland.Enums.AdoptionFormStatus;
import jakarta.persistence.*;
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
public class AdoptionFormDetailDTOResponse {

    private int id;
    private List<BunnyViewDTOResponse> bunnies = new ArrayList<>();
    private String name;
    private String phone;
    private Date requestDate;
    private String comment;
    private Date expectedPickupDate;
    private Time expectedPickupTime;
    private AdoptionFormStatus status;
}
