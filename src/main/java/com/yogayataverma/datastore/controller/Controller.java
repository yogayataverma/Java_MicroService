package com.yogayataverma.datastore.controller;

import com.yogayataverma.datastore.model.Model;
import com.yogayataverma.datastore.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/products")
public class Controller {
    @Autowired
    private ProductService service;

    @GetMapping
    public List<Model> getAllProducts() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<byte[]> getProductImage(@PathVariable Long id) {
        Model product = service.findById(id);
        if (product != null && product.getProductImage() != null) {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.IMAGE_JPEG); // Adjust the media type based on your image's actual type
            return new ResponseEntity<>(product.getProductImage(), headers, HttpStatus.OK);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/upload")
    public ResponseEntity<Model> uploadProduct(
            @RequestParam("productName") String productName,
            @RequestParam("price") double price,
            @RequestParam("productImage") MultipartFile file,
            @RequestParam("storeName") String storeName,
            @RequestParam("productColor") String productColor) throws IOException {
        Model product = new Model();
        product.setProductName(productName);
        product.setPrice(price);
        product.setProductImage(file.getBytes());
        product.setStoreName(storeName);
        product.setProductColor(productColor);
        Model savedProduct = service.save(product);
        return ResponseEntity.ok(savedProduct);
    }
}
