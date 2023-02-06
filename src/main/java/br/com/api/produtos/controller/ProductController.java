package br.com.api.produtos.controller;

import br.com.api.produtos.model.ProductModel;
import br.com.api.produtos.model.ProductRegistrationModel;
import br.com.api.produtos.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping()
    public Iterable<ProductModel> getAllProducts() {
        return productService.listProducts();
    }

    @PostMapping()
    public ResponseEntity<?> save(@RequestBody ProductRegistrationModel productRegistrationModel) {
        return productService.saveProduct(productRegistrationModel);
    }

    @PutMapping()
    public ResponseEntity<?> updateProduct(@RequestBody ProductModel productModel) {
        return productService.updateProduct(productModel);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable Long id) {
        return productService.deleteProduct(id);
    }

}
