package BunnyCafeIsland.API;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import BunnyCafeIsland.DTO.Request.MenuItemDTORequest;
import BunnyCafeIsland.DTO.Response.ApiResponse;
import BunnyCafeIsland.DTO.Response.MenuItemDTOResponse;
import BunnyCafeIsland.Service.Interface.IMenuItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import BunnyCafeIsland.Exception.BadRequestException;
import BunnyCafeIsland.Service.MenuItemService;
import org.springframework.web.multipart.MultipartFile;

import static BunnyCafeIsland.Constant.Constant.IMAGE_DIRECTORY;
import static org.springframework.http.MediaType.IMAGE_JPEG_VALUE;
import static org.springframework.http.MediaType.IMAGE_PNG_VALUE;

@RestController
@RequestMapping("/api")
public class MenuItemAPI {

    private final IMenuItemService menuItemService;

    @Autowired
    public MenuItemAPI(MenuItemService menuItemService){
        this.menuItemService=menuItemService;
    }
    


    @ResponseBody
    @GetMapping("/menuItems")
    public ApiResponse<Page<MenuItemDTOResponse>> getAllMenuItemWithPagination(@RequestParam(defaultValue = "0") int page,
                                                                               @RequestParam(defaultValue = "5") int size){
        Pageable pageable = PageRequest.of(page, size);
        Page<MenuItemDTOResponse> data = menuItemService.getAllPageable(pageable);
        return ApiResponse.success("Get all Menu Item as Page success",data);

    }

    @GetMapping("/menuItems/{menuItemId}")
    public ApiResponse<MenuItemDTOResponse> getMenuItemById(@PathVariable int menuItemId) {
        MenuItemDTOResponse response = menuItemService.getById(menuItemId);
        if(response==null){
            throw new BadRequestException("Menu Item not found - ID: "+menuItemId);
        }
        return ApiResponse.success("Get a Menu Item success", response);
    }

    @PostMapping("/menuItems")
    public ApiResponse<MenuItemDTOResponse> addMenuItem(@RequestBody MenuItemDTORequest menuItemDTORequest) {
        MenuItemDTOResponse response = menuItemService.add(menuItemDTORequest);
        return ApiResponse.success("Create new menu items success", response);
    }
    
    @PutMapping("/menuItems/{menuItemId}")
    public ApiResponse<MenuItemDTOResponse> updateMenuItem(@RequestBody MenuItemDTORequest menuItemDTORequest, @PathVariable int menuItemId) {
        MenuItemDTOResponse response = menuItemService.update(menuItemId,menuItemDTORequest);
        return ApiResponse.success("Update a menu items success", response);

    }

    @DeleteMapping("/menuItems/{menuItemId}")
    public ApiResponse<Integer> deleteMenuItem(@PathVariable int menuItemId) {
        MenuItemDTOResponse data = menuItemService.getById(menuItemId);
        if(data==null){
            throw new BadRequestException("Menu Item not found  ID: "+menuItemId);
        }
        menuItemService.delete(menuItemId);
        return ApiResponse.success("Delete Menu Item " + menuItemId,menuItemId);
    }


    //TODO: Make this into a global thing

    @PutMapping("/menuItems/images")
    public ResponseEntity<String> uploadPhoto(@RequestParam("id") int id, @RequestParam("file") MultipartFile file) {
        return ResponseEntity.ok().body(menuItemService.uploadImage(id,file));
    }

    @GetMapping(path = "/menuItems/images/{filename}", produces = { IMAGE_PNG_VALUE, IMAGE_JPEG_VALUE })
    public byte[] getPhoto(@PathVariable("filename") String filename) throws IOException {
        return Files.readAllBytes(Paths.get(IMAGE_DIRECTORY + filename));
    }


    //Todo later: Change only status quickly
}
