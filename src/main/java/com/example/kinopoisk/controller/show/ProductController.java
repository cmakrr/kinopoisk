package com.example.kinopoisk.controller.show;

import com.example.kinopoisk.model.entities.show.Product;
import com.example.kinopoisk.service.product.interfaces.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService showService;

    @GetMapping("/{id}")
    public Product show(Model model, @PathVariable Long id){
        return showService.findById(id);
    }

    @PostMapping("/find")
    public Product findByName(@RequestParam String showName){
        return showService.findByName(showName);
    }
}
