package id.ac.ui.cs.advprog.eshop.repository;

import java.util.Iterator;

public interface ReadRepository<T, ID> {
    public Iterator<T> findAll();
    public T findById(ID id);
}