package BunnyCafeIsland.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import BunnyCafeIsland.Entity.MenuItem;

public interface MenuItemRepository extends JpaRepository<MenuItem,Integer> {

}
