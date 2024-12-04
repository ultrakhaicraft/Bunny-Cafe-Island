package BunnyCafeIsland.Entity;

import BunnyCafeIsland.Enums.MenuStatus;
import BunnyCafeIsland.Enums.MenuType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor
@Table(name="menuitem")
public class MenuItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="name", length = 50, nullable = false)
    private String name;

    @Column(name="price", nullable = false)
    private int price;

    @Column(name="description",columnDefinition = "TEXT", nullable = false)
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name="status", nullable = false)
    private MenuStatus status;

    @Enumerated(EnumType.STRING)
    @Column(name="type", nullable = false)
    private MenuType type;

    @Column(name="image_path")
    private String image_path;

    @Column(name="date_added", nullable = false, updatable = false)
    private LocalDateTime date_added;

    public MenuItem() {
    }


}
