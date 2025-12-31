package BunnyCafeIsland.DTO.Response;

import BunnyCafeIsland.Enums.MenuStatus;
import BunnyCafeIsland.Enums.MenuType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MenuItemResponse {
    private int id;
    private String name;
    private int price;
    private String description;
    private MenuStatus status;
    private MenuType type;
    private String image_path;
    private LocalDateTime date_added;
}
