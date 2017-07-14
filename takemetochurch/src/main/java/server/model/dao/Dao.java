package server.model.dao;

/**
 * Created by Cyrille on 13/07/17.
 */
public interface Dao<T> {

    void create(T data);

    T read(long id);

    void update(T data);

    void delete(T data);

    T finById(Long id);
}
