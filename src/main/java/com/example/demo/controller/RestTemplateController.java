package com.example.demo.controller;

import com.example.demo.model.Cliente;
import com.example.demo.services.ApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rest_template")
public class RestTemplateController {
    @Autowired
    private ApiService api_service;

    @GetMapping
    public ResponseEntity<String> get() {
        return this.api_service.get();
    }

    @PostMapping
    public ResponseEntity<Cliente> post(@RequestBody Cliente cliente) {
        return this.api_service.post(cliente);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        this.api_service.delete(id);
    }
}
