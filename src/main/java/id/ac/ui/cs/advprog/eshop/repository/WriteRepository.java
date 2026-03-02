package id.ac.ui.cs.advprog.eshop.repository;

public interface WriteRepository<T, ID> {
    public T create(T entity);
    public T update(ID id, T entity);
    public void delete(ID id);
}