package BunnyCafeIsland.Service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import BunnyCafeIsland.DTO.Request.MenuItemRequest;
import BunnyCafeIsland.DTO.Response.MenuItemResponse;
import BunnyCafeIsland.Service.Interface.IMenuItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import BunnyCafeIsland.Entity.MenuItem;
import BunnyCafeIsland.Exception.BadRequestException;
import BunnyCafeIsland.Repository.MenuItemRepository;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import static BunnyCafeIsland.Constant.Constant.IMAGE_DIRECTORY;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

@Service
public class MenuItemService implements IMenuItemService {

    private MenuItemRepository menuItemRepository;

    @Autowired
    public MenuItemService(MenuItemRepository menuItemRepository) {
        this.menuItemRepository = menuItemRepository;
    }

    @Override
    public MenuItem getById(int id) {
        Optional<MenuItem> result =menuItemRepository.findById(id);
        MenuItem menuItem =null;
        if(result.isPresent()){
            menuItem=result.get();
        }else{
            throw new BadRequestException("Menu Item not found - ID: "+id);
        }
        return menuItem;
    }

    @Override
    public MenuItem save(MenuItem MenuItem) {
        MenuItem.setDate_added(LocalDateTime.now());
        return menuItemRepository.save(MenuItem);
    }

    @Override
    public List<MenuItem> getAll() {
            return menuItemRepository.findAll();
    }

    public Page<MenuItem> getAllPageable(Pageable pageable){
        return menuItemRepository.findAll(pageable);
    }

    @Override
    public void delete(int id) {
        menuItemRepository.deleteById(id);
    }

    @Override
    public MenuItemResponse convertToDTO(MenuItem menuItem) {
        MenuItemResponse menuItemResponse = new MenuItemResponse();
        if(menuItem!=null){
            menuItemResponse.setId(menuItem.getId());
        }else{
            menuItemResponse.setId(0);
        }
        menuItemResponse.setName(menuItem.getName());
        menuItemResponse.setPrice(menuItem.getPrice());
        menuItemResponse.setDescription(menuItem.getDescription());
        menuItemResponse.setStatus(menuItem.getStatus());
        menuItemResponse.setType(menuItem.getType());
        menuItemResponse.setImage_path(menuItem.getImage_path());
        menuItemResponse.setDate_added(menuItem.getDate_added());
        return menuItemResponse;
    }

    @Override
    public MenuItem convertToEntity(MenuItemRequest menuItemRequest) {
        MenuItem menuItem = new MenuItem();
        if(menuItemRequest !=null){
            menuItem.setId(menuItemRequest.getId());
        }else{
            menuItem.setId(0);
        }
        menuItem.setId(menuItemRequest.getId());
        menuItem.setName(menuItemRequest.getName());
        menuItem.setPrice(menuItemRequest.getPrice());
        menuItem.setDescription(menuItemRequest.getDescription());
        menuItem.setStatus(menuItemRequest.getStatus());
        menuItem.setType(menuItemRequest.getType());
        menuItem.setImage_path(menuItemRequest.getImage_path());
        menuItem.setDate_added(menuItemRequest.getDate_added());
        return menuItem;
    }


    public String uploadImage(int menuItemID, MultipartFile file){
        System.out.println("Saving picture for menu");
        MenuItem menuItem = getById(menuItemID);
        String image_URL = imageFunction(menuItemID,file);
        menuItem.setImage_path(image_URL);
        menuItemRepository.save(menuItem);
        return image_URL;
    }

    //Very complicated, taken from "Full Stack ReactJS with Spring Boot" video from Get Arrays
    private final String imageFunction (int id, MultipartFile image){
        String filename= id + getImageExtension(image.getOriginalFilename());
        try{
            Path imageStorageLocation = Paths.get(IMAGE_DIRECTORY).toAbsolutePath().normalize();
            if(!Files.exists(imageStorageLocation)) {Files.createDirectories(imageStorageLocation);}
            Files.copy(image.getInputStream(),imageStorageLocation
                    .resolve(id+getImageExtension(image.getOriginalFilename())),REPLACE_EXISTING);
            return ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path("/api/management/menuItems/images/"+filename)
                    .toUriString();
        }catch (Exception exception){
            throw new RuntimeException("Unable to save image");
        }
    }

    private final String getImageExtension (String filename){
        return Optional.of(filename).filter(name -> name.contains("."))
                .map(name->"."+name.substring(filename.lastIndexOf(".")+1))
                .orElse(".jpg");
    }
}
