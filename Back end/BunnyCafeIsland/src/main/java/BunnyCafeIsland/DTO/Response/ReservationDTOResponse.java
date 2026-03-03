package BunnyCafeIsland.DTO.Response;

import BunnyCafeIsland.Enums.ReservationStatus;
import jakarta.persistence.*;
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
public class ReservationDTOResponse {
    private int id;
    private String name;
    private String phoneNumber;
    private ReservationStatus status;
    private String comment;
    private Date expectedDate;
    private Time expectedTime;
    private int tableNumber;
}
