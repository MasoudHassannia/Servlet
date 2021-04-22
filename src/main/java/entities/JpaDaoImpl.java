package entities;

import javax.persistence.EntityManager;

public abstract class JpaDaoImpl<E,I> {
    protected EntityManager entityManager;

    public JpaDaoImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public void save(E entity){
        entityManager.persist(entity);
    }

    public void update(E entity) {
        entityManager.merge(entity);
    }

    public void remove(E entity) {
        entityManager.remove(entity);
    }

        public E loadById(I id){
        return entityManager.find(getEntityClass(),id);
    }


    protected abstract Class<E> getEntityClass();

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
}
