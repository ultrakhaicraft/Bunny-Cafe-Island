package BunnyCafeIsland.Entity;


import jakarta.persistence.*;

import java.util.Date;

import BunnyCafeIsland.Enums.AvailabilityStatus;
import BunnyCafeIsland.Enums.Gender;

@Entity
@Table(name="bunny")
public class Bunny {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name", length = 50, nullable = false)
    private String name;

    @Column(name = "breed", length = 50, nullable = false)
    private String breed;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender", nullable = false)
    private Gender gender;

    @Column(name = "age", nullable = false)
    private int age;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "image", length = 255)
    private String image;

    @Enumerated(EnumType.STRING)
    @Column(name = "availabilityStatus", nullable = false)
    private AvailabilityStatus availabilityStatus;

    @Column(name = "healthStatus", length = 100)
    private String healthStatus;

    @Column(name = "dateAdded")
    @Temporal(TemporalType.DATE)
    private Date dateAdded;

    public Bunny(){}

    public Bunny(String name,String breed, Gender gender, int age, String description, String image, AvailabilityStatus availabilityStatus, String healthStatus, Date dateAdded) {
        this.name = name;
        this.breed = breed;
        this.gender = gender;
        this.age = age;
        this.description = description;
        this.image = image;
        this.availabilityStatus = availabilityStatus;
        this.healthStatus = healthStatus;
        this.dateAdded = dateAdded;
    }

    @Override
    public String toString() {
        return "Bunny{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", breed='" + breed + '\'' +
                ", gender=" + gender +
                ", age=" + age +
                ", description='" + description + '\'' +
                ", image='" + image + '\'' +
                ", availabilityStatus=" + availabilityStatus +
                ", healthStatus='" + healthStatus + '\'' +
                ", dateAdded=" + dateAdded +
                '}';
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

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public AvailabilityStatus getAvailabilityStatus() {
        return availabilityStatus;
    }

    public void setAvailabilityStatus(AvailabilityStatus availabilityStatus) {
        this.availabilityStatus = availabilityStatus;
    }

    public String getHealthStatus() {
        return healthStatus;
    }

    public void setHealthStatus(String healthStatus) {
        this.healthStatus = healthStatus;
    }

    public Date getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(Date dateAdded) {
        this.dateAdded = dateAdded;
    }
}
