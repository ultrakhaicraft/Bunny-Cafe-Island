package BunnyCafeIsland.Entity;

import java.sql.Date;
import java.sql.Time;

import BunnyCafeIsland.Enums.ReservationStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Entity
@Table(name="reservation")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="name", length = 50,  nullable = false)
    private String name;

    @Column(name="phone", length = 50,  nullable = false, unique = true)
    private String phone;

    @Enumerated(EnumType.STRING)
    @Column(name="status",  nullable = false)
    private ReservationStatus status;

    @Column(name="comment",columnDefinition = "TEXT")
    private String comment;

    @Column(name="expectedDate",  nullable = false)
    @Temporal(TemporalType.DATE)
    private Date expectedDate;

    @Column(name="expectedTime",  nullable = false)
    @Temporal(TemporalType.TIME)
    private Time expectedTime;

    @Column(name="tableNumber",  nullable = false)
    private int tableNumber;

    public Reservation() {
    }

    public Reservation(int id, String name, String phone, ReservationStatus status, String comment, Date expectedDate, Time expectedTime, int tableNumber) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.status = status;
        this.comment = comment;
        this.expectedDate = expectedDate;
        this.expectedTime = expectedTime;
        this.tableNumber = tableNumber;
    }


}
