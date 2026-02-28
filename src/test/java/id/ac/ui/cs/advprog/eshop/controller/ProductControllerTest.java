package id.ac.ui.cs.advprog.eshop.controller;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.service.ProductService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductControllerTest {

    @Mock
    private ProductService service;

    @Mock
    private Model model;

    @InjectMocks
    private ProductController controller;

    @Test
    void testCreateProductPage() {
        String viewName = controller.createProductPage(model);
        assertEquals("createProduct", viewName);
        verify(model).addAttribute(org.mockito.ArgumentMatchers.eq("product"), org.mockito.ArgumentMatchers.any(Product.class));
    }

    @Test
    void testCreateProductPost() {
        Product product = new Product();
        String viewName = controller.createProductPost(product, model);
        assertEquals("redirect:list", viewName);
        verify(service).create(product);
    }

    @Test
    void testProductListPage() {
        Product product = new Product();
        List<Product> products = List.of(product);
        when(service.findAll()).thenReturn(products);
        String viewName = controller.productListPage(model);
        assertEquals("productList", viewName);
        verify(model).addAttribute("products", products);
    }

    @Test
    void testEditProductPageWhenProductFound() {
        Product product = new Product();
        product.setProductId("id-1");
        when(service.findById("id-1")).thenReturn(product);
        String viewName = controller.editProductPage("id-1", model);
        assertEquals("editProduct", viewName);
        verify(model).addAttribute("product", product);
    }

    @Test
    void testEditProductPageWhenProductNotFound() {
        when(service.findById("missing")).thenReturn(null);
        String viewName = controller.editProductPage("missing", model);
        assertEquals("editProduct", viewName);
        verify(model).addAttribute("product", null);
    }

    @Test
    void testEditProductPost() {
        Product product = new Product();
        product.setProductId("id-1");
        String viewName = controller.editProductPost(product, model);
        assertEquals("redirect:list", viewName);
        verify(service).update("id-1", product);
    }

    @Test
    void testDeleteProduct() {
        String viewName = controller.deleteProduct("id-1");
        assertEquals("redirect:list", viewName);
        verify(service).deleteProductById("id-1");
    }
}
