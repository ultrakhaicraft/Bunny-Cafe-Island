package BunnyCafeIsland.Service.Interface;

import BunnyCafeIsland.DTO.Request.MenuItemDTORequest;
import BunnyCafeIsland.DTO.Response.MenuItemResponse;
import BunnyCafeIsland.Entity.MenuItem;

import java.util.List;

public interface IMenuItemService {
    MenuItem getById(int id);
    MenuItem save(MenuItem menuItem);
    List<MenuItem> getAll();
    void delete(int id);
    MenuItemResponse convertToDTO(MenuItem menuItem);
    MenuItem convertToEntity(MenuItemDTORequest menuItemDTORequest);
}
