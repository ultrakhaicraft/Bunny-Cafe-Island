package BunnyCafeIsland.API;

import BunnyCafeIsland.DTO.Request.BunnyInfoDTORequest;
import BunnyCafeIsland.DTO.Response.ApiResponse;
import BunnyCafeIsland.DTO.Response.BunnyDetailDTOResponse;
import BunnyCafeIsland.DTO.Response.BunnyViewDTOResponse;
import BunnyCafeIsland.DTO.Response.MenuItemDTOResponse;
import BunnyCafeIsland.Service.Interface.IBunnyService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.nio.file.Path;
import java.util.*;

import BunnyCafeIsland.Entity.Bunny;

import BunnyCafeIsland.Enums.AvailabilityStatus;
import BunnyCafeIsland.Exception.BadRequestException;

import BunnyCafeIsland.Service.BunnyService;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.DeleteMapping;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/api")
public class BunnyAPI {

    
    private final IBunnyService bunnyService;

    @Autowired
    public BunnyAPI(BunnyService bunnyService){
        this.bunnyService=bunnyService;
    }
    
    @GetMapping("/bunnies")
    public ApiResponse<Page<BunnyViewDTOResponse>> getAllBunnyWithPagination(@RequestParam(defaultValue = "0") int page,
                                                                            @RequestParam(defaultValue = "5") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<BunnyViewDTOResponse> data = bunnyService.GetAllBunniesPaged(pageable);
        return ApiResponse.success("Get all Bunnies as Page success",data);
    }

    @GetMapping("/bunnies/{bunnyId}")
    public ApiResponse<BunnyDetailDTOResponse> getABunny(@PathVariable int bunnyId) {
        BunnyDetailDTOResponse response = bunnyService.getBunnyById(bunnyId);
        if(response==null){
            throw new BadRequestException("Bunny not found - ID: "+bunnyId);
        }
        return ApiResponse.success("Get a Bunny success", response);
    }

    @PostMapping("/bunnies")
    public ApiResponse<BunnyViewDTOResponse> addBunny(@RequestBody BunnyInfoDTORequest dtoRequest) {
        BunnyViewDTOResponse response = bunnyService.create(dtoRequest);
        return ApiResponse.success("Create new bunny success", response);
    }
    
    @PutMapping("/bunnies/{bunnyId}")
    public ApiResponse<BunnyViewDTOResponse> updateBunny(@RequestBody BunnyInfoDTORequest dtoRequest, @PathVariable int bunnyId) {
        BunnyViewDTOResponse response=bunnyService.update(dtoRequest,bunnyId);
        return ApiResponse.success("Update bunny success", response);
    }

    @PatchMapping("/bunnies/{bunnyId}")
    public ApiResponse<BunnyViewDTOResponse> changeBunnyStatus(@PathVariable int bunnyId, @RequestParam AvailabilityStatus status) {
        BunnyDetailDTOResponse result = bunnyService.getBunnyById(bunnyId);
        if(result==null){
            throw new BadRequestException("Bunny not found  ID: "+bunnyId);
        }
        BunnyViewDTOResponse dtoResponse=bunnyService.changeAvailabilityStatus(bunnyId, status);
        return ApiResponse.success("Change status of Bunny successful", dtoResponse);
    }

    @PatchMapping("/bunnies/add-medical-record/{bunnyId}")
    public ApiResponse<Integer> addMedicalRecordToBunny(@PathVariable int bunnyId, @RequestParam int medicalRecordId) {
        bunnyService.addMedicalRecordToBunny(bunnyId,medicalRecordId);
        return ApiResponse.success("Add Medical Record to Bunny success",bunnyId);
    }
    
    @DeleteMapping("/bunnies/{bunnyId}")
    public ApiResponse<Integer> deleteBunny(@PathVariable int bunnyId) {
        BunnyDetailDTOResponse response = bunnyService.getBunnyById(bunnyId);
        if(response==null){
            throw new BadRequestException("Bunny not found  ID: "+bunnyId);
        }
        bunnyService.delete(bunnyId);
        return ApiResponse.success("Delete Bunny success " + bunnyId,bunnyId);
    }

    
    
}
