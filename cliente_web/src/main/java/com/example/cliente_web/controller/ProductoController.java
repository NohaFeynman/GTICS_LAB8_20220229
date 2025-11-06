package com.example.cliente_web.controller;

import com.example.cliente_web.model.Product;
import com.example.cliente_web.service.ProductoApiService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class ProductoController {

    private final ProductoApiService api;

    public ProductoController(ProductoApiService api) {
        this.api = api;
    }

    @GetMapping("/")
    public String inicio() {
        return "index";
    }

    @GetMapping("/productos")
    public String listar(Model model) {
        model.addAttribute("productos", api.listar());
        return "productos";
    }

    @PostMapping("/productos/buscar")
    public String buscar(@RequestParam("id") String idStr, Model model) {
        model.addAttribute("productos", api.listar());
        try {
            Integer id = Integer.valueOf(idStr);
            Product p = api.obtenerPorId(id);
            if (p == null) {
                model.addAttribute("error", "Producto no encontrado");
            } else {
                model.addAttribute("resultado", p);
            }
        } catch (NumberFormatException e) {
            model.addAttribute("error", "ID inválido");
        }
        return "productos";
    }
}
