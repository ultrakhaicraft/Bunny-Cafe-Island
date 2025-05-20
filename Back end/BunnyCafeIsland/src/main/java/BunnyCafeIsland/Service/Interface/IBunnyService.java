package BunnyCafeIsland.Service.Interface;

import BunnyCafeIsland.Entity.Bunny;
import BunnyCafeIsland.Enums.AvailabilityStatus;

import java.util.List;

public interface IBunnyService {
    List<Bunny> getAllBunny();
    List<Bunny> getBunnyByBreed(String breed);
    Bunny getBunnyById(int id);
    Bunny save(Bunny aBunny);
    Bunny changeStatus(int id, AvailabilityStatus status);
    void deleteBunnyById(int id);
}
