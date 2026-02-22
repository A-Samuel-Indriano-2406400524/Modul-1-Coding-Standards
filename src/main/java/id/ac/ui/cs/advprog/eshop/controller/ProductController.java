package id.ac.ui.cs.advprog.eshop.controller;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/product")
public class ProductController {
    private String REDIRECT_PRODUCT_LIST = "redirect:/product/list";
    private ProductService service;

    public ProductController(ProductService service) {
        this.service = service;
    }
    
    @GetMapping("/create")
    public String createProductPage(@ModelAttribute("product") Product product){
        return "createProduct";
    }
    
    @PostMapping("/create")
    public String createProductPost(@ModelAttribute Product product, Model model){
        if (product.getProductId() == null || product.getProductId().isBlank()){
            product.setProductId(UUID.randomUUID().toString());
        }
        service.create(product);
        return "redirect:list";
    }
    
    @GetMapping("/list")
    public String productListPage(Model model){
        List<Product> allProducts = service.findAll();
        model.addAttribute("products", allProducts);
        return "productList";
    }

    @GetMapping({"/edit", "/edit/"})
    public String editProductRoot(){
        return REDIRECT_PRODUCT_LIST;
    }

    @GetMapping("/edit/{id}")
    public String editProductPage(@PathVariable("id") String productId, Model model){
        Product product = service.findProductById(productId);
        if (product == null){
            return REDIRECT_PRODUCT_LIST;
        }
        model.addAttribute("product", product);
        return "editProduct";
    }

    @PostMapping("/edit")
    public String editProductPost(@ModelAttribute Product product){
        service.updateProduct(product);
        return REDIRECT_PRODUCT_LIST;
    }

    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable("id") String productId){
        service.deleteProduct(productId);
        return REDIRECT_PRODUCT_LIST;
    }
}