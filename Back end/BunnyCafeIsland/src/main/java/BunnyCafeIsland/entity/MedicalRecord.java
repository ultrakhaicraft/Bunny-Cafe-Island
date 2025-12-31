package BunnyCafeIsland.Entity;

import java.util.Date;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.grammars.hql.HqlParser.DateTimeContext;

@Entity
@Data
@Table(name="medicalrecord")
public class MedicalRecord {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="date")
    @Temporal(TemporalType.DATE)
    private Date date;
    
    @Column(name="medicalHistory", columnDefinition="TEXT")
    private String medicalHistory;

    @Column(name="treatmentDetails", columnDefinition="TEXT")
    private String treatmentDetails;

    @Column(name="clinic", length=50)
    private String clinic;

    @ManyToOne(cascade = {CascadeType.MERGE,CascadeType.PERSIST,CascadeType.DETACH,CascadeType.REFRESH})
    @JoinColumn(name="bunnyID")
    private Bunny bunny;
    

    public MedicalRecord() {
    }

    public MedicalRecord(Date date, String medicalHistory, String treatmentDetails, String clinic) {
        this.date = date;
        this.medicalHistory = medicalHistory;
        this.treatmentDetails = treatmentDetails;
        this.clinic = clinic;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getMedicalHistory() {
        return medicalHistory;
    }

    public void setMedicalHistory(String medicalHistory) {
        this.medicalHistory = medicalHistory;
    }

    public String getTreatmentDetails() {
        return treatmentDetails;
    }

    public void setTreatmentDetails(String treatmentDetails) {
        this.treatmentDetails = treatmentDetails;
    }

    public String getClinic() {
        return clinic;
    }

    public void setClinic(String clinic) {
        this.clinic = clinic;
    }

    public Bunny getBunny() {
        return bunny;
    }

    public void setBunny(Bunny bunny) {
        this.bunny = bunny;
    }
}
