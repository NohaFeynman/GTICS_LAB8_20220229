package com.example.servidor_api.controller;

import com.example.servidor_api.entity.Product;
import com.example.servidor_api.repository.ProductRepository;
import com.example.servidor_api.repository.CategoryRepository;
import com.example.servidor_api.repository.SupplierRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/product")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ProductController {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final SupplierRepository supplierRepository;

    public ProductController(ProductRepository productRepository,
                             CategoryRepository categoryRepository,
                             SupplierRepository supplierRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.supplierRepository = supplierRepository;
    }


    @GetMapping
    public List<Product> listarProductos() {
        return productRepository.findAll();
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> obtenerProductoPorId(@PathVariable Integer id) {
        Optional<Product> opt = productRepository.findById(id);
        if (opt.isPresent()) {
            return ResponseEntity.ok(opt.get());
        } else {
            Map<String, Object> error = new HashMap<>();
            error.put("estado", "error");
            error.put("mensaje", "Producto no encontrado");
            return ResponseEntity.badRequest().body(error);
        }
    }


    @PostMapping
    public ResponseEntity<?> crearProducto(@RequestBody Product nuevoProducto) {
        try {
            if (nuevoProducto.getCategory() != null) {
                int catId = nuevoProducto.getCategory().getId();
                nuevoProducto.setCategory(categoryRepository.findById(catId).orElse(null));
            }

            if (nuevoProducto.getSupplier() != null) {
                int supId = nuevoProducto.getSupplier().getId();
                nuevoProducto.setSupplier(supplierRepository.findById(supId).orElse(null));
            }

            Product guardado = productRepository.save(nuevoProducto);
            Map<String, Object> respuesta = new HashMap<>();
            respuesta.put("estado", "creado");
            respuesta.put("producto", guardado);
            return ResponseEntity.status(HttpStatus.CREATED).body(respuesta);

        } catch (Exception e) {
            Map<String, Object> error = new HashMap<>();
            error.put("estado", "error");
            error.put("mensaje", e.getMessage());
            return ResponseEntity.internalServerError().body(error);
        }
    }


    @PutMapping
    public ResponseEntity<?> actualizarProducto(@RequestBody Product productoActualizado) {
        if (productoActualizado.getId() == null) {
            Map<String, Object> error = new HashMap<>();
            error.put("estado", "error");
            error.put("mensaje", "Debe proporcionar un ID para actualizar el producto");
            return ResponseEntity.badRequest().body(error);
        }

        Optional<Product> opt = productRepository.findById(productoActualizado.getId());

        if (opt.isPresent()) {
            Product existente = opt.get();
            existente.setProductName(productoActualizado.getProductName());
            existente.setUnit(productoActualizado.getUnit());
            existente.setPrice(productoActualizado.getPrice());

            if (productoActualizado.getCategory() != null) {
                existente.setCategory(categoryRepository.findById(
                        productoActualizado.getCategory().getId()).orElse(null));
            }

            if (productoActualizado.getSupplier() != null) {
                existente.setSupplier(supplierRepository.findById(
                        productoActualizado.getSupplier().getId()).orElse(null));
            }

            Product guardado = productRepository.save(existente);
            Map<String, Object> ok = new HashMap<>();
            ok.put("estado", "actualizado");
            ok.put("producto", guardado);

            return ResponseEntity.ok(ok);

        } else {
            Map<String, Object> error = new HashMap<>();
            error.put("estado", "error");
            error.put("mensaje", "Producto no encontrado");
            return ResponseEntity.badRequest().body(error);
        }
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarProducto(@PathVariable Integer id) {
        Optional<Product> opt = productRepository.findById(id);

        if (opt.isPresent()) {
            productRepository.deleteById(id);
            Map<String, Object> ok = new HashMap<>();
            ok.put("estado", "ok");
            ok.put("mensaje", "Producto eliminado correctamente");
            return ResponseEntity.ok(ok);
        } else {
            Map<String, Object> error = new HashMap<>();
            error.put("estado", "error");
            error.put("mensaje", "Producto no encontrado");
            return ResponseEntity.badRequest().body(error);
        }
    }
}

