package BunnyCafeIsland.Entity;

import BunnyCafeIsland.Enums.AdoptionFormStatus;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="adoptionrequest")  
public class AdoptionForm {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;
    
    
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "adoptionFormId") // This is the foreign key in the Bunny table
    private List<Bunny> bunnies = new ArrayList<>();

    @Column(name="name",length = 50, nullable=false)
    private String name;

    @Column(name="phone",length = 50, nullable=false)
    private String phone;

    @Column(name="requestDate", nullable=false)
    @Temporal(TemporalType.DATE)
    private Date requestDate;

    @Column(name="comment", columnDefinition= "TEXT")
    private String comment;

    @Column(name="expectedPickupDate", nullable=false)
    @Temporal(TemporalType.DATE)
    private Date expectedPickupDate;

    @Column(name="expectedPickupTime", nullable=false)
    @Temporal(TemporalType.TIME)
    private Time expectedPickupTime;

    @Enumerated(EnumType.STRING)
    @Column(name="staus")
    private AdoptionFormStatus status;
}
