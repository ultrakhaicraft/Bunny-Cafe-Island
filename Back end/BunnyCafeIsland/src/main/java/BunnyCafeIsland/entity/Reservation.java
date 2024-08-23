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


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public ReservationStatus getStatus() {
        return status;
    }

    public void setStatus(ReservationStatus status) {
        this.status = status;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getExpectedDate() {
        return expectedDate;
    }

    public void setExpectedDate(Date expectedDate) {
        this.expectedDate = expectedDate;
    }

    public Time getExpectedTime() {
        return expectedTime;
    }

    public void setExpectedTime(Time expectedTime) {
        this.expectedTime = expectedTime;
    }

    public int getTableNumber() {
        return tableNumber;
    }

    public void setTableNumber(int tableNumber) {
        this.tableNumber = tableNumber;
    }
}
