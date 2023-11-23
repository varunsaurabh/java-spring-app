package RestAPI.APIDemo.controller;

import RestAPI.APIDemo.model.Product;
import RestAPI.APIDemo.repository.ProductRepository;
import RestAPI.APIDemo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @PostMapping
    public void crateProduct(@RequestBody List<Product> productList){
        productService.saveProducts(productList);
    }

    @GetMapping("/{product-id}")
    @ResponseStatus(HttpStatus.OK)
    public Product getProductDetails(@PathVariable("product-id") Long id){
        return productService.getProductById(id);
    }
    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<Product> getAllProducts(){
        List<Product> productList = new ArrayList<>();
        productList = productService.findAll();
        return productList;
    }

    @PutMapping("/{product-id}")
    @ResponseStatus(HttpStatus.OK)
    public String updateProduct(@PathVariable("product-id")Long id, @RequestBody Product product){
        return productService.updateProduct(id, product);
    }

    @DeleteMapping("/{product-id}")
    @ResponseStatus(HttpStatus.OK)
    public String deleteProductWithId(@PathVariable("product-id") Long id){
        return productService.deleteProductWithId(id);
    }
}
