package br.com.api.produtos.service;


import br.com.api.produtos.model.ProductModel;
import br.com.api.produtos.model.ProductRegistrationModel;
import br.com.api.produtos.model.ResponseModel;
import br.com.api.produtos.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import static org.springframework.http.HttpStatus.*;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ResponseModel responseModel;

    public Iterable<ProductModel> listProducts() {
        return productRepository.findAll();
    }

    public ResponseEntity<?> saveProduct(ProductRegistrationModel request) {

        if (request.nome() == null || request.nome().equals("")) {
            responseModel.setStatus(BAD_REQUEST.value());
            responseModel.setMessage("Nome do produto é obrigatório.");
            return new ResponseEntity<>(responseModel, BAD_REQUEST);
        } else if (request.marca() == null || request.marca().equals("")) {
            responseModel.setStatus(BAD_REQUEST.value());
            responseModel.setMessage("Nome da marca é obrigatório");
            return new ResponseEntity<>(responseModel, BAD_REQUEST);
        } else {
            ProductModel productModel = ProductModel.builder()
                    .nome(request.nome())
                    .marca(request.marca())
                    .build();

            productRepository.save(productModel);
            responseModel.setStatus(CREATED.value());
            responseModel.setMessage("Produto cadastrado com sucesso.");
            return new ResponseEntity<>(responseModel, CREATED);
        }

    }

    public ResponseEntity<?> updateProduct(ProductModel productModel) {

        if (productRepository.existsById(productModel.getId())) {
            if (productModel.getNome().equals("")) {
                responseModel.setStatus(BAD_REQUEST.value());
                responseModel.setMessage("Nome do produto é obrigatório.");
                return new ResponseEntity<>(responseModel, BAD_REQUEST);
            } else if (productModel.getMarca().equals("")) {
                responseModel.setStatus(BAD_REQUEST.value());
                responseModel.setMessage("Nome da marca é obrigatório");
                return new ResponseEntity<>(responseModel, BAD_REQUEST);
            } else {
                productRepository.save(productModel);
                responseModel.setStatus(OK.value());
                responseModel.setMessage("Produto alterado com sucesso!");
                return new ResponseEntity<>(responseModel, OK);
            }
        } else {
            responseModel.setMessage("Product does not exist.");
            return new ResponseEntity<>(responseModel, BAD_REQUEST);
        }

    }

    public ResponseEntity<?> deleteProduct(Long id) {

        if (!(productRepository.existsById(id))) {
            responseModel.setStatus(BAD_REQUEST.value());
            responseModel.setMessage("Product does not exist.");
            return new ResponseEntity<>(responseModel, BAD_REQUEST);
        }

        productRepository.deleteById(id);
        responseModel.setStatus(OK.value());
        responseModel.setMessage("Product was removed.");
        return new ResponseEntity<>(responseModel, OK);
    }
}
