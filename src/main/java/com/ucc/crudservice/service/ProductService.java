package com.ucc.crudservice.service;


import com.ucc.crudservice.model.Product;
import com.ucc.crudservice.repositories.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor

public class ProductService {
    private final ProductRepository productRepository; //inyeccion de dedependecia inyectamos todos los metedos de la libreria

    //metodo para obtener los productos
    public List<Product> getProducts (){
        return  productRepository.findAll();
    }

    //metodo para crear producto
    public ResponseEntity<Object>  newProduct(Product product) {
        productRepository.save(product);
        return new ResponseEntity<>(
                HttpStatus.CREATED
        );
    }
//Metodo para actualizar un producto
public ResponseEntity<Object> updateProduct(Long id,Product updatedProduct){ //definimos el update que acepte el id del product y updateproduct que devuelve el objeto

    Optional<Product>existingProductOptional = productRepository.findById(id); //utilizamos este jpa para buscar el producto que coinsida con el producto de la base de datos

    if(existingProductOptional.isPresent()){ //si existe un product y esta presente(isPresent)

        Product existingProduct = existingProductOptional.get();
        existingProduct.setSku(updatedProduct.getSku());
        existingProduct.setName(updatedProduct.getName());
        existingProduct.setDescription(updatedProduct.getDescription());
        existingProduct.setPrice(updatedProduct.getPrice());
        existingProduct.setStatus(updatedProduct.getStatus());


        productRepository.save(existingProduct);
        return new ResponseEntity<>("Product updated successfully", HttpStatus.OK);//mensaje de okey

    }else {
        return new ResponseEntity<>("product not found" , HttpStatus.NOT_FOUND); //mensaje de error 404
    }

    }



    public ResponseEntity<Object> deleteProduct(Long id){
        Optional<Product>existingProductOptional = productRepository.findById(id);

        if(existingProductOptional.isPresent()) {

            productRepository.deleteById(id);
            return new ResponseEntity<>("Producto eliminado", HttpStatus.OK);//mensaje de okey
        }else {
            return new ResponseEntity<>("product not encontrado" , HttpStatus.NOT_FOUND); //mensaje de error 404
        }

    }
}
