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
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.never;
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
        Product product = new Product();
        String viewName = controller.createProductPage(product);
        assertEquals("createProduct", viewName);
    }

    @Test
    void testCreateProductPostWhenIdIsNull() {
        Product product = new Product();
        product.setProductId(null);
        String viewName = controller.createProductPost(product, model);
        assertEquals("redirect:list", viewName);
        assertNotNull(product.getProductId());
        assertFalse(product.getProductId().isBlank());
        verify(service).create(product);
    }

    @Test
    void testCreateProductPostWhenIdAlreadyExists() {
        Product product = new Product();
        product.setProductId("id-1");
        String viewName = controller.createProductPost(product, model);
        assertEquals("redirect:list", viewName);
        assertEquals("id-1", product.getProductId());
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
    void testEditProductRoot() {
        String viewName = controller.editProductRoot();
        assertEquals("redirect:/product/list", viewName);
    }

    @Test
    void testEditProductPageWhenProductFound() {
        Product product = new Product();
        product.setProductId("id-1");
        when(service.findProductById("id-1")).thenReturn(product);
        String viewName = controller.editProductPage("id-1", model);
        assertEquals("editProduct", viewName);
        verify(model).addAttribute("product", product);
    }

    @Test
    void testEditProductPageWhenProductNotFound() {
        when(service.findProductById("missing")).thenReturn(null);
        String viewName = controller.editProductPage("missing", model);
        assertEquals("redirect:/product/list", viewName);
        verify(model, never()).addAttribute(eq("product"), any());
    }

    @Test
    void testEditProductPost() {
        Product product = new Product();
        product.setProductId("id-1");
        String viewName = controller.editProductPost(product);
        assertEquals("redirect:/product/list", viewName);
        verify(service).updateProduct(product);
    }

    @Test
    void testDeleteProduct() {
        String viewName = controller.deleteProduct("id-1");
        assertEquals("redirect:/product/list", viewName);
        verify(service).deleteProduct("id-1");
    }
}
