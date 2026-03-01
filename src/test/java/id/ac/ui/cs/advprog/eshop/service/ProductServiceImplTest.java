package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.ProductReadRepository;
import id.ac.ui.cs.advprog.eshop.repository.ProductWriteRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.Iterator;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {

    @Mock
    private ProductReadRepository productReadRepository;

    @Mock
    private ProductWriteRepository productWriteRepository;

    @InjectMocks
    private ProductServiceImpl productService;

    @Test
    void testCreate() {
        Product product = new Product();
        product.setProductId("id-1");
        when(productWriteRepository.create(product)).thenReturn(product);

        Product createdProduct = productService.create(product);
        assertSame(product, createdProduct);
        verify(productWriteRepository).create(product);
    }

    @Test
    void testFindAll() {
        Product firstProduct = new Product();
        firstProduct.setProductId("id-1");
        Product secondProduct = new Product();
        secondProduct.setProductId("id-2");
        Iterator<Product> iterator = List.of(firstProduct, secondProduct).iterator();
        when(productReadRepository.findAll()).thenReturn(iterator);

        List<Product> products = productService.findAll();
        assertEquals(2, products.size());
        assertSame(firstProduct, products.get(0));
        assertSame(secondProduct, products.get(1));
    }

    @Test
    void testFindById() {
        Product product = new Product();
        product.setProductId("id-1");
        when(productReadRepository.findById("id-1")).thenReturn(product);
        
        Product result = productService.findById("id-1");
        assertSame(product, result);
        verify(productReadRepository).findById("id-1");
    }

    @Test
    void testUpdate() {
        Product product = new Product();
        product.setProductId("id-1");
        productService.update("id-1", product);
        verify(productWriteRepository).update("id-1", product);
    }

    @Test
    void testDeleteProductById() {
        productService.deleteProductById("id-1");
        verify(productWriteRepository).delete("id-1");
    }
}
