package by.itAcademy.dao.ds;

import by.itAcademy.dao.ds.api.IDaoLocation;
import by.itAcademy.dao.entity.Location;
import by.itAcademy.dao.support.api.IManger;

import javax.persistence.EntityManager;
import javax.persistence.criteria.*;
import java.time.LocalDateTime;
import java.util.List;

public class DaoLocation implements IDaoLocation {
    private final IManger manger;

    public DaoLocation(IManger manger) {
        this.manger = manger;
    }

    @Override
    public void add(Location location) {
        EntityManager em = null;
        try {
            em = manger.getEntityManager();
            em.getTransaction().begin();
            em.persist(location);
            em.getTransaction().commit();
        } catch (RuntimeException e){
            if(em!=null){
                em.getTransaction().rollback();
            }
            throw new RuntimeException("Error in base, method add, class DaoLocation");
        } finally {
            if(em!=null) {
                em.close();
            }
        }
    }

    @Override
    public List<Location> getAll() {
        List<Location> locations;
        EntityManager em = null;
        try {
            em = manger.getEntityManager();
            em.getTransaction().begin();
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Location> criteria = cb.createQuery(Location.class);
            Root<Location> locationRoot = criteria.from(Location.class);
            criteria.select(locationRoot);
            locations = em.createQuery(criteria).getResultList();
            em.getTransaction().commit();
        } catch (Exception e){
            if(em!=null){
                em.getTransaction().rollback();
            }
            throw new RuntimeException("Error in base, method detAll, class DaoLocation");
        } finally {
            if(em!=null){
                em.close();
            }
        }
        return locations;
    }

    @Override
    public Location getById(Long id) {
        List<Location> location;
        EntityManager em = null;
        try {
            em = manger.getEntityManager();
            em.getTransaction().begin();
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Location> query = cb.createQuery(Location.class);
            Root<Location> locationRoot = query.from(Location.class);
            query.select(locationRoot).where(cb.equal(locationRoot.get("id"),id.longValue()));
            location = em.createQuery(query).getResultList();
            if(location.size()>1)
                throw new RuntimeException("Error in base, from id");
            em.getTransaction().commit();
        }catch (Exception e){
            if(em!=null){
                em.getTransaction().rollback();
            }
            throw new RuntimeException("Error in base, method 'getById', table 'Location'");
        } finally {
            if(em!=null){
                em.close();
            }
        }
        if(location.size()==0)
            throw new IllegalArgumentException("Данного id не существует");
        return location.get(0);
    }

    @Override
    public void delete(Long id, LocalDateTime dateUpdate) {
        EntityManager em = null;
        try {
            em = manger.getEntityManager();
            em.getTransaction().begin();
            CriteriaBuilder builder = em.getCriteriaBuilder();
            CriteriaDelete<Location> delete = builder.createCriteriaDelete(Location.class);
            Root<Location> locationRoot = delete.from(Location.class);
            Predicate predicate = builder.and(
                    builder.equal(locationRoot.get("id"),id),
                    builder.equal(locationRoot.get("dateUpdate"),dateUpdate)
            );
            delete.where(predicate);
            em.createQuery(delete).executeUpdate();
            em.getTransaction().commit();
        } catch (RuntimeException e){
            if(em!=null){
                em.getTransaction().rollback();
                throw new RuntimeException("Error in base, method 'delete', class Location");
            }
        } finally {
            if(em!=null){
                em.close();
            }
        }
    }

    @Override
    public void update(Long id, LocalDateTime dateUpdate, Location location) {
        EntityManager em = null;
        try {
            em = manger.getEntityManager();
            em.getTransaction().begin();
            CriteriaBuilder builder = em.getCriteriaBuilder();
            CriteriaUpdate<Location> update = builder.createCriteriaUpdate(Location.class);
            Root<Location> locationRoot = update.from(Location.class);
            Predicate predicate = builder.and(
                    builder.equal(locationRoot.get("id"),id),
                    builder.equal(locationRoot.get("dateUpdate"),location.getDateUpdate())
            );
            LocalDateTime localDateTime = LocalDateTime.now();
            update
                    .set("name",location.getName())
                    .set("dateUpdate",localDateTime);
            update.where(predicate);
            int count = em.createQuery(update).executeUpdate();
            em.getTransaction().commit();
            System.out.println(count);
        } catch (RuntimeException e){
            if(em!=null){
                em.getTransaction().rollback();
                throw new RuntimeException("Error in base, method 'update', class Location");
            }
        } finally {
            if(em!=null){
                em.close();
            }
        }
    }

    @Override
    public boolean exist(Long id) {
        EntityManager em = null;
        boolean exist = false;
        try {
            em = manger.getEntityManager();
            em.getTransaction().begin();
            CriteriaBuilder builder = em.getCriteriaBuilder();
            CriteriaQuery<Location> query = builder.createQuery(Location.class);
            Root<Location> locationRoot = query.from(Location.class);
            query.select(locationRoot.getCorrelationParent()).
                    where(builder.equal(locationRoot.get("id"),id));
            Location location = em.createQuery(query).getSingleResult();
            em.getTransaction().commit();
            if(location!=null){
                exist = true;
            }
        } catch (RuntimeException e){
            if(em!=null){
                em.getTransaction().rollback();
                throw new RuntimeException("Error in base, method 'exit from id', class Location");
            }
        } finally {
            if(em!=null){
                em.close();
            }
        }
        return exist;
    }

    @Override
    public boolean exist(String name) {
        EntityManager em = null;
        boolean exit = false;
        try {
            em = manger.getEntityManager();
            em.getTransaction().begin();
            CriteriaBuilder builder = em.getCriteriaBuilder();
            CriteriaQuery<Location> query = builder.createQuery(Location.class);
            Root<Location> locationRoot = query.from(Location.class);
            query.select(locationRoot).where(builder.equal(locationRoot.get("name"),name));
            List<Location> location = em.createQuery(query).getResultList();
            em.getTransaction().commit();
            if(location!=null&&location.size()!=0){
                exit = true;
            }
        } catch (RuntimeException e){
            if(em!=null){
                em.getTransaction().rollback();
            }
            throw new RuntimeException("Error in base, method 'exit from name', class Location");
        } finally {
            if(em!=null){
                em.close();
            }
        }
        return exit;
    }
}
