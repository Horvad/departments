package by.itAcademy.dao.ds;

import by.itAcademy.dao.ds.api.IDaoDepartment;
import by.itAcademy.dao.entity.Department;
import by.itAcademy.dao.entity.Location;
import by.itAcademy.dao.support.api.IManger;
import org.hibernate.internal.CriteriaImpl;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.criteria.*;
import javax.swing.*;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class DaoDepartment implements IDaoDepartment {
    private final IManger manger;

    public DaoDepartment(IManger manger) {
        this.manger = manger;
    }

    @Override
    public void add(Department department) {
        EntityManager em = null;
        try {
            em = manger.getEntityManager();
            em.getTransaction().begin();
            em.persist(department);
            em.getTransaction().commit();
        }catch (RuntimeException e){
            if(em!=null){
                em.getTransaction().rollback();
                throw new RuntimeException("Error in base, method 'add', DaoDepartment class");
            }
        }finally {
            if(em!=null){
                em.close();
            }
        }
    }

    @Override
    public List<Department> getAll() {
        EntityManager em = null;
        List<Department> departments = new ArrayList<>();
        try {
            em = manger.getEntityManager();
            em.getTransaction().begin();
            CriteriaBuilder builder = em.getCriteriaBuilder();
            CriteriaQuery<Department> query = builder.createQuery(Department.class);
            Root<Department> departmentRoot = query.from(Department.class);
            query.select(departmentRoot);
            departments = em.createQuery(query).getResultList();
            em.getTransaction().commit();
        } catch (RuntimeException e){
            if(em!=null){
                em.getTransaction().rollback();
            }
            throw new RuntimeException("Error on base, method 'getAll', Department class");
        }
        return departments;
    }

    @Override
    public Department getById(Long id) {
        EntityManager em = null;
        List<Department> departments = null;
        try {
            em = manger.getEntityManager();
            em.getTransaction().begin();
            CriteriaBuilder builder = em.getCriteriaBuilder();
            CriteriaQuery<Department> query = builder.createQuery(Department.class);
            Root<Department> departmentRoot = query.from(Department.class);
            query.select(departmentRoot).where(builder.equal(departmentRoot.get("id"),id));
            departments = em.createQuery(query).getResultList();
            em.getTransaction().commit();
        } catch (RuntimeException e){
            if(em!=null)
            {
                em.getTransaction().rollback();
            }
            throw new RuntimeException("Error in base, method 'getById', class DaoDepartment");
        } finally {
            if(em!=null){
                em.close();
            }
        }
        return departments.get(0);
    }

    @Override
    public void delete(Long id, LocalDateTime dateUpdate) {
        EntityManager em = null;
        try {
            em = manger.getEntityManager();
            em.getTransaction().begin();
            CriteriaBuilder builder = em.getCriteriaBuilder();
            CriteriaDelete<Department> delete = builder.createCriteriaDelete(Department.class);
            Root<Department> departmentRoot = delete.from(Department.class);
            Predicate predicate = builder.and(
                    builder.equal(departmentRoot.get("id"),id),
                    builder.equal(departmentRoot.get("dateUpdate"),dateUpdate)
            );
            delete.where(predicate);
            em.createQuery(delete).executeUpdate();
            em.getTransaction().commit();
        } catch (RuntimeException e){
            if(em!=null){
                em.getTransaction().rollback();
            }
            throw new RuntimeException("Error in base, method 'delete', class class DaoDepartment");
        } finally {
            if(em!=null){
                em.close();
            }
        }
    }

    @Override
    public void update(Long id, LocalDateTime dateUpdate, Department department) {
        EntityManager em = null;
        try {
            em = manger.getEntityManager();
            em.getTransaction().begin();
            CriteriaBuilder builder = em.getCriteriaBuilder();
            CriteriaUpdate<Department> update = builder.createCriteriaUpdate(Department.class);
            Root<Department> departmentRoot = update.from(Department.class);
            Predicate predicate = builder.and(
                    builder.equal(departmentRoot.get("id"),id),
                    builder.equal(departmentRoot.get("dateUpdate"),department.getDateUpdate())
            );
            LocalDateTime localDateTime = LocalDateTime.now();
            update
                    .set("name",department.getName())
                    .set("phone",department.getPhone())
                    .set("location",department.getLocation())
                    .set("parent",department.getParent())
                    .set("dateUpdate",localDateTime);
            update.where(predicate);
            em.createQuery(update).executeUpdate();
            em.getTransaction().commit();
        } catch (RuntimeException e){
            if(em!=null){
                em.getTransaction().rollback();
            }
            throw new RuntimeException("Error is base, method 'update', class Department");
        } finally {
            if (em!=null){
                em.close();
            }
        }
    }

    @Override
    public boolean exist(Long id) {
        EntityManager em = null;
        boolean ex = false;
        try {
            em = manger.getEntityManager();
            em.getTransaction().begin();
            CriteriaBuilder builder = em.getCriteriaBuilder();
            CriteriaQuery<Department> query = builder.createQuery(Department.class);
            Root<Department> departmentRoot = query.from(Department.class);
            query.select(departmentRoot).where(builder.equal(departmentRoot.get("id"),id));
            List<Department> department = em.createQuery(query).getResultList();
            if(department!=null&&department.size()==1){
                ex =true;
            }
        } catch (RuntimeException e){
            if(em!=null){
                em.getTransaction().rollback();
            }
            throw new RuntimeException("Error in base, method 'exist by id, class Department'");
        } finally {
            if(em!=null){
                em.close();
            }
        }
        return ex;
    }

    @Override
    public boolean exist(String name) {
        EntityManager em = null;
        boolean ex = false;
        try {
            em = manger.getEntityManager();
            em.getTransaction().begin();
            CriteriaBuilder builder = em.getCriteriaBuilder();
            CriteriaQuery<Department> query = builder.createQuery(Department.class);
            Root<Department> departmentRoot = query.from(Department.class);
            query.select(departmentRoot).where(builder.equal(departmentRoot.get("name"),name));
            Department department = em.createQuery(query).getSingleResult();
            if(department!=null){
                ex =true;
            }
        } catch (RuntimeException e){
            if(em!=null){
                em.getTransaction().rollback();
            }
            throw new RuntimeException("Error in base, method 'exist by id, class Department'");
        } finally {
            if(em!=null){
                em.close();
            }
        }
        return ex;
    }

    @Override
    public List<Department> getChild(Department department){
        EntityManager em = null;
        List<Department> departments = new ArrayList<>();
        try {
            em = manger.getEntityManager();
            em.getTransaction().begin();
            CriteriaBuilder builder = em.getCriteriaBuilder();
            CriteriaQuery<Department> query = builder.createQuery(Department.class);
            Root<Department> departmentRoot = query.from(Department.class);
            query.select(departmentRoot)
                    .where(builder.equal(departmentRoot.get("parent"),department));
            departments = em.createQuery(query).getResultList();
            em.getTransaction().commit();
        } catch (RuntimeException e){
            if(em!=null){
                em.getTransaction().rollback();
            }
        } finally {
            if(em!=null){
                em.close();
            }
        }
        return departments;
//        List<Department> departments = getAll();
//        List<Department> departmentChildren = new ArrayList<>();
//        for(Department departmentSearch : departments){
//            if(departmentSearch.getParent()!=null&&departmentSearch.getParent().equals(department)){
//                departmentChildren.add(departmentSearch);
//            }
//        }
//        return departmentChildren;
    }

    @Override
    public boolean exitSaveLocation(Location location) {
        EntityManager em = null;
        boolean exist = false;
        try {
            em = manger.getEntityManager();
            em.getTransaction().begin();
            CriteriaBuilder builder = em.getCriteriaBuilder();
            CriteriaQuery<Department> query = builder.createQuery(Department.class);
            Root<Department> departmentRoot = query.from(Department.class);
            query.select(departmentRoot.getCorrelationParent()).
                    where(builder.equal(departmentRoot.get("location"),location));
            List<Department> departments = em.createQuery(query).getResultList();
            em.getTransaction().commit();
            if(departments!=null&&departments.size()!=0){
                exist =true;
            }
        } catch (RuntimeException e){
            if(em!=null){
                em.getTransaction().rollback();
            }
            throw new RuntimeException("Error is base, method existSaveLocation, class DaoDepartment");
        } finally {
            if(em!=null){
                em.close();
            }
        }
        return exist;
    }

    @Override
    public List<Location> getSaveLocation() {
        EntityManager em = null;
        List<Location> locations = new ArrayList<>();
        try {
            em = manger.getEntityManager();
            em.getTransaction().begin();
            CriteriaBuilder builder = em.getCriteriaBuilder();
            CriteriaQuery<Department> query = builder.createQuery(Department.class);
            Root<Department> departmentRoot = query.from(Department.class);
            query.select(departmentRoot.get("location"));
            List<Department> departments = em.createQuery(query).getResultList();
            for(Department department : departments){
                locations.add(department.getLocation());
            }
            em.getTransaction().commit();
        } catch (RuntimeException e){
            if(em!=null){
                em.getTransaction().rollback();
            }
            throw new RuntimeException("Error is base, method getSaveLocation, class DaoDepartment");
        } finally {
            if(em!=null){
                em.close();
            }
        }
        return locations;
    }
}
