package BunnyCafeIsland.DTO.Request;

import BunnyCafeIsland.Enums.ReservationStatus;
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
public class ReservationDTORequest {
    private int id;
    private String name;
    private String phoneNumber;
    private ReservationStatus status;
    private String comment;
    private Date expectedDate;
    private Time expectedTime;
    private int tableNumber;
}
