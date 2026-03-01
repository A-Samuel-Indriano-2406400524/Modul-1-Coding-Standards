package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import java.util.Iterator;

public interface ProductReadRepository {
    public Iterator<Product> findAll();
    public Product findProductById(String id);
}