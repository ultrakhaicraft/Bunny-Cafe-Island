package BunnyCafeIsland.Service;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import BunnyCafeIsland.DTO.Request.MenuItemDTORequest;
import BunnyCafeIsland.DTO.Response.MenuItemDTOResponse;
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

    private final MenuItemRepository menuItemRepository;


    @Autowired
    public MenuItemService(MenuItemRepository menuItemRepository) {
        this.menuItemRepository = menuItemRepository;
    }

    @Override
    public MenuItemDTOResponse getById(int id) {
        Optional<MenuItem> result =menuItemRepository.findById(id);
        MenuItem menuItem =null;
        if(result.isPresent()){
            menuItem=result.get();
        }else{
            throw new BadRequestException("Menu Item not found - ID: "+id);
        }

        return mapToDTO(menuItem);
    }

    @Override
    public MenuItemDTOResponse add(MenuItemDTORequest dtoRequest) {
        MenuItem menuItem = mapToEntity(dtoRequest);
        menuItem.setDate_added(LocalDateTime.now());
        MenuItem addedItem= menuItemRepository.save(menuItem);
        return mapToDTO(addedItem);
    }

    @Override
    public MenuItemDTOResponse update(int menuItemId, MenuItemDTORequest dtoRequest) {
        MenuItem existingMenuItem = menuItemRepository.findById(menuItemId)
                .orElseThrow(() -> new BadRequestException("Menu Item not found - ID: " + menuItemId));

        updateEntityFromDTO(existingMenuItem, dtoRequest);

        MenuItem updatedItem= menuItemRepository.save(existingMenuItem);
        return mapToDTO(updatedItem);
    }


    @Override
    public Page<MenuItemDTOResponse> getAllPageable(Pageable pageable){
        Page<MenuItem> menuItemPage= menuItemRepository.findAll(pageable);

        //Convert to DTO Response page
        return menuItemPage.map(this::mapToDTO);
    }

    @Override
    public void delete(int id) {
        menuItemRepository.deleteById(id);
    }


    private MenuItemDTOResponse mapToDTO(MenuItem menuItem) {
        MenuItemDTOResponse menuItemDTOResponse = new MenuItemDTOResponse();
        if(menuItem!=null){
            menuItemDTOResponse.setId(menuItem.getId());
        }else{
            menuItemDTOResponse.setId(0);
        }
        menuItemDTOResponse.setName(menuItem.getName());
        menuItemDTOResponse.setPrice(menuItem.getPrice());
        menuItemDTOResponse.setDescription(menuItem.getDescription());
        menuItemDTOResponse.setStatus(menuItem.getStatus());
        menuItemDTOResponse.setType(menuItem.getType());
        menuItemDTOResponse.setImage_path(menuItem.getImage_path());
        menuItemDTOResponse.setDate_added(menuItem.getDate_added());
        return menuItemDTOResponse;
    }


    private MenuItem mapToEntity(MenuItemDTORequest menuItemDTORequest) {
        MenuItem menuItem = new MenuItem();
        menuItem.setId(0); //Set Id as 0 to trigger auto increment
        menuItem.setName(menuItemDTORequest.getName());
        menuItem.setPrice(menuItemDTORequest.getPrice());
        menuItem.setDescription(menuItemDTORequest.getDescription());
        menuItem.setStatus(menuItemDTORequest.getStatus());
        menuItem.setType(menuItemDTORequest.getType());
        menuItem.setImage_path(menuItemDTORequest.getImage_path());
        menuItem.setDate_added(menuItemDTORequest.getDate_added());
        return menuItem;
    }

    private void updateEntityFromDTO(MenuItem menuItem, MenuItemDTORequest dtoRequest) {
        menuItem.setName(dtoRequest.getName());
        menuItem.setPrice(dtoRequest.getPrice());
        menuItem.setDescription(dtoRequest.getDescription());
        menuItem.setStatus(dtoRequest.getStatus());
        menuItem.setType(dtoRequest.getType());
        menuItem.setImage_path(dtoRequest.getImage_path());
    }


    @Override
    public String uploadImage(int menuItemID, MultipartFile file){
        System.out.println("Saving picture for menu");
        Optional<MenuItem> result =menuItemRepository.findById(menuItemID);
        MenuItem menuItem =null;
        if(result.isPresent()){
            menuItem=result.get();
        }else{
            throw new BadRequestException("Menu Item not found - ID: "+menuItemID);
        }
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
