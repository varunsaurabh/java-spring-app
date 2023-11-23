package RestAPI.APIDemo.service;

import RestAPI.APIDemo.model.Product;
import RestAPI.APIDemo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Cacheable(cacheNames = "products", key = "#id")
    public Product getProductById(Long id){
        System.out.println("fetching from db");
    if(productRepository.findAll().size() > 0)
        return productRepository.findById(id).get();
    else return null;
    }

    public void saveProducts(List<Product> productList) {
        productRepository.saveAll(productList);
    }

    public List<Product> findAll() {

        return new ArrayList<>(productRepository.findAll());
    }

    @CachePut(cacheNames = "products", key="#product.id")
    public String updateProduct(Long id, Product product) {
//        Product product1 = new Product();
//        product1.setProductName(product.getProductName());
//        product1.setProductPrice(product.getProductPrice());
//        product1.setProductType(product.getProductType());
//
//        productRepository.save(product1);

        Product existingProduct = productRepository.findById(id).get();
        existingProduct.setProductName(product.getProductName());
        existingProduct.setProductPrice(product.getProductPrice());
        existingProduct.setProductType(product.getProductType());
        productRepository.save(existingProduct);

        return "Product having id: " + id + " is updated successfully!";
    }

    @CacheEvict(cacheNames = "products", key = "#id")
    public String  deleteProductWithId(Long id) {
        productRepository.deleteById(id);
        return "Product having id: " + id + " is deleted successfully!";
    }
}
