package BunnyCafeIsland.Entity;





import BunnyCafeIsland.Enums.Role;
import BunnyCafeIsland.Enums.StaffStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="staff")
public class Staff {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="email", length = 100, nullable = false, unique = true)
    private String email;

    @Column(name="password", length = 100, nullable = false)
    private String password;

    @Column(name="name", length = 100, nullable = false)
    private String name;

    @Column(name="phone", length = 20, nullable = false, unique = true)
    private String phone;

    @Column(name="profilePicture", length = 255)
    private String profilePicture;

    @Enumerated(EnumType.STRING)
    @Column(name="role", nullable = false)
    private Role role;

    @Enumerated(EnumType.STRING)
    @Column(name="status", nullable = false)
    private StaffStatus status;

    public Staff() {
    }

    public Staff(int id, String email, String password, String name, String phone, String profilePicture, Role role, StaffStatus status) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.name = name;
        this.phone = phone;
        this.profilePicture = profilePicture;
        this.role = role;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public StaffStatus getStatus() {
        return status;
    }

    public void setStatus(StaffStatus status) {
        this.status = status;
    }


    @Override
    public String toString() {
        return "Staff{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", profilePicture='" + profilePicture + '\'' +
                ", role=" + role +
                ", status=" + status +
                '}';
    }
}
