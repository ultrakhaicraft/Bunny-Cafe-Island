package BunnyCafeIsland.DAO;

import BunnyCafeIsland.Enums.AvailabilityStatus;
import BunnyCafeIsland.Entity.Bunny;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class BunnyDAO implements IBunnyDAO {

    public EntityManager entityManager;

    @Autowired
    public BunnyDAO(EntityManager entityManager){
        this.entityManager=entityManager;
    }

    @Override
    @Transactional
    public void save(Bunny aBunny) {
        entityManager.persist(aBunny);
    }

    @Override
    public Bunny findById(int id) {
        return entityManager.find(Bunny.class,id);
    }

    @Override
    public List<Bunny> findAll() {
        TypedQuery<Bunny> result = entityManager.createQuery("FROM Bunny order by name", Bunny.class);
        return result.getResultList();
    }

    @Override
    public List<Bunny> findByBreed(String breed) {
        TypedQuery<Bunny> result = entityManager.createQuery("FROM Bunny WHERE breed=:breedKeyword", Bunny.class);
        result.setParameter("breedKeyword",breed);
        return result.getResultList();
    }

    @Override
    public List<Bunny> findByName(String name) {
        TypedQuery<Bunny> result = entityManager.createQuery("FROM Bunny WHERE name=:nameKeyword", Bunny.class);
        result.setParameter("nameKeyword",name);
        return result.getResultList();
    }

    @Override
    @Transactional
    public void update(Bunny aBunny) {
        entityManager.merge(aBunny);
    }

    //Simply change status to Unavailable
    @Override
    @Transactional
    public void softRemove(Bunny aBunny) {
        aBunny.setAvailabilityStatus(AvailabilityStatus.Unavailable);
        entityManager.merge(aBunny);
    }

    @Override
    @Transactional
    public void hardRemove(int id) {
        Bunny tempBunny= findById(id);
        if(tempBunny==null){
            System.out.println("Bunny not found!!, Exiting deletion");
            return;
        }
        System.out.println("Bunny found!!, Begin deletion");
        entityManager.remove(tempBunny);
        System.out.println("Delete completed");
    }
}
