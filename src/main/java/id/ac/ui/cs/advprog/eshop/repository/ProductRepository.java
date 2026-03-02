package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Repository
public class ProductRepository implements ProductReadRepository, ProductWriteRepository {
    private List<Product> productData = new ArrayList<>();

    @Override
    public Product create(Product product){
        productData.add(product);
        return product;
    }
    
    @Override
    public Iterator<Product> findAll(){
        return productData.iterator();
    }
    
    @Override
    public Product findById(String id){
        for (Product product : productData){
            if (product.getProductId().equals(id)){
                return product;
            }
        }
        return null;
    }
    
    @Override
    public Product update(String id, Product product){
        for (int i = 0; i < productData.size(); i++){
            Product p = productData.get(i);
            if (p.getProductId().equals(id)){
                productData.set(i, product);
                return product;
            }
        }
        return null;
    }

    @Override
    public void delete(String id){
        Iterator<Product> iterator = productData.iterator();
        while (iterator.hasNext()){
            Product product = iterator.next();
            if (product.getProductId().equals(id)){
                iterator.remove();
                return;
            }
        }
    }
}