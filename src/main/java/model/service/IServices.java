package model.service;

import java.util.List;

public interface IServices<T, E> {
    List<T> findAll();

    boolean save(T object);

    boolean update(T object);

    boolean delete(E id);
    T findById(E id);
}
