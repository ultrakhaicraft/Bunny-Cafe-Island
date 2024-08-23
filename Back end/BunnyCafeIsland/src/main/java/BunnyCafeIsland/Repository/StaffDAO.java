package BunnyCafeIsland.Repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import BunnyCafeIsland.Entity.Staff;
import BunnyCafeIsland.Enums.StaffStatus;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

@Repository
public class StaffDAO implements IStaffDAO {

    private EntityManager entityManager;

    @Autowired
    public StaffDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Staff save(Staff aStaff) {
        Staff dbStaff = entityManager.merge(aStaff);
        return dbStaff;
    }

    @Override
    public Staff findById(int id) {
        return entityManager.find(Staff.class, id);
    }

    @Override
    public List<Staff> findAll() {
        TypedQuery<Staff> result= entityManager.createQuery("FROM Staff order by name", Staff.class);
        return result.getResultList();
    }

    @Override
    public List<Staff> findByPhone(String phone) {
        TypedQuery<Staff> result= entityManager.createQuery("FROM Staff WHERE phone LIKE : phoneKeyword", Staff.class);
        result.setParameter("phoneKeyword","%"+ phone+"%");
        return result.getResultList();
    }

    @Override
    public List<Staff> findByEmail(String email) {
        TypedQuery<Staff> result= entityManager.createQuery("FROM Staff WHERE email LIKE : emailKeyword", Staff.class);

        result.setParameter("emailKeyword", "%"+email+"%");
        return result.getResultList();
    }

    @Override
    public List<Staff> findByName(String name) {
        TypedQuery<Staff> result= entityManager.createQuery("FROM Staff WHERE name LIKE :nameKeyword", Staff.class);
        result.setParameter("nameKeyword", "%"+name+"%");
        return result.getResultList();
    }

    @Override
    public void update(Staff aStaff) {
        entityManager.merge(aStaff);    
    }

    @Override
    public void softRemove(Staff aStaff) {
        aStaff.setStatus(StaffStatus.Inactive);
        entityManager.merge(aStaff);
    }

    @Override
    public void hardRemove(int id) {
        Staff tempStaff= findById(id);
        if(tempStaff==null){
            System.out.println("Staff not found!!, Exiting deletion");
            return;
        }
        System.out.println("Staff found!!, Begin deletion");
        entityManager.remove(tempStaff);
        System.out.println("Delete completed");
    }

}
