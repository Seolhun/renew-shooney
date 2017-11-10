
package hi.cord.com.user.common;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;

/**
 * The type Abstract repository.
 *
 * @Waring : I will not used this. never use anywhere, except before code.
 *
 * @param <PK> the type parameter
 * @param <T>  the type parameter
 */
public abstract class AbstractRepository<PK extends Serializable, T> {

    private final Class<T> persistentClass;

    @SuppressWarnings("unchecked")
    public AbstractRepository() {
        this.persistentClass = (Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[1];
    }

    @Autowired
    private SessionFactory sessionFactory;

    protected Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    public T getByKey(PK id) {
        return (T) getSession().get(persistentClass, id);
    }

    public T getByKeyByLong(Long id) {
        return (T) getSession().get(persistentClass, id);
    }

    public void persist(T entity) {
        getSession().persist(entity);
    }

    public void update(T entity) {
        getSession().update(entity);
    }

    public void delete(T entity) {
        getSession().delete(entity);
    }

    protected Criteria createEntityCriteria() {
        return getSession().createCriteria(persistentClass);
    }
}