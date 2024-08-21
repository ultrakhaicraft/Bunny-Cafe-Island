package BunnyCafeIsland.DAO;

import BunnyCafeIsland.Entity.Bunny;

import java.util.List;

public interface IBunnyDAO {

    void save(Bunny aBunny);
    Bunny findById(int id);
    List<Bunny> findAll();
    List<Bunny> findByBreed(String breed);
    List<Bunny> findByName(String name);
    void update(Bunny aBunny);
    void softRemove(Bunny aBunny);
    void hardRemove(int id);
}
