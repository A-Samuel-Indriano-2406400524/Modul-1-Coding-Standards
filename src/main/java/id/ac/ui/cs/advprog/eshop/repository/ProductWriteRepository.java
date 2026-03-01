package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;

public interface ProductWriteRepository {
    public Product create(Product product);
    public Product updateProduct(Product product);
    public void deleteProduct(String id);
}