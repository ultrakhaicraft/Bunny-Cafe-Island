package BunnyCafeIsland.Service;

import BunnyCafeIsland.DTO.MenuItemDTO;
import BunnyCafeIsland.Entity.MenuItem;

import java.util.List;

public interface IMenuItemService {
    public MenuItem getById(int id);
    public MenuItem save(MenuItem menuItem);
    public List<MenuItem> getAll();
    public void delete(int id);
    public MenuItemDTO convertToDTO(MenuItem menuItem);
    public MenuItem convertToEntity(MenuItemDTO menuItemDTO);
}
