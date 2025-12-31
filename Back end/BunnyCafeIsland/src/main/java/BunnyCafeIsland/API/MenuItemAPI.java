package BunnyCafeIsland.API;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import BunnyCafeIsland.DTO.Request.MenuItemDTORequest;
import BunnyCafeIsland.DTO.Response.MenuItemResponse;
import BunnyCafeIsland.DTO.Response.SingleMessageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import BunnyCafeIsland.Entity.MenuItem;
import BunnyCafeIsland.Exception.BadRequestException;
import BunnyCafeIsland.Service.MenuItemService;
import org.springframework.web.multipart.MultipartFile;

import static BunnyCafeIsland.Constant.Constant.IMAGE_DIRECTORY;
import static org.springframework.http.MediaType.IMAGE_JPEG_VALUE;
import static org.springframework.http.MediaType.IMAGE_PNG_VALUE;

@RestController
@RequestMapping("/api/management")
public class MenuItemAPI {

    private MenuItemService menuItemService;

    @Autowired
    public MenuItemAPI(MenuItemService menuItemService){
        this.menuItemService=menuItemService;
    }
    


    @ResponseBody
    @GetMapping("/menuItems")
    public Page<MenuItem> getAllWithPagination ( @RequestParam(defaultValue = "0") int page,
                                                 @RequestParam(defaultValue = "5") int size){
        Pageable pageable = PageRequest.of(page, size);
        return menuItemService.getAllPageable(pageable);

    }

    @GetMapping("/menuItems/{menuItemId}")
    public MenuItemResponse get(@PathVariable int menuItemId) {
        MenuItem menuItem = menuItemService.getById(menuItemId);
        if(menuItem==null){
            throw new BadRequestException("Menu Item not found - ID: "+menuItemId);
        }
        return menuItemService.convertToDTO(menuItem);
    }

    @PostMapping("/menuItems")
    public MenuItemResponse add(@RequestBody MenuItemDTORequest menuItemDTORequest) {
        MenuItem menuItem=menuItemService.convertToEntity(menuItemDTORequest);
        MenuItem dbMenuItem=menuItemService.save(menuItem);
        return menuItemService.convertToDTO(dbMenuItem);
    }
    
    @PutMapping("/menuItems")
    public MenuItemResponse update(@RequestBody MenuItemDTORequest menuItemDTORequest) {
        MenuItem menuItem=menuItemService.convertToEntity(menuItemDTORequest);
        MenuItem dbMenuItem=menuItemService.save(menuItem);
        return menuItemService.convertToDTO(dbMenuItem);
    }

    @DeleteMapping("/menuItems/{menuItemId}")
    public SingleMessageResponse delete(@PathVariable int menuItemId) {
        MenuItem aBunny = menuItemService.getById(menuItemId);
        if(aBunny==null){
            throw new BadRequestException("Menu Item not found  ID: "+menuItemId);
        }
        menuItemService.delete(menuItemId);

        SingleMessageResponse responseMessage = new SingleMessageResponse();
        responseMessage.setMessage("Delete Menu Item " + menuItemId);
        return responseMessage;
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
