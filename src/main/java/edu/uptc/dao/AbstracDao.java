package edu.uptc.dao;

import edu.uptc.utils.EntityManagerUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

public  abstract class AbstracDao<T> implements Dao<T> {
    private EntityManager entityManager = EntityManagerUtil.getEntityManager();

    private Class<T> entityClass;


    @Override
    public Optional<T> findById(Integer id) {
        return Optional.ofNullable(entityManager.find( entityClass,id));
    }

    @Override
    public List<T> findAll() {
        String hQuery = "FROM " + entityClass.getName();

        Query query = entityManager.createQuery( hQuery );

        return query.getResultList();
    }

    @Override
    public void save(T t) {
        executeInsideTransaction( entityManager -> entityManager.persist(t));
    }

    @Override
    public void update(T t) {
        executeInsideTransaction( entityManager -> entityManager.merge(t));
    }

    @Override
    public void delete(T t) {
        executeInsideTransaction( entityManager -> entityManager.remove(t));
    }

    private void executeInsideTransaction( Consumer<EntityManager> action){
        EntityTransaction entityTransaction = entityManager.getTransaction();

        try{
            entityTransaction.begin();
            action.accept( entityManager );
            entityTransaction.commit();
        }catch(RuntimeException e){
            entityTransaction.rollback();
            throw e;
        }
    }

    public void setEntityClass(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }
}
