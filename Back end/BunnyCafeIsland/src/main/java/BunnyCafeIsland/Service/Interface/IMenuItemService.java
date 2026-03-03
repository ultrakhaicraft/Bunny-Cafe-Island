package BunnyCafeIsland.Service.Interface;

import BunnyCafeIsland.DTO.Request.MenuItemDTORequest;
import BunnyCafeIsland.DTO.Response.MenuItemDTOResponse;
import BunnyCafeIsland.Entity.MenuItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IMenuItemService {
    MenuItemDTOResponse getById(int id);
    MenuItemDTOResponse add(MenuItemDTORequest dtoRequest);
    MenuItemDTOResponse update(int menuItemId, MenuItemDTORequest dtoRequest);
    Page<MenuItemDTOResponse> getAllPageable(Pageable pageable);
    void delete(int id);
    String uploadImage(int menuItemID, MultipartFile file);
}
