package com.ucc.crudservice.controller;


import com.ucc.crudservice.model.Product;
import com.ucc.crudservice.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping ("api/products")
@RequiredArgsConstructor

//todos  @ son anotaciones

public class ProductController {
    private  final ProductService productService; //inyectamos el service


    //metodos para obtener los productos
    @GetMapping
    @ResponseStatus(HttpStatus.OK)

    //luego llamamos a la funcion que define que queremos obtener
    public List<Product> getProducts(){
        return this.productService.getProducts();
    }

    //metodos para agregar un producto
    @PostMapping //metodo post
    @ResponseStatus(HttpStatus.OK)//responde el estado del metodo es una anotacion 200=ok 404 etc
    //llamamos la funcion para crear un nuevo producto

    public ResponseEntity<Object> newProduct (@Valid @RequestBody Product product, BindingResult bindingResult){

        if (bindingResult.hasErrors()){
            //manejos de los errores
            List<String> error = bindingResult.getAllErrors().stream()//devuelve una lista de los errores y stream te lo comvierte en string
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)//mapea la lista y lo tranforma en mensaje
                    .collect(Collectors.toList()); //metodo para rencopilar la lista de error y muestra al cliente el error
            return  new ResponseEntity<>(error , HttpStatus.BAD_REQUEST);//retorna el mensaje
        }
        return  productService.newProduct(product);// si no hay error crea el producto

    }

    //metodo put update
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Object> updateProduct(@PathVariable("id")Long id,@RequestBody Product updateProduct){
        return productService.updateProduct(id,updateProduct);
    }

    //Delete metodo
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Object> deleteProduct(@PathVariable("id")Long id){
        return  productService.deleteProduct(id);
    }
}
